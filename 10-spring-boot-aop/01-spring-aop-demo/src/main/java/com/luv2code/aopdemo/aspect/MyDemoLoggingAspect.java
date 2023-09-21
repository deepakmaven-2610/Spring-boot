package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect
{
    //this is where we add all of our related advices for logging

    //start with @Before advice
    //@Before("execution(public void addAccount())") // match with any class
    @Before("execution(public void com.luv2code.aopdemo.dao.AccountDao.addAccount())")
    public void beforeAddAccountAdvice()
    {
        System.out.println("\n=======>>> Executing @Before advice on AddAccountAdvice ");
    }

}
