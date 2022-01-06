package org.launchcode.jobsearchtracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class WebController {

    @RequestMapping("/dashboard")
    public String displayDashboard(Model model, Principal principal) {
        return "dashboard";
    }

    @RequestMapping("/")
    public String displayLoginPage(Model model, Principal principal) {
        return "index";
    }
}
