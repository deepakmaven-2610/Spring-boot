package com.luv2code.springcoredemo.common;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class BaseBallCoach implements Coach
{

    @Override
    public String getDailyWorkout() {
        return "Spend 30 minutes in baseball court";
    }
}
