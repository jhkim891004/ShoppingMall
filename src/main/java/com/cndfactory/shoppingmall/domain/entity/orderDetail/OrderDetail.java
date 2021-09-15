package com.cndfactory.shoppingmall.domain.entity.orderDetail;

import com.cndfactory.shoppingmall.domain.entity.BaseEntity;
import com.cndfactory.shoppingmall.domain.entity.order.Order;
import com.cndfactory.shoppingmall.domain.entity.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "TB_ORDER_DETAIL")
public class OrderDetail extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orderId")
	private Order order;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productId")
	private Product product;

	public void addOrder(Order order) {
		this.order = order;
	}
	public void addProduct(Product product) {
		this.product = product;
	}
}
