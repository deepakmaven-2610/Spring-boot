package com.luv2code.springbootdemo.demosecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig
{
    //jdbc support
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource)
    {
        return new JdbcUserDetailsManager(dataSource);
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http.authorizeHttpRequests(configurer ->
                                      configurer
                                              .requestMatchers("/").permitAll()
                                              .requestMatchers("/leaders/**").hasRole("MANAGER")
                                              .requestMatchers("/systems/**").hasRole("ADMIN")
                                              .anyRequest().authenticated()
        )
                .formLogin(form ->
                           form
                                   .loginPage("/showMyLoginPage")
                                   .loginProcessingUrl("/authenticateTheUser")
                                   .permitAll()
                )
                .logout(logout -> logout.permitAll()
                )
                .exceptionHandling(configurer ->
                                                  configurer.accessDeniedPage("/access-denied")
                );
        return http.build();
    }

}
