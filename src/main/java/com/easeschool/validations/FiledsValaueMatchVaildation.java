package com.easeschool.validations;

import com.easeschool.annotation.FiledsValaueMatch;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class FiledsValaueMatchVaildation implements ConstraintValidator<FiledsValaueMatch, Object> {

    String field1Name;
    String field2Name;
    @Override
    public void initialize(FiledsValaueMatch constraintAnnotation) {
               field1Name = constraintAnnotation.field();
               field2Name = constraintAnnotation.field2();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object value1 = new BeanWrapperImpl(value).getPropertyValue(field1Name);
        Object value2 = new BeanWrapperImpl(value).getPropertyValue(field2Name);

        if(value1.equals(value2)){
            return true;
        }


        return false;
    }
}
