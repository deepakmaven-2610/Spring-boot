package com.luv2code.springboot.demo.mycoolapp.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController
{

    //injecting properties for coach.name and coach.value
    @Value("${coach.name}")
    private String coachName;

    @Value("${team.name}")
    private String teamName;

    //exposing new endpoints for this
    @GetMapping("/teaminfo")
    public String teamInfo()
    {
        return "Coach:-> " +coachName+ "Team:->" +teamName;
    }
 @GetMapping("/")
 public String SayHello()
 {
     return "Hello World";
 }
    @GetMapping("/greet")
 public String greet()
 {
     return "How are you";
 }
}
