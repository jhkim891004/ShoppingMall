package com.cndfactory.shoppingmall.domain.entity.orderDetails;

import com.cndfactory.shoppingmall.domain.entity.BaseEntity;
import com.cndfactory.shoppingmall.domain.entity.order.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "TB_ORDER_DETAILS")
public class OrderDetails extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orderId")
	private Order order;


}
