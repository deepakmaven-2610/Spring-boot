package com.luv2code.aopdemo;

import com.luv2code.aopdemo.dao.AccountDao;
import com.luv2code.aopdemo.dao.MembershipDao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountDao theAccountDao, MembershipDao theMembershipDao)
	{
		return runner -> {
			//demoTheBeforeAdvice(theAccountDao,theMembershipDao);
			//demoTheAfterAdvice(theAccountDao);
			demoTheAfterThrowingAdvice(theAccountDao);
		};
	}

	private void demoTheAfterThrowingAdvice(AccountDao theAccountDao)
	{
		//call method to find the Account
		List<Account> theAccounts = null;

		try {
			//add a boolean flag to stimulate the exception
			boolean tripWire = true;

			theAccounts=theAccountDao.findAccount(tripWire);
		}
		catch (Exception ex)
		{
			System.out.println("/n/n Main program catch exception" + ex);
		}


		//display the accounts
		System.out.println("\n\n Main Program : demoTheAfterReturningAdvice");
		System.out.println("----");

		System.out.println(theAccounts);
		System.out.println("\n");
	}

	private void demoTheAfterAdvice(AccountDao theAccountDao)
	{
		//call method to find the Account
		List<Account> theAccounts = theAccountDao.findAccount();

		//display the accounts
		System.out.println("\n\n Main Program : demoTheAfterReturningAdvice");
		System.out.println("----");

		System.out.println(theAccounts);
		System.out.println("\n");
	}

	private void demoTheBeforeAdvice(AccountDao theAccountDao, MembershipDao theMembershipDao)
	{
		//call the business method
		Account myAccount = new Account();
		myAccount.setName("Madhu");
		myAccount.setLevel("Platinum");
		theAccountDao.addAccount(myAccount,true);
		theAccountDao.doWork();

		//call the accountdao getter/setter method
		theAccountDao.setName("foobar");
		theAccountDao.setServiceCode("silver");

		String name = theAccountDao.getName();
		String code = theAccountDao.getServiceCode();

		theMembershipDao.addAccount();
		theMembershipDao.goToSleep();;
	}

}
