package com.cndfactory.shoppingmall.domain.dto.shop;

import com.cndfactory.shoppingmall.domain.entity.shop.Shop;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ShopSaveDto {
	private String shopCode = "";
	private String shopName;

	public Shop toEntity() {
		return Shop.builder()
				.shopCode(this.shopCode)
				.shopName(this.shopName)
				.build();
	}
}
