package com.cndfactory.shoppingmall.web.service.product;

import com.cndfactory.shoppingmall.domain.dto.product.ProductModifyDto;
import com.cndfactory.shoppingmall.domain.dto.product.ProductResponseDto;
import com.cndfactory.shoppingmall.domain.dto.product.ProductSaveDto;
import com.cndfactory.shoppingmall.domain.entity.product.Product;
import com.cndfactory.shoppingmall.domain.entity.product.ProductRepository;
import com.cndfactory.shoppingmall.domain.entity.shop.Shop;
import com.cndfactory.shoppingmall.domain.entity.shop.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class ProductService {
	private final ProductRepository productRepository;
	private final ShopRepository shopRepository;

	public ProductResponseDto getOne(Long id) {
		return productRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Not Found Product")).toDto();

	}

	public Page<ProductResponseDto> getAll(Pageable page) {
		return productRepository.findAll(page)
				.map(product -> {
					return product.toDto();
				});
	}

	public Page<ProductResponseDto> getAllByShop(Pageable page, Long shopId) {
		return productRepository.findAllByShopId(page, shopId)
				.map(product-> {
					return product.toDto();
				});
	}

	@Transactional
	public ProductResponseDto save(ProductSaveDto dto) {
		Product product = dto.toEntity();
		product = productRepository.save(product);

		Shop shop = shopRepository.findById(dto.getShopId())
				.orElseThrow(() -> new NoSuchElementException("Not Found Shop"));

		product.addShop(shop);
		product.updateProductCode(product.getId());

		return product.toDto();
	}

	@Transactional
	public ProductResponseDto modify(ProductModifyDto dto) {
		Product product = productRepository.findById(dto.getId())
				.orElseThrow(() -> new NoSuchElementException("Not Found Product"));
		product.updateProductInfo(dto.getProductName());

		return product.toDto();
	}
}
