package com.cndfactory.shoppingmall.web.service.shop;

import com.cndfactory.shoppingmall.domain.dto.shop.ShopResponseDto;
import com.cndfactory.shoppingmall.domain.dto.shop.ShopSaveDto;
import org.springframework.data.domain.Page;

public interface ShopService {

	ShopResponseDto getOne(Long id);
	Page<ShopResponseDto> getAll();
	void modify(com.cndfactory.shoppingmall.domain.dto.shop.ShopModifyDto dto);
	void save(ShopSaveDto dto);

}
