package com.easeschool.annotation;

import com.easeschool.validations.FiledsValaueMatchVaildation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Email;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {FiledsValaueMatchVaildation.class})
public @interface FiledsValaueMatch {

    String message() default " not mataching";
    String field();
    String field2();



    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Target({TYPE })
    @Retention(RUNTIME)
    public @interface List {
        FiledsValaueMatch[] value();
    }

}
