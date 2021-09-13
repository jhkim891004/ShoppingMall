package com.cndfactory.shoppingmall.web.controller.product;

import com.cndfactory.shoppingmall.domain.dto.product.ProductModifyDto;
import com.cndfactory.shoppingmall.domain.dto.product.ProductResponseDto;
import com.cndfactory.shoppingmall.domain.dto.product.ProductSaveDto;
import com.cndfactory.shoppingmall.web.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class ProductRestController {
	private final ProductService productService;

	@GetMapping("/api/v1/products/{id}")
	public ResponseEntity<ProductResponseDto> getOne(@PathVariable Long id) {
		ProductResponseDto dto = productService.getOne(id);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@GetMapping("/api/v1/products")
	public ResponseEntity<Page<ProductResponseDto>> getAll(Pageable page) {
		Page<ProductResponseDto> dto = productService.getAll(page);

		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@PostMapping("/api/v1/products")
	public ResponseEntity<ProductResponseDto> save(@RequestBody @Valid ProductSaveDto dto) {
		ProductResponseDto responseDto = productService.save(dto);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

	@PutMapping("/api/v1/products")
	public ResponseEntity<ProductResponseDto> modify(@RequestBody @Valid ProductModifyDto dto) {
		ProductResponseDto responseDto = productService.modify(dto);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

}
