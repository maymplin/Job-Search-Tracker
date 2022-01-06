package org.launchcode.jobsearchtracker.controllers;

import org.launchcode.jobsearchtracker.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;

//@Configuration
@Controller
public class AppController {

    @Autowired
    private UserRepository userRepository;

//    // URL: http://localhost:8080/login/oauth2/code/google
    @GetMapping
    public String displayRegistrationForm(Model model) {
        return "index";
    }

    @GetMapping("index")
    public String indexPage(Model model) {
        return "index";
    }

    @GetMapping("test")
    public String testPage(Model model) {
        model.addAttribute("user", "HI! YOU ARE LOGIN!");
        return "test";
    }

    @GetMapping("login")
    public String displayLoginForm() {
        return "login";
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/logout";
    }


//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/login").setViewName("login");
//    }
}
