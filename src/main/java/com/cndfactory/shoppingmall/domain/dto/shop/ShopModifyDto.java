package com.cndfactory.shoppingmall.domain.dto.shop;

import com.cndfactory.shoppingmall.domain.entity.shop.Shop;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
public class ShopModifyDto {
	@NotNull
	private Long id;
	@NotBlank
	private String shopName;

	@Builder
	public ShopModifyDto(Long id, String shopName) {
		this.id = id;
		this.shopName = shopName;
	}
}
