package com.cndfactory.shoppingmall.web.service.shop;

import com.cndfactory.shoppingmall.domain.dto.shop.ShopModifyDto;
import com.cndfactory.shoppingmall.domain.dto.shop.ShopResponseDto;
import com.cndfactory.shoppingmall.domain.dto.shop.ShopSaveDto;
import com.cndfactory.shoppingmall.domain.entity.shop.Shop;
import com.cndfactory.shoppingmall.domain.entity.shop.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.function.Function;

@RequiredArgsConstructor
@Service
public class ShopService {
	private final ShopRepository shopRepository;

	public ShopResponseDto getOne(Long id) {
		Shop shop = shopRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Not Found Shop"));

		return shop.toDto();
	}

	public Page<ShopResponseDto> getAll(Pageable page) {
		return shopRepository.findAll(page).map(new Function<Shop, ShopResponseDto>() {
			
			public ShopResponseDto apply(Shop shop) {
				return shop.toDto();
			}
		});
	}

	@Transactional
	public ShopResponseDto modify(ShopModifyDto dto) {
		Shop shop = shopRepository.findById(dto.getId())
				.orElseThrow(() -> new NoSuchElementException("Not Found Shop"));

		shop.updateInfo(dto);
		return shop.toDto();
	}

	@Transactional
	public ShopResponseDto save(ShopSaveDto dto) {
		Shop shop = shopRepository.save(dto.toEntity());
		shop.updateShopCode(shop.getId());
		return shop.toDto();
	}
}
