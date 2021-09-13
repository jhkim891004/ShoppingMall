package com.cndfactory.shoppingmall.domain.dto.product;

import com.cndfactory.shoppingmall.domain.entity.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ProductSaveDto {
	private Long shopId;
	private String productCode;
	private String productName;

	public Product toEntity() {
		return Product.builder()
				.productCode(this.productCode)
				.productName(this.productName)
				.build();
	}
}
