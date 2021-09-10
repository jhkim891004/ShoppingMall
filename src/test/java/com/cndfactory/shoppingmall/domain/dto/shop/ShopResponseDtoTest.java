package com.cndfactory.shoppingmall.domain.dto.shop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class ShopResponseDtoTest {

	Validator validator;

	@BeforeEach
	void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@DisplayName("매장코드_사이즈_테스트")
	@Test
	void Test_Successful_LengthValid() {
		ShopModifyDto dto = new ShopModifyDto("테스트매장", "SP000001", "Y");

		Set<ConstraintViolation<ShopModifyDto>> violations = validator.validate(dto);
		violations.forEach(violation -> System.out.println(violation.getPropertyPath()+": "+violation.getMessage()));

		assertThat(violations.size()).isZero();
	}

}