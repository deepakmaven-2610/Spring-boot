package com.luv2code.cruddemo;

import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import com.luv2code.cruddemo.entity.dao.AppDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
			//createInstructor(appDAO);
			//findInstructor(appDAO);
			deleteInstructor(appDAO);
		};
	}

	private void deleteInstructor(AppDAO appDAO)
	{
		int theId=1;
		System.out.println("Deeleting Instructot with id: "+theId);
		appDAO.deleteInstructorById(theId);
		System.out.println("Done!!");
	}

	private void findInstructor(AppDAO appDAO)
	{
		int theId=1;
		System.out.println("Instructor with id :-> " +theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println(("tempInstructor: " +tempInstructor));
		System.out.println("the associated instructorDetail only: " +tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO)
	{
		//create the Instructor
		Instructor tempInstructor = new Instructor("Chad","Darby","darby@luv2code.com");

		//create the Instructor detail
		InstructorDetail tempInstucctorDetail= new InstructorDetail("http//luv2code.com","Luv 2 Code!!");

		//associate the objects
		tempInstructor.setInstructorDetail(tempInstucctorDetail);

		//save the instructor
		System.out.println("Saviing the Instructor"+tempInstructor);
		appDAO.save(tempInstructor);
		System.out.println("Done!");

	}

}
