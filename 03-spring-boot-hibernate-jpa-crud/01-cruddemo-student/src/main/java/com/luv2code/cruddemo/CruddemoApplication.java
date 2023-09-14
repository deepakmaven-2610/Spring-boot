package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO)
	{
		return runner ->{
			//createStudent(studentDAO);
			createmultipleStudent(studentDAO);
			//readStudent(studentDAO);

			//queryForStudent(studentDAO);
			//queryForStudentsByLastName(studentDAO);

			//updateStudent(studentDAO);

			//deleteStudent(studentDAO);
			//deleteAll(studentDAO);
		};
	}

	private void deleteAll(StudentDAO studentDAO)
	{
		System.out.println("Deleting All Students");
		int numrowsdeleted = studentDAO.deleteAll();
		System.out.println("Total Rows Deleted :> "+numrowsdeleted);
	}

	private void deleteStudent(StudentDAO studentDAO)
	{
		int studentId = 3;
		System.out.println("Deleting Student with ID :->"+studentId);
		studentDAO.delete(3);
	}

	private void updateStudent(StudentDAO studentDAO)
	{
		//retrieve student based on the id: pk
		int studentId=1;
		System.out.println("Getting Student with Id:-> " +studentId);
		Student myStudent=studentDAO.findById(studentId);

		//change first name to Scooby
		System.out.println("updating student....");
		myStudent.setFirstName("Scooby");

		//update student
		studentDAO.update(myStudent);

		//display updated student
		System.out.println("Updated Student :->" +myStudent);
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO)
	{
		//get list of student
		List<Student> theStudents = studentDAO.findByLastName("Singh");

		//display list of student
		for(Student tempStudents:theStudents)
		{
			System.out.println(tempStudents);
		}
	}

	private void queryForStudent(StudentDAO studentDAO)
	{
		//get list of Student
		List<Student> theStudents = studentDAO.findAll();

		//display list of Student
		for (Student tempStudents:theStudents
			 )
		{
			System.out.println(tempStudents);
		}
	}

	private void readStudent(StudentDAO studentDAO)
	{
		//create student obj
		System.out.println("Creating Student object");
		Student tempStudent = new Student("jeff","beam","jeff@email.com");


		//save student obj
		System.out.println("Saving the student");
		studentDAO.save(tempStudent);

		//display id of save student
		int theid= tempStudent.getId();
		System.out.println("Saved student id, Generated id: "+theid);

		//retrieve student based on pk
		System.out.println("Find by id");
		Student mystudent = studentDAO.findById(theid);

		//display student
		System.out.println("student found "+mystudent);

	}

	private void createmultipleStudent(StudentDAO studentDAO)
	{
		//create student obj
		System.out.println("Creating Student object");
		Student tempStudent1 = new Student("Alex","Singh","alex@email.com");
		Student tempStudent2 = new Student("Burger","Cook","Burger@email.com");
		Student tempStudent3 = new Student("Carry","Jhonson","Carry@email.com");

		//save student obj
		System.out.println("Saving Student object");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);

	}

	private void createStudent(StudentDAO studentDAO)
	{
		//create student obj
		System.out.println("Creating Student object");
		Student tempStudent = new Student("Deepak","Singh","deepak@email.com");

		//save student obj
		System.out.println("Saving Student object");
		studentDAO.save(tempStudent);

		//display id of saved student obj
		System.out.println("Id of saved student -> " +tempStudent.getId());
	}

}
