package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController
{

    private List<Student> theStudents;

    //define @PostConstruct to load the student data.. only once
    @PostConstruct
    public void loadData()
    {
        theStudents = new ArrayList<>();
        theStudents.add(new Student("Deepak","Singh"));
        theStudents.add(new Student("Alex","Carry"));
    }

    //define endpoints for students
    @GetMapping("/students")
    public List<Student> getStudents()
    {
        return theStudents;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId)
    {
        //check the studentId again list size
        if((studentId < 0 || studentId >= theStudents.size()))
        {
            throw new StudentNotFoundException("Student Not Found -> "+studentId);
        }
        return theStudents.get(studentId);
    }


}
