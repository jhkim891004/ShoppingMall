package com.cndfactory.shoppingmall.domain.dto.shop;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ShopSaveDto {
	private String shopName;
	private String shopCode;
	private String useYn;

	@Builder
	public ShopSaveDto(String shopName, String shopCode, String useYn) {
		this.shopName = shopName;
		this.shopCode = shopCode;
		this.useYn = useYn;
	}
}
