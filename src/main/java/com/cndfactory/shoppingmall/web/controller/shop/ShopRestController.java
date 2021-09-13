package com.cndfactory.shoppingmall.web.controller.shop;

import com.cndfactory.shoppingmall.domain.dto.shop.ShopModifyDto;
import com.cndfactory.shoppingmall.domain.dto.shop.ShopResponseDto;
import com.cndfactory.shoppingmall.domain.dto.shop.ShopSaveDto;
import com.cndfactory.shoppingmall.utils.exception.BusinessException;
import com.cndfactory.shoppingmall.web.service.shop.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class ShopRestController {
	private final ShopService shopService;

	@GetMapping("/api/v1/shops/{id}")
	public ResponseEntity<ShopResponseDto> getOne(@PathVariable Long id) throws BusinessException {
		// 테스트02 반영
		// merge 충돌 병합 테스트
		return new ResponseEntity<ShopResponseDto>(shopService.getOne(id), HttpStatus.OK);
	}

	@GetMapping("/api/v1/shops")
	public ResponseEntity<Page<ShopResponseDto>> getAll(Pageable page) {
		return new ResponseEntity<Page<ShopResponseDto>>(shopService.getAll(page), HttpStatus.OK);
	}

	@PostMapping("/api/v1/shops")
	public ResponseEntity<ShopResponseDto> save(@RequestBody @Valid ShopSaveDto dto) {
		return new ResponseEntity<ShopResponseDto>(shopService.save(dto), HttpStatus.OK);
	}

	@PutMapping("/api/v1/shops")
	public ResponseEntity<ShopResponseDto> modify(@RequestBody @Valid ShopModifyDto dto) {
		return new ResponseEntity<ShopResponseDto>(shopService.modify(dto), HttpStatus.OK);
	}


}
