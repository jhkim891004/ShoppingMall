package com.cndfactory.shoppingmall.web.service.product;

import com.cndfactory.shoppingmall.domain.dto.product.ProductModifyDto;
import com.cndfactory.shoppingmall.domain.dto.product.ProductResponseDto;
import com.cndfactory.shoppingmall.domain.dto.product.ProductSaveDto;
import com.cndfactory.shoppingmall.domain.entity.product.Product;
import com.cndfactory.shoppingmall.domain.entity.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.function.Function;

@RequiredArgsConstructor
@Service
public class ProductService {
	private final ProductRepository productRepository;

	public ProductResponseDto getOne(Long id) {
		return productRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Not Found Product")).toDto();

	}

	public Page<ProductResponseDto> getAll(Pageable page) {
		return productRepository.findAll(page).map(new Function<Product, ProductResponseDto>() {
			@Override
			public ProductResponseDto apply(Product product) {
				return product.toDto();
			}
		});
	}

	@Transactional
	public ProductResponseDto save(ProductSaveDto dto) {
		Product product = dto.toEntity();
		product = productRepository.save(product);

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
