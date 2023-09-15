package com.luv2code.springboot.thymeleafdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController
{
    //create mapping for /hello

    @GetMapping("/hello")
    public String sayHello(Model theModel)
    {
        theModel.addAttribute("theData",new java.util.Date());
        return "helloworld"; //thymeleaf will automatically look for this file in template
    }
}
