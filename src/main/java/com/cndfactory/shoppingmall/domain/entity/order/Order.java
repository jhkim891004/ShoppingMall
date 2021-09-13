package com.cndfactory.shoppingmall.domain.entity.order;

import com.cndfactory.shoppingmall.domain.entity.BaseEntity;
import com.cndfactory.shoppingmall.domain.entity.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "TB_ORDER")
public class Order extends BaseEntity {

	@Column(updatable = false, unique = true)
	private String orderCode;
	@Column
	private String statusCode;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "memberId")
	private Member member;


}
