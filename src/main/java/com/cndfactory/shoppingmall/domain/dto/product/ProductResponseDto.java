package com.cndfactory.shoppingmall.domain.dto.product;

import com.cndfactory.shoppingmall.domain.dto.shop.ShopResponseDto;
import com.cndfactory.shoppingmall.domain.entity.shop.Shop;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ProductResponseDto {
	private Long id;
	private String productCode;
	private String productName;

	private ShopResponseDto shop;

	@Builder
	public ProductResponseDto(Long id, String productCode, String productName, Shop shop) {
		this.id = id;
		this.productCode = productCode;
		this.productName = productName;
		this.shop = shop.toDto();
	}
}
