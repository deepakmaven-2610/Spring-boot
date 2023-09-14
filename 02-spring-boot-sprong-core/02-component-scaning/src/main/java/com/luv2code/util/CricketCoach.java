package com.luv2code.util;

import com.luv2code.util.Coach;
import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach {
    @Override
    public String getDailyWorkout() {
        return "Fast bowling practice for 15 min!!";
    }
}
