package com.cndfactory.shoppingmall.domain.entity.shop;

import com.cndfactory.shoppingmall.domain.dto.shop.ShopModifyDto;
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
@Table(name = "TB_SHOP")
public class Shop extends BaseEntity {
	@Column(unique = true)
	private String shopCode;
	@Column
	private String shopName;

	public void updateInfo(ShopModifyDto dto) {
		this.shopName = dto.getShopName();
	}

	public void updateShopCode(Long id) {
		this.shopCode = String.format("SHP%09d", id);
	}

	public ShopResponseDto toDto() {
		return ShopResponseDto.builder()
				.id(this.getId())
				.shopName(this.shopName)
				.shopCode(this.shopCode)
				.build();
	}

	@Builder
	public Shop(String shopCode, String shopName) {
		this.shopCode = shopCode;
		this.shopName = shopName;
	}
}
