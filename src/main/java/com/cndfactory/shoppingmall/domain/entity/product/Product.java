package com.cndfactory.shoppingmall.domain.entity.product;

import com.cndfactory.shoppingmall.domain.dto.product.ProductResponseDto;
import com.cndfactory.shoppingmall.domain.entity.BaseEntity;
import com.cndfactory.shoppingmall.domain.entity.shop.Shop;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "TB_PRODUCT")
public class Product extends BaseEntity {
	@Column(unique = true)
	private String productCode;
	@Column
	private String productName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shopId", updatable = false)
	private Shop shop;

	public void updateProductCode(Long id) {
		this.productCode = String.format("PRD%09d", id);
	}

	public void updateProductInfo(String productCode) {
		this.productCode = productCode;
	}

	public ProductResponseDto toDto() {
		return ProductResponseDto.builder()
				.id(this.getId())
				.productCode(this.productCode)
				.productName(this.productName)
				.build();
	}

	@Builder
	public Product(String productCode, String productName, Shop shop) {
		this.productCode = productCode;
		this.productName = productName;
		this.shop = shop;
	}
}
