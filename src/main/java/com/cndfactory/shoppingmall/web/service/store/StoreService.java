package com.cndfactory.shoppingmall.web.service.store;

import com.cndfactory.shoppingmall.domain.dto.store.StoreModifyDto;
import com.cndfactory.shoppingmall.domain.dto.store.StoreResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StoreService {

	StoreResponseDto getOne(Long id);
	Page<StoreResponseDto> getAll();
	void modify(StoreModifyDto dto);

}
