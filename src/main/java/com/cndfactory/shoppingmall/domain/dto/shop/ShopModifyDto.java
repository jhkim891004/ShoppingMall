package com.cndfactory.shoppingmall.domain.dto.shop;

import com.cndfactory.shoppingmall.domain.entity.shop.Shop;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ShopModifyDto {
	@NotBlank
	private String shopName;
	@NotBlank
	@Size(min = 8, max = 8)
	private String shopCode;
	@NotBlank
	@Size(min = 1, max = 1)
	private String useYn;

	public Shop toEntity() {
		return Shop.builder()
				.shopName(this.shopName)
				.shopCode(this.shopCode)
				.useYn(this.useYn)
				.build();
	}
}
