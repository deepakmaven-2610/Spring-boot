package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO
{
    //define field for entity manager
    private EntityManager entityManager;

    //set up constructor injection
    @Autowired
    public EmployeeDAOJpaImpl(EntityManager theEntityManager)
    {
        entityManager = theEntityManager;
    }

    @Override
    public List<Employee> findAll() {
        //create query
        TypedQuery<Employee> theQuery = entityManager.createQuery("From Employee", Employee.class);

        //execute query and get result
        List<Employee> employees = theQuery.getResultList();

        //return result
        return employees;
    }

    @Override
    public Employee findById(int theId) {
        //get Employee
        Employee theEmployee = entityManager.find(Employee.class,theId);

        //return Employee
        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {
        //Save/Update Employee
        Employee dbEmployee = entityManager.merge(theEmployee);
        //return the dbEmployee
        return dbEmployee;
    }

    @Override
    public void deleteByID(int theId)
    {
        //find employee by id
        Employee theEmployee = entityManager.find(Employee.class,theId);
        //delete employee
        entityManager.remove(theEmployee);

    }
}
