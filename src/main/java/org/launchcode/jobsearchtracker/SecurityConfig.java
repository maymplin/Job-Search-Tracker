package org.launchcode.jobsearchtracker;

import org.launchcode.jobsearchtracker.models.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                    .antMatchers("/resources/static/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                    .antMatchers("/", "/login").permitAll()
                    // allows anyone to access a URL that begins with "/resources"
                    // since this is where my CSS, JavaScript and images are stored
                    // all my static resources are viewable by anyone
//                    .antMatchers("../static/**").permitAll()
                .anyRequest().authenticated()   // every request requires the user to be authenticated
                .and()
                .formLogin()    // form based authentication is supported
                    .permitAll()
                    .loginPage("/login")     // when authentication is required, redirect the browswer to "/login"
                    .loginProcessingUrl("/login")
//                    .defaultSuccessUrl("/dashboard.html", true)
                    .successHandler(
                            new AuthenticationSuccessHandler() {
                                @Override
                                public void onAuthenticationSuccess(HttpServletRequest request,
                                                                    HttpServletResponse response,
                                                                    Authentication authentication)
                                        throws IOException, ServletException {

                                    response.sendRedirect("/dashboard");
                                }
                            }

                    )
                .and()
                .logout().permitAll()
                .logoutSuccessUrl("/login")
                .and()
                .exceptionHandling().accessDeniedPage("/403");
    }



    //    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web
//                .ignoring()
//                .antMatchers("../static/**");
//    }

//    // okta tutorial
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .csrf().disable()
//                .antMatcher("/**")
//                .authorizeRequests()
//                .antMatchers("/", "/login.html","/login**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .oauth2Login();
//    }
}
