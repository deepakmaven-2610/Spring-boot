package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudnetDAOImpl implements StudentDAO
{
    //define feild for entity manager
    private EntityManager entityManager;

    //inject entity manager using constructor injection

    @Autowired
    public StudnetDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //implement save method
    @Override
    @Transactional
    public void save(Student theStudent)
    {
        entityManager.persist(theStudent);
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class,id);
    }

    @Override
    public List<Student> findAll()
    {
        //create query
        TypedQuery<Student> theQuery = entityManager.createQuery("From Student", Student.class); //here From Student, Student is not database name it is entity name

        //return query result
        return theQuery.getResultList();

    }

    @Override
    public List<Student> findByLastName(String theLastName) {
        //create query
        TypedQuery<Student> theQuery = entityManager.createQuery("From Student where lastName=:theData",Student.class);

        //set query parameter
        theQuery.setParameter("theData",theLastName);

        //return query result
        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Student theStudent)
    {
        entityManager.merge(theStudent);
    }
    @Override
    @Transactional
    public void delete(Integer id)
    {
        //retrieve student
        Student theStudent = entityManager.find(Student.class,id);
        //delete student
        entityManager.remove(theStudent);
    }

    @Override
    @Transactional
    public int deleteAll()
    {   int numRowsDeleted = entityManager.createQuery("Delete from Student").executeUpdate();
        return numRowsDeleted;
    }
}
