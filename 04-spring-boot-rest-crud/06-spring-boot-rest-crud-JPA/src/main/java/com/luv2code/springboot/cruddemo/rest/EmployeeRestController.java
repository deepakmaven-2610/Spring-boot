package com.luv2code.springboot.cruddemo.rest;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController
{
    private EmployeeService employeeService;
    //inject employee dao
    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService)

    {
        employeeService = theEmployeeService;
    }

    //expose "/employees" and return the whole list of employees by using constructor injection
    @GetMapping("/employees")
    public List<Employee> findAll()
    {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId)
    {
        Employee theEmployee = employeeService.findById(employeeId);
        if(theEmployee == null)
        {
            throw new RuntimeException("Employee id not found -> " +employeeId);
        }
        return theEmployee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee)
    {
        //if they pass an id in json ... set id to 0
        //this is to create a new id instead of update

        theEmployee.setId(0);
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee)
    {
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId)
    {
        Employee tempEmployee = employeeService.findById(employeeId);
        //throw exception if null
        if(tempEmployee == null)
        {
            throw new RuntimeException("Employee id not found");
        }
        employeeService.deleteById(employeeId);
        return "Deleted id -> " +employeeId;
    }



}
