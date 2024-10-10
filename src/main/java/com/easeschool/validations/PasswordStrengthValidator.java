package com.easeschool.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;


public class PasswordStrengthValidator implements ConstraintValidator<com.easeschool.annotation.PasswordStrengthValidator,String> {

    List<String> passwords;


    @Override
    public void initialize(com.easeschool.annotation.PasswordStrengthValidator constraintAnnotation) {
        passwords = Arrays.asList("12345", "password", "qwerty");
    }


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {


        return !passwords.contains(value);
    }
}
