package com.cndfactory.shoppingmall.domain.dto.order;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class OrderResponseDto {
	private Long id;
	private String orderCode;
	private String statusCode;

	@Builder
	public OrderResponseDto(Long id, String orderCode, String statusCode) {
		this.id = id;
		this.orderCode = orderCode;
		this.statusCode = statusCode;
	}
}
