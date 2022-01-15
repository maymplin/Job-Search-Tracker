package org.launchcode.jobsearchtracker.controllers;

import org.launchcode.jobsearchtracker.data.JobListingDetailsRepository;
import org.launchcode.jobsearchtracker.data.JobListingRepository;
import org.launchcode.jobsearchtracker.data.UserRepository;
import org.launchcode.jobsearchtracker.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
//@RequestMapping("dashboard")
public class DashboardController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobListingRepository jobListingRepository;

    @Autowired
    private JobListingDetailsRepository jobListingDetailsRepository;


    @GetMapping("dashboard")
    public String displayAllJobs (Principal principal, Model model) {

//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username = principal.getName();

//        String username;
//        if (principal instanceof UserDetails) {
//            username = ((UserDetails) principal).getUsername();
//        } else {
//            username = principal.toString();
//        }

        User user = userRepository.findByUsername(username);

        model.addAttribute("jobs", user.getJobListings());
        return "/dashboard";
    }
}
