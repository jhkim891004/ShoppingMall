package com.cndfactory.shoppingmall.web.service.shop;

import com.cndfactory.shoppingmall.domain.dto.shop.ShopModifyDto;
import com.cndfactory.shoppingmall.domain.dto.shop.ShopResponseDto;
import com.cndfactory.shoppingmall.domain.dto.shop.ShopSaveDto;
import com.cndfactory.shoppingmall.domain.entity.shop.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ShopServiceImpl implements ShopService {
	private final ShopRepository shopRepository;

	@Override
	public ShopResponseDto getOne(Long id) {
		return null;
	}

	@Override
	public Page<ShopResponseDto> getAll() {
		return null;
	}

	@Override
	public void modify(ShopModifyDto dto) {

	}

	@Override
	public void save(ShopSaveDto dto) {

	}
}
