package com.cndfactory.shoppingmall.domain.dto.member;

import com.cndfactory.shoppingmall.domain.entity.member.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class MemberSaveDtoTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
    @DisplayName("검증_성공")
    @Test
    void memberSaveDto_Success_Setting() {
        MemberSaveDto dto = new MemberSaveDto("tester", "cndfactory@)21", "ADMIN");

        Set<ConstraintViolation<MemberSaveDto>> violations = validator.validate(dto);
        violations.forEach(violation -> System.out.println(violation.getPropertyPath()+": "+violation.getMessage()));

        assertThat(violations.size()).isZero();

    }

    @DisplayName("memberId_검증_테스트")
    @Test
    void memberId_error_validCheck() {
        /**
         * 테스트 케이스
         * null: pass
         * empty: pass
         * whitespace: pass
         * test: pass
         * 12test: pass
         * @test: pass
         */
        MemberSaveDto dto = new MemberSaveDto("@test", "cndfactory@)21", "ADMIN");

        Set<ConstraintViolation<MemberSaveDto>> violations = validator.validate(dto);
        violations.forEach(violation -> System.out.println(violation.getPropertyPath()+": "+violation.getMessage()));

        assertThat(violations.size()).isNotZero();
    }
    
    @DisplayName("password_검증_테스트")
    @Test
    void password_error_validCheck() {
        /**
         * 테스트 케이스
         * null: pass
         * empty: pass
         * whitespace: pass
         * cndfactory21: pass
         * 20210903@): pass
         * cndfactory@): pass
         */
        // 테스트 케이스: null, 공백, whitespace, 자리수
        MemberSaveDto dto = new MemberSaveDto("tester", "cndfactory@)", "ADMIN");

        Set<ConstraintViolation<MemberSaveDto>> violations = validator.validate(dto);
        violations.forEach(violation -> System.out.println(violation.getPropertyPath()+": "+violation.getMessage()));

        assertThat(violations.size()).isNotZero();

    }

    @DisplayName("authority_검증_테스트")
    @Test
    void authority_error_validCheck() {
        /**
         * 테스트 케이스
         * null: pass
         * empty: pass
         * whitespae: pass
         */
        MemberSaveDto dto = new MemberSaveDto("tester", "cndfactory@)21", "   ");

        Set<ConstraintViolation<MemberSaveDto>> violations = validator.validate(dto);
        violations.forEach(violation -> System.out.println(violation.getPropertyPath()+": "+violation.getMessage()));

        assertThat(violations.size()).isNotZero();
    }
}