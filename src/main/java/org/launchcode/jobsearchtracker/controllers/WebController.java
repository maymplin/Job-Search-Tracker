package org.launchcode.jobsearchtracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class WebController {

    @GetMapping
    public String displayRoot() {
        return "redirect:/dashboard";
    }

    @GetMapping("/login")
    public String displayLoginPage() {
        return "login";
    }

//    @GetMapping("/dashboard")
//    public String displayDashboard() {
//        return "dashboard";
//    }



//    @RequestMapping("/dashboard")
//    public String displayDashboard(Model model, Principal principal) {
//        return "dashboard";
//    }

//    @RequestMapping("/login")
//    public String displayLoginPage(Model model, Principal principal) {
//        return "index";
//    }
}

