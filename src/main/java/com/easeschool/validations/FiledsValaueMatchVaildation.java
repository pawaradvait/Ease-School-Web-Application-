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
    private static int counter =0;

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object value1 = new BeanWrapperImpl(value).getPropertyValue(field1Name);
        Object value2 = new BeanWrapperImpl(value).getPropertyValue(field2Name);

if(field1Name.equals("pwd")){
    counter++;
}

         if(value1 !=null){
             if( counter == 2 && value1.toString().startsWith("$2a")){
                 counter = 0;
                 return true;
             }else{
                 return value1.equals(value2);
             }
         }

        return false;
    }
}
