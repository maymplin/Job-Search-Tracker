package org.launchcode.jobsearchtracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping
    public String displayHomePage(Model model){
        model.addAttribute("title", "Homepage");
        return "index";
    }

    @GetMapping("index")
    public String displayIndex(Model model){
        model.addAttribute("title", "Homepage");
        return "index";
    }

    @GetMapping("dashboard")
    public String displayDashboard(Model model){
        model.addAttribute("title", "Dashboard");
        return "dashboard";
    }
}
