package com.luv2code.springbootdemo.mvc.validation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String>
{
    private String coursePrefix;


    @Override
    public void initialize(CourseCode theCourseCode) {
        coursePrefix=theCourseCode.value();
    }

    @Override
    public boolean isValid(String theCode, ConstraintValidatorContext theConstraintValidatorContext) {
        boolean result = theCode.startsWith(coursePrefix);

        if (theCode!=null)
        {
            result=theCode.startsWith(coursePrefix);
        }
        else
        {
            result=true;
        }
        return result;
    }
}
