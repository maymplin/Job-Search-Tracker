package org.launchcode.jobsearchtracker.controllers;

import org.launchcode.jobsearchtracker.data.JobListingDetailsRepository;
import org.launchcode.jobsearchtracker.data.JobListingRepository;
import org.launchcode.jobsearchtracker.data.UserRepository;
import org.launchcode.jobsearchtracker.models.JobListing;
import org.launchcode.jobsearchtracker.models.JobListingDetails;
import org.launchcode.jobsearchtracker.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

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
//    @GetMapping
    public String displayAllJobs (Principal principal, Model model) {

        String username = principal.getName();

        User user = userRepository.findByUsername(username);

        if (user.getJobListings() != null) {
            model.addAttribute("jobs", user.getJobListings());
        }

        return "/dashboard";
    }

    @GetMapping("/{id}")
    public String displayJobListing(@PathVariable String id, Model model) {
        int jobListingId = Integer.parseInt(id);

        Optional<JobListing> result = jobListingRepository.findById(jobListingId);

        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Job Listing ID: " + jobListingId);
        } else {
            JobListing jobListing = result.get();
            JobListingDetails jobListingDetails = jobListing.getJobListingDetails();
            model.addAttribute("title", jobListingDetails.getCompany() + ": "
                    + jobListing.getJobTitle());
            model.addAttribute("listing", jobListing);
            model.addAttribute("details", jobListingDetails);
        }


        return "jobs/jobListing";
    }
}
