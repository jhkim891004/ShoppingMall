package com.cndfactory.shoppingmall.domain.dto.store;

import com.cndfactory.shoppingmall.domain.entity.store.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class StoreModifyDto {
	@NotBlank
	private String storeName;
	@NotBlank
	@Size(min = 8, max = 8)
	private String storeCode;
	@NotBlank
	@Size(min = 1, max = 1)
	private String useYn;

	public Store toEntity() {
		return Store.builder()
				.storeName(this.storeName)
				.storeCode(this.storeCode)
				.useYn(this.useYn)
				.build();
	}
}
