package com.luv2code.springbootdemo.mvc;

import com.luv2code.springbootdemo.mvc.validation.CourseCode;
import jakarta.validation.constraints.*;
import org.apache.el.lang.EvaluationContext;

public class Customer
{
    private String firstName;

    @NotNull (message = "is required")
    @Size (min=1 , message = "is required")
    private String lastName="";

    @Min(value = 0, message = "value should be greater than 0")
    @Max(value = 10, message = "value should be less than 10" )
    private Integer freePasses;
    @Pattern(regexp = "^[a-zA-Z0-9]{5}",message = "only 4 char/digits are allowed")
    private String postalCode;

    @CourseCode
    private String courseCode;

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Integer getFreePasses() {
        return freePasses;
    }

    public void setFreePasses(Integer freePasses) {
        this.freePasses = freePasses;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
