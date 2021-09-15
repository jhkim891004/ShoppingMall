package com.cndfactory.shoppingmall.web.controller.order;

import com.cndfactory.shoppingmall.domain.dto.order.OrderResponseDto;
import com.cndfactory.shoppingmall.domain.dto.order.OrderSaveDto;
import com.cndfactory.shoppingmall.web.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class OrderRestController {
	private final OrderService orderService;

	@GetMapping("/api/v1/orders/{id}")
	public ResponseEntity<OrderResponseDto> getOne(@PathVariable Long id) {
		return new ResponseEntity<>(orderService.getOne(id), HttpStatus.OK);
	}

	@GetMapping("/api/v1/orders")
	public ResponseEntity<Page<OrderResponseDto>> getAll(Pageable pageable) {
		return new ResponseEntity<>(orderService.getAll(pageable), HttpStatus.OK);
	}

	@PostMapping("/api/v1/orders")
	public ResponseEntity<OrderResponseDto> save(@RequestBody @Valid OrderSaveDto dto) {
		return new ResponseEntity<>(orderService.save(dto), HttpStatus.OK);
	}
}
