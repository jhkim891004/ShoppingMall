package com.cndfactory.shoppingmall.web.service.order;

import com.cndfactory.shoppingmall.domain.dto.order.OrderResponseDto;
import com.cndfactory.shoppingmall.domain.dto.order.OrderSaveDto;
import com.cndfactory.shoppingmall.domain.entity.member.Member;
import com.cndfactory.shoppingmall.domain.entity.member.MemberRepository;
import com.cndfactory.shoppingmall.domain.entity.order.Order;
import com.cndfactory.shoppingmall.domain.entity.order.OrderRepository;
import com.cndfactory.shoppingmall.domain.entity.orderDetail.OrderDetail;
import com.cndfactory.shoppingmall.domain.entity.orderDetail.OrderDetailRepository;
import com.cndfactory.shoppingmall.domain.entity.product.Product;
import com.cndfactory.shoppingmall.domain.entity.product.ProductRepository;
import com.cndfactory.shoppingmall.utils.security.domain.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class OrderService {
	private final OrderRepository orderRepository;
	private final MemberRepository memberRepository;
	private final OrderDetailRepository orderDetailRepository;
	private final ProductRepository productRepository;

	public OrderResponseDto getOne(Long id) {
		return orderRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Not Found Order")).toDto();
	}

	public Page<OrderResponseDto> getAll(Pageable pageable) {
		return orderRepository.findAll(pageable).map(order -> order.toDto());
	}

	@Transactional
	public OrderResponseDto save(OrderSaveDto dto) {
		CustomUserDetails securityUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Member member = memberRepository.findByMemberId(securityUser.getUsername())
				.orElseThrow(() -> new NoSuchElementException("Not Found Member"));

		Order order = orderRepository.save(dto.toEntity());
		order.updateOrderCode(order.getId());
		order.addMember(member);

		final List<OrderDetail> orderDetails = new ArrayList<>();
		List<Product> products = productRepository.findAllById(dto.getProductId());
		products.forEach(product -> {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.addOrder(order);
			orderDetail.addProduct(product);
			orderDetails.add(orderDetail);
		});
		orderDetailRepository.saveAll(orderDetails);

		return order.toDto();
	}

}
