package com.luv2code.aopdemo.aspect;

import com.luv2code.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)


public class MyDemoLoggingAspect
{
    @After("execution(* com.luv2code.aopdemo.dao.AccountDao.findAccount(..))")
    public void afterFinallyFindAccountAdvice(JoinPoint thejoinPoint)
    {
        //print out which methods we are advising on
        String method = thejoinPoint.getSignature().toShortString();
        System.out.println("\n=======>>> Executing @AfterFinally on method :  "+method);
    }

    @AfterThrowing(
            pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDao.findAccount(..))",
            throwing = "theExc")
    public void afterThrowingFindAcountAdvice(
            JoinPoint thejoinPoint, Throwable theExc)
    {
        //print out which methods we are advising on
        String method = thejoinPoint.getSignature().toShortString();
        System.out.println("\n=======>>> Executing @AfterThrowing on method :  "+method);

        //log the exception
        System.out.println("\n=======>>> The exception is: " +theExc);
    }

    @AfterReturning(
            pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDao.findAccount(..))",
            returning = "result"
    )
public void afterReturningFindAccountsAdvice(JoinPoint thejoinPoint, List<Account> result)
    {
        //print out which method we are advising on
        String method = thejoinPoint.getSignature().toShortString();
        System.out.println("\n=======>>> Executing @After Returning on method :  "+method);

        //print out the results of method call
        System.out.println("\n=======>>> result :  "+result);

        //lets post-process the data ... lets modify it

        //convert the account names to uppercase
        convertAccountNamesToUpperCase(result);

    }

    private void convertAccountNamesToUpperCase(List<Account> result)
    {
        //loop through the account
        for (Account tempAccount : result)
        {
            //get uppercase version
            String theUpperCase = tempAccount.getName().toUpperCase();

            //update the name on the internet
            tempAccount.setName(theUpperCase);
        }

    }

    @Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDAOPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint)
    {
        System.out.println("\n=======>>> Executing @Before advice on AddAccountAdvice ");

        //display method signature
        MethodSignature methodSignature = (MethodSignature) theJoinPoint.getSignature();
        System.out.println("Method: "+methodSignature);

        //display method arguments

        //get args
        Object[] args = theJoinPoint.getArgs();

        //loop thru args
        for (Object tempArg : args) {
            System.out.println(tempArg);
            if (tempArg instanceof Account) {
                //downcast and print Account specific stuff
                Account theAccount = (Account) tempArg;
                System.out.println("account name : " + theAccount.getName());
                System.out.println("account level : " + theAccount.getLevel());
            }
        }

    }


}
