package com.cndfactory.shoppingmall.domain.entity.shop;

import com.cndfactory.shoppingmall.domain.dto.shop.ShopResponseDto;
import com.cndfactory.shoppingmall.domain.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tb_shop")
public class Shop extends BaseEntity {
	@Column(updatable = false, unique = true)
	private String shopCode;
	@Column
	private String shopName;
	@Column
	private String useYn;

	public ShopResponseDto toDto() {
		return ShopResponseDto.builder()
				.shopName(this.shopName)
				.shopCode(this.shopCode)
				.useYn(this.useYn)
				.build();
	}

	@Builder
	public Shop(String shopCode, String shopName, String useYn) {
		this.shopCode = shopCode;
		this.shopName = shopName;
		this.useYn = useYn;
	}
}
