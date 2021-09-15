package com.cndfactory.shoppingmall.domain.entity.order;

import com.cndfactory.shoppingmall.domain.dto.order.OrderResponseDto;
import com.cndfactory.shoppingmall.domain.entity.BaseEntity;
import com.cndfactory.shoppingmall.domain.entity.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "TB_ORDER")
public class Order extends BaseEntity {

	@Column
	private String orderCode;
	@Column
	private String statusCode;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "memberId")
	private Member member;

	public void updateOrderCode(Long id) {
		this.orderCode = String.format("ORD%09d", id);
	}

	public void addMember(Member member) {
		this.member = member;
	}

	public OrderResponseDto toDto() {
		return OrderResponseDto.builder()
				.id(this.getId())
				.orderCode(this.orderCode)
				.statusCode(this.statusCode)
				.build();
	}

	@Builder
	public Order(String orderCode, String statusCode, Member member) {
		this.orderCode = orderCode;
		this.statusCode = statusCode;
		this.member = member;
	}
}
