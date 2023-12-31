package com.luv2code.springbootdemo.mvc.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CourseCodeConstraintValidator.class)
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseCode
{
    //define default course code
    public String value() default "LUV";

    //define default error message
    public String message() default "Must Start with LUV";

    //define groups
    public Class<?>[] groups() default {};

    //define payloads
    public Class<? extends Payload>[] payload() default {}; //provide custom detail about validation failure

}
