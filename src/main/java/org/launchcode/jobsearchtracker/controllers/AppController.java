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

    @GetMapping
    public String displayRegistrationForm(Model model) {
//        model.addAttribute("title", "Homepage");
        return "login";
    }

    @GetMapping("index")
    public String indexPage(Model model) {
        model.addAttribute("title", "Homepage");
        return "index";
    }

    @GetMapping("dashboard")
    public String testPage(Model model) {
        model.addAttribute("title", "Dashboard");
        return "dashboard";
    }

//    @GetMapping("login")
//    public String displayLoginForm() {
//        return "login";
//    }

    @GetMapping("logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:";
    }

}
