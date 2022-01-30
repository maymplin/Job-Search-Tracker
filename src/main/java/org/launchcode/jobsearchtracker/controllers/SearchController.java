package org.launchcode.jobsearchtracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping
    public String displaySearchForm(Model model) {
        
        model.addAttribute("title", "Search My Jobs");

        return "search";
    }

    @PostMapping("/results")
    public String processSearchForm(Model model) {
        model.addAttribute("title", "");

        return "list-jobs";
    }
}
