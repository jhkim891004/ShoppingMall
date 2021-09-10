package com.cndfactory.shoppingmall.domain.dto.shop;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ShopResponseDto {
	private String shopName;
	private String shopCode;
	private String useYn;

	@Builder
	public ShopResponseDto(String shopName, String shopCode, String useYn) {
		this.shopName = shopName;
		this.shopCode = shopCode;
		this.useYn = useYn;
	}
}
