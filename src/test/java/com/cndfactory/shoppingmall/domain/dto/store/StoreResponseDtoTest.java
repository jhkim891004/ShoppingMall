package com.cndfactory.shoppingmall.domain.dto.store;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class StoreResponseDtoTest {

	Validator validator;

	@BeforeEach
	void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@DisplayName("매장코드_사이즈_테스트")
	@Test
	void Length_Successful_LengthValid() {
		StoreModifyDto dto = new StoreModifyDto("테스트매장", "SP000001", "Y");

		Set<ConstraintViolation<StoreModifyDto>> violations = validator.validate(dto);
		violations.forEach(violation -> System.out.println(violation.getPropertyPath()+": "+violation.getMessage()));

		assertThat(violations.size()).isZero();
	}

}