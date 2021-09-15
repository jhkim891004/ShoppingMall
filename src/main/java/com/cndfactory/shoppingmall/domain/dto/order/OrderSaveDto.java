package com.cndfactory.shoppingmall.domain.dto.order;

import com.cndfactory.shoppingmall.domain.entity.order.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class OrderSaveDto {
	private List<Long> productId;
	private String statusCode;

	public Order toEntity() {
		return Order.builder()
				.statusCode(this.statusCode)
				.build();
	}
}
