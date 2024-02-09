package com.ddingmate.ddingmate.util.valid;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Constraint(validatedBy = YearMonthValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
public @interface YearMonth {
    String message() default "yyyyMM 형식에 맞지 않습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String pattern() default "yyyyMMdd";
}
