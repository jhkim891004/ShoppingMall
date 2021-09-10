package com.cndfactory.shoppingmall.domain.dto.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberUpdateDtoTest {

	Validator validator;

	@BeforeEach
	void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@DisplayName("멤버_변수_테스트_성공")
	@Test
	void memberUpdateDto_Success_Setting() {
		MemberUpdateDto dto = new MemberUpdateDto(1L, "tester", "cndfactory@)21");

		Set<ConstraintViolation<MemberUpdateDto>> violations = validator.validate(dto);
		violations.forEach(violation -> System.out.println(violation.getPropertyPath()+": "+violation.getMessage()));

		assertThat(violations.size()).isZero();
	}

	@DisplayName("id_검증_테스트")
	@Test
	void id_Fail_Validation() {
		/**
		 * 테스트 케이스
		 * null: pass
		 * 0: pass
		 */
		MemberUpdateDto dto = new MemberUpdateDto(1L, "tester", "cndfactory@)21");

		Set<ConstraintViolation<MemberUpdateDto>> violations = validator.validate(dto);
		violations.forEach(violation -> System.out.println(violation.getPropertyPath()+": "+violation.getMessage()));

		assertThat(violations.size()).isNotZero();
	}

	@DisplayName("memberId_검증_테스트")
	@Test
	void memberId_Fail_Validation() {
		/**
		 * 테스트 케이스
		 * null: pass
		 * empty: pass
		 * whitespace: pass
		 * test: pass
		 * 12test: pass
		 * @test: pass
		 */
		MemberUpdateDto dto = new MemberUpdateDto(1L, "@test", "cndfactory@)21");

		Set<ConstraintViolation<MemberUpdateDto>> violations = validator.validate(dto);
		violations.forEach(violation -> System.out.println(violation.getPropertyPath()+": "+violation.getMessage()));

		assertThat(violations.size()).isNotZero();
	}

	@DisplayName("password_검증_테스트")
	@Test
	void password_Fail_Validation() {
		/**
		 * 테스트 케이스
		 * null: pass
		 * empty: pass
		 * whitespace: pass
		 * cndfactory21: pass
		 * 20210903@): pass
		 * cndfactory@): pass
		 */
		MemberUpdateDto dto = new MemberUpdateDto(1L, "cndfactory", "cndfactory@)");

		Set<ConstraintViolation<MemberUpdateDto>> violations = validator.validate(dto);
		violations.forEach(violation -> System.out.println(violation.getPropertyPath()+": "+violation.getMessage()));

		assertThat(violations.size()).isNotZero();
	}
}