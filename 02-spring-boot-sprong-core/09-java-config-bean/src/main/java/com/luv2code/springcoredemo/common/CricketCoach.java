package com.luv2code.springcoredemo.common;

import com.luv2code.springcoredemo.common.Coach;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component

public class CricketCoach implements Coach {
    public CricketCoach()
    {
        System.out.println("In Constructor: "+getClass().getSimpleName());
    }


    @Override
    public String getDailyWorkout() {
        return "Fast bowling practice for 15 min!!";
    }
}
