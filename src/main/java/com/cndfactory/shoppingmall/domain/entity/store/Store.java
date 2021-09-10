package com.cndfactory.shoppingmall.domain.entity.store;

import com.cndfactory.shoppingmall.domain.dto.store.StoreResponseDto;
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
@Table(name = "tb_store")
public class Store extends BaseEntity {
	@Column(updatable = false, unique = true)
	private String storeCode;
	@Column
	private String storeName;
	@Column
	private String useYn;

	public StoreResponseDto toDto() {
		return StoreResponseDto.builder()
				.storeName(this.storeName)
				.storeCode(this.storeCode)
				.useYn(this.useYn)
				.build();
	}

	@Builder
	public Store(String storeCode, String storeName, String useYn) {
		this.storeCode = storeCode;
		this.storeName = storeName;
		this.useYn = useYn;
	}
}
