package com.codezero.BookRental;

import com.codezero.BookRental.dto.BookRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


public class BookRequestValidationTest {
    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void 제목과_저자_출판일이_누락되면_에러메시지가_반환된다() {
        BookRequest bookRequest = new BookRequest("","",null);
        Set<ConstraintViolation<BookRequest>> violations = validator.validate(bookRequest);

        assertThat(violations).hasSize(3);
        assertThat(violations).anyMatch(v->v.getMessage().equals("제목은 필수입니다."));
        assertThat(violations).anyMatch(v->v.getMessage().equals("저자는 필수입니다."));
        assertThat(violations).anyMatch(v->v.getMessage().equals("출판일은 필수입니다."));
    }
}
