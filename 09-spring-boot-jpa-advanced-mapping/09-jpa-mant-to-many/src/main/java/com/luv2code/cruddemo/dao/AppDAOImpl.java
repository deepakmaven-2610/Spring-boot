package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO
{
    //define field for entity manager
    private EntityManager entityManager;
    //inject entity manager using constructor injection
    @Autowired
    public AppDAOImpl(EntityManager theEntityManager)
    {
        this.entityManager=theEntityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor)
    {
        entityManager.persist(theInstructor);

    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class,theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId)
    {
        //retrieve the Instructor
        Instructor tempInstructor = entityManager.find(Instructor.class,theId);

        //get courses
        List<Course> courses = tempInstructor.getCourses();

        //break association of all courses for the instructor
        for (Course tempCourse : courses)
        {
            tempCourse.setInstructor(null);
        }
        //delete the ID
        entityManager.remove(tempInstructor);

    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class,theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId)
    {
        //retrieve the instructor
        InstructorDetail tempInstructorDetail = entityManager.find(InstructorDetail.class,theId);

        //remove the associated object reference
        //break bi-directional link
        tempInstructorDetail.getInstructor().setInstructorDetail(null);

        //delete the ID
        entityManager.remove(tempInstructorDetail);

    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
        //create query
        TypedQuery<Course> query=entityManager.createQuery(
                                "from Course where instructor.id = :data",Course.class);
        query.setParameter("data",theId);
        //execute query
        List<Course> courses=query.getResultList();
        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        //create query
        TypedQuery<Instructor> query = entityManager.createQuery(
                                                     "select i from Instructor i "
                                                        + "JOIN FETCH i.courses "
                                                        + "JOIN FETCH i.instructorDetail "
                                                        + "where i.id = :data", Instructor.class);

        query.setParameter("data",theId);

        //execute the query
        Instructor instructor = query.getSingleResult();
        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor tempInstructor)
    {
        entityManager.merge(tempInstructor);
    }

    @Override
    @Transactional
    public void update(Course tempCourse)
    {
        entityManager.merge(tempCourse);

    }

    @Override
    public Course findCourseById(int theId) {
        return entityManager.find(Course.class,theId);
    }

    @Override
    @Transactional
    public void deleteCourse(int theId)
    {
        //retrieve the Course
        Course tempCourse = entityManager.find(Course.class,theId);

        //remove the course
        entityManager.remove(tempCourse);
    }

    @Override
    @Transactional
    public void saveCourse(Course theCourse)
    {
        entityManager.persist(theCourse);

    }
    @Override
    @Transactional
    public void save(Course tempCourse) {
        entityManager.persist(tempCourse);
    }

    @Override
    public Course findCourseAndReviewById(int theId) {
        //create query
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
                +"JOIN FETCH c.reviews "
                +"where c.id = :data", Course.class);
        query.setParameter("data",theId);
        //execute query
        Course course = query.getSingleResult();
        return course;
    }

    @Override
    public Course findCourseAndStudentsByCourseId(int theId) {
        //create query
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
                        +"JOIN FETCH c.students "
                        +"where c.id = :data", Course.class);
        query.setParameter("data",theId);

        //execute query
        Course course = query.getSingleResult();
        return course;
    }

    @Override
    public Student findCStudentAndCourseByStudentId(int theId) {
        //create query
        TypedQuery<Student> query = entityManager.createQuery(
                "select s from Student s "
                        +"JOIN FETCH s.courses "
                        +"where s.id = :data", Student.class);
        query.setParameter("data",theId);

        //execute query
        Student student = query.getSingleResult();
        return student;
    }

    @Override
    @Transactional
    public void update(Student tempStudent) {
        entityManager.merge(tempStudent);
    }

    @Override
    @Transactional
    public void deleteStudentById(int theId)
    {
        //retrieve the Student
        Student tempStudent = entityManager.find(Student.class,theId);

        //delete the Student
        entityManager.remove(tempStudent);
    }
}
