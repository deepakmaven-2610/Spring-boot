package com.luv2code.cruddemo;

import com.luv2code.cruddemo.entity.*;
import com.luv2code.cruddemo.dao.AppDAO;
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
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
		//	createCourseAndStudent(appDAO);
		//	findCoursesAndStudents(appDAO);
		//	findStudentAndCourses(appDAO);
		//	addMoreCoursesToStudent(appDAO);
		//	deleteCourse(appDAO);
			deleteStudent(appDAO);
		};
	}

	private void deleteStudent(AppDAO appDAO)
	{
		int theId = 1;
		System.out.println("Deleting the Student with ID " +theId);
		appDAO.deleteStudentById(theId);
		System.out.println("Done!!");
	}

	private void addMoreCoursesToStudent(AppDAO appDAO)
	{
		int theId=2;
		Student tempStudent = appDAO.findCStudentAndCourseByStudentId(theId);

		//create more courses
		Course tempCourse1 = new Course("Rubik Cube");
		Course tempCourse2 = new Course("Swimming");
		Course tempCourse3 = new Course("Game Development");

		//add courses to Student
		tempStudent.addCourse(tempCourse1);
		tempStudent.addCourse(tempCourse2);
		tempStudent.addCourse(tempCourse3);

		System.out.println("Updating the Student" +tempStudent);
		System.out.println("associated course " +tempStudent.getCourses());
		appDAO.update(tempStudent);
		System.out.println("Done!!");

	}

	private void findStudentAndCourses(AppDAO appDAO)
	{
		int theId=2;
		Student tempStudent = appDAO.findCStudentAndCourseByStudentId(theId);
		System.out.println("Loaded Student "+tempStudent);
		System.out.println("Courses " +tempStudent.getCourses());
		System.out.println("Done!!");
	}

	private void findCoursesAndStudents(AppDAO appDAO)
	{
		int theId=10;
		Course tempCourse = appDAO.findCourseAndStudentsByCourseId(theId);
		System.out.println("Loaded Course " +tempCourse);
		System.out.println("Students " + tempCourse.getStudents());
		System.out.println("Done!!");

	}

	private void createCourseAndStudent(AppDAO appDAO)
	{
		//create a course
		Course tempCourse = new Course("title:Pacman - How to score one million");

		//create a student
		Student tempStudent1 = new Student("Jhon","Joe","john@luv2code.com");
		Student tempStudent2 = new Student("Mary","Public","mary@luv2code.com");

		//add students to the course
		tempCourse.addStudent(tempStudent1);
		tempCourse.addStudent(tempStudent2);

		//save the course and associated student
		System.out.println("Saving the course : "+tempCourse);
		System.out.println("associated students: "+tempCourse.getStudents());
		appDAO.save(tempCourse);
		System.out.println("Done!!");
	}

	private void deleteCourseAndReview(AppDAO appDAO)
	{
		int theId = 10;
		System.out.println("deleting the course by id : " +theId);
		appDAO.deleteCourse(theId);
		System.out.println("Done");
	}

	private void retrieveCourseAndReview(AppDAO appDAO)
	{
		//get course and reviews
		int theId=10;
		Course tempCourse = appDAO.findCourseAndReviewById(theId);

		//print course
		System.out.println(tempCourse);

		//print reviews
		System.out.println(tempCourse.getReviews());
	}

	private void createCourseAndReview(AppDAO appDAO)
	{
		//create a course
		Course tempCourse = new Course("Pacman - how to score one million points");

		//add some reviews
		tempCourse.addReview(new Review("Very Nice Course"));
		tempCourse.addReview(new Review("Helped me to get one million scores"));
		tempCourse.addReview(new Review("Amzaing"));
		tempCourse.addReview(new Review("Loved It!!"));

		//save the course
		System.out.println("Saving the course");
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());

		appDAO.save(tempCourse);
		System.out.println("Done!!");
	}

	private void deleteCourse(AppDAO appDAO)
	{
		int theId=10;
		System.out.println("Deleing Course .... " +theId);
		appDAO.deleteCourse(theId);
		System.out.println("Done!!");
	}

	private void updateCourse(AppDAO appDAO)
	{
		int theId=10;

		//finding the course
		System.out.println("Finding Course Id :-> " +theId);
		Course tempCourse = appDAO.findCourseById(theId);

		//update the course
		System.out.println("Updating the course id : "+theId);
		tempCourse.setTitle("Enjoy Simple Things");
		appDAO.update(tempCourse);

		System.out.println("Done!!");
	}

	private void updateInstructot(AppDAO appDAO)
	{
		int theId=1;

		//finding the instructor
		System.out.println("Finding instructor id: "+theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);

		//update the instructor
		System.out.println("Updating the instructor id: "+theId);
		tempInstructor.setLastName("TESTER");

		appDAO.update(tempInstructor);

		System.out.println("Done!!");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO)
	{
		int theId=1;
		//find instructor
		System.out.println("Finding instructor with ID -> " +theId);
		Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);
		System.out.println("tempInstructor : "+tempInstructor);

		System.out.println("the associated course "+tempInstructor.getCourses());

		System.out.println("Done!!");
	}

	private void findCoursesForInstructor(AppDAO appDAO)
	{
		int theId=1;
		//find instructor
		System.out.println("Finding instructor with ID -> " +theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("tempInstructor : "+tempInstructor);

		//find courses for instructor
		System.out.println("Finding courses for Instructor with id :-) " +theId);
		List<Course> courses = appDAO.findCoursesByInstructorId(theId);

		//associate the courses
        tempInstructor.setCourses(courses);
		System.out.println("The associated courses are "+tempInstructor.getCourses());
	}

	private void findInstructorWithCourses(AppDAO appDAO)
	{
		int theId=1;
		System.out.println("Finding instructor with ID -> " +theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor: "+tempInstructor);
		System.out.println("the associated courses "+tempInstructor.getCourses());

		System.out.println("Done!!");
	}

	private void createInstructorWithCourse(AppDAO appDAO)
	{
		//create the Instructor
		Instructor tempInstructor = new Instructor("Susan","Public","susan@luv2code.com");

		//create the Instructor detail
		InstructorDetail tempInstructorDetail= new InstructorDetail("http//luv2code.com","Games");

		//associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		//creating some courses
		Course tempCourse1 = new Course("Air Guitar- the Ultimate Guide");
		Course tempCourse2 = new Course("The PinBall MasterClass");

		//add course to the instructor
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);

		//save the Instructor
		// But it will also save the Courses because we are using Cascade.Persist
		System.out.println("Saving the Instructor " +tempInstructor);
		System.out.println("The courses are " +tempInstructor.getCourses());

		appDAO.save(tempInstructor);

		System.out.println("Done!!");
	}

	private void deleteInstructorDetail(AppDAO appDAO)
	{
		int theId=3;
		System.out.println("Deleting the Instructor with id:- "+theId);

		appDAO.deleteInstructorDetailById(theId);
		System.out.println("Done!!");
	}

	private void findInstructorDetail(AppDAO appDAO)
	{
		//get the instructor detail object
		int theId=2;
		InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);

		//print the instructor detail
		System.out.println("Temp Instructor Detail " +tempInstructorDetail);

		//print the associated instructor
		System.out.println("Associated Instructor " +tempInstructorDetail.getInstructor());
		System.out.println("Done!!");
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
