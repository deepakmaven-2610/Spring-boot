package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect
{
    //this is where we add all of our related advices for logging

    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
    private void forDaoPackage()
    {
    }
    //create pointcut for getter method
    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.get*(..))")
    private void getter(){}

    //creating pointcut for setter methods
    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.set*(..))")
    private void setter(){}

    //create pointcut: include package.... exclude getter/setter
    @Pointcut("forDaoPackage() && ! (getter() || setter())")
    private void forDAOPackageNoGetterSetter()
    {
    }

    @Before("forDAOPackageNoGetterSetter()")
    public void beforeAddAccountAdvice()
    {
        System.out.println("\n=======>>> Executing @Before advice on AddAccountAdvice ");
    }
    @Before("forDAOPackageNoGetterSetter()")
    public void performApiAnalytics()
    {
        System.out.println("\n=======>>> Performing API Analytics ");
    }

}
