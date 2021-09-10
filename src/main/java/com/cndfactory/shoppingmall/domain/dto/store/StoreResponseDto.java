package com.cndfactory.shoppingmall.domain.dto.store;

import lombok.Builder;
import lombok.Getter;

@Getter
public class StoreResponseDto {
	private String storeName;
	private String storeCode;
	private String useYn;

	@Builder
	public StoreResponseDto(String storeName, String storeCode, String useYn) {
		this.storeName = storeName;
		this.storeCode = storeCode;
		this.useYn = useYn;
	}
}
