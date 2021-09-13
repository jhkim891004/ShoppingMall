package com.cndfactory.shoppingmall.domain.dto.shop;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ShopResponseDto {
	private Long id;
	private String shopName;
	private String shopCode;

	@Builder
	public ShopResponseDto(Long id, String shopName, String shopCode) {
		this.id = id;
		this.shopName = shopName;
		this.shopCode = shopCode;
	}
}
