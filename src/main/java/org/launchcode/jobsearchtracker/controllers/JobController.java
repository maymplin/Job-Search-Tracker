package org.launchcode.jobsearchtracker.controllers;

import org.launchcode.jobsearchtracker.data.JobListingDetailsRepository;
import org.launchcode.jobsearchtracker.data.JobListingRepository;
import org.launchcode.jobsearchtracker.data.UserRepository;
import org.launchcode.jobsearchtracker.models.JobListing;
import org.launchcode.jobsearchtracker.models.JobListingDetails;
import org.launchcode.jobsearchtracker.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.oauth2.core.DefaultOAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;
import java.util.logging.Logger;

@Controller
@RequestMapping("jobs")
public class JobController {

    @Autowired
    private JobListingRepository jobListingRepository;

    @Autowired
    private JobListingDetailsRepository joblistingDetailsRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("add")
    private String displayAddJobListingForm(
//            Authentication authentication,
            Principal principal,
            Model model) {

        String username = principal.getName();
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        Object principal = auth.getPrincipal();


//        SecurityContextImpl context = exchange.getSession()

//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        model.addAttribute("title", "Add a New Job Listing");
        model.addAttribute("username", username);
//        model.addAttribute("jobListing", new JobListing());

        return "jobs/add";
    }

    @PostMapping("add")
    private String processAddJobListing(
            String username,
//            User user,
//                                        Principal principal,
                                        String jobTitle, String company) {

//        String username = principal.getName();
        User user = userRepository.findByUsername(username);

        JobListingDetails newJobListingDetails = new JobListingDetails();
        joblistingDetailsRepository.save(newJobListingDetails);

        JobListing newJobListing = new JobListing(
                jobTitle, company,
                newJobListingDetails,
                user);

        jobListingRepository.save(newJobListing);
        user.addJobListing(newJobListing);

        return "redirect:/dashboard";
    }

}