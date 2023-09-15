package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource(path="members") //spring data api instead exposing employees it wil expose members


public interface EmployeeRepository extends JpaRepository<Employee,Integer>
{

    //no need to write code over here
}
