package com.easeschool.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = com.easeschool.validations.PasswordStrengthValidator.class)
public @interface PasswordStrengthValidator {


    String message() default " password is week...";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };


}
