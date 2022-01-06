package org.launchcode.jobsearchtracker;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// https://yatheesanc9.medium.com/spring-boot-with-google-sign-in-8e304dbe936e
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .antMatcher("/**").authorizeRequests()
                .antMatchers("/", "/index.html").authenticated()
                .anyRequest().authenticated()
                .and()
                .oauth2Login().permitAll()
                .defaultSuccessUrl("/index", true)
                .and()
                .logout()
                .logoutUrl("/logout").permitAll()
                .logoutSuccessUrl("/");
    }
}
