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
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

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
            Principal principal,
            Model model) {
//        System.out.println("JobController: Inside displayAddJobListingForm()");
        String username = principal.getName();
        System.out.println("JobController: Inside displayAddJobListingForm() - username = "+username);
//        User user = userRepository.findByUsername(username);

        model.addAttribute("title", "Add a New Job Listing");
//        model.addAttribute("jobListing", new JobListing());
        model.addAttribute("username", username);
//        if (result.isEmpty()) {
//            model.addAttribute("title", "Invalid user ID");
//        } else {
//            User user = result.get();
//            model.addAttribute("title", "Add a New Job Listing");
//            model.addAttribute("jobListing", new JobListing());
//            model.addAttribute("user", user);
//            }
        return "jobs/add";
    }

//    @PostMapping("add")
//    private String processAddJobListingForm(String username,
//            String jobTitle,
//            String company,
//            String listingUrl,
//            String listingNumber,
//            String jobDescription) {
//
//            User user = userRepository.findByUsername(username);
//            JobListing newJobListing = new JobListing(jobTitle, company, user);
//            jobListingRepository.save(newJobListing);
//            JobListingDetails newJobListingDetails = new JobListingDetails(
//            newJobListing, listingUrl, listingNumber, jobDescription);
//            joblistingDetailsRepository.save(newJobListingDetails);
//
//            return "../dashboard";
//    }

    @PostMapping("add")
    private String processAddJobListing(
            String username,
//            User user,
//                                        Principal principal,
                                        String jobTitle, String company) {
        System.out.println("JobController: Inside processAddJobListing()");

//        String username = principal.getName();
        User user = userRepository.findByUsername(username);

        JobListingDetails newJobListingDetails = new JobListingDetails();
        joblistingDetailsRepository.save(newJobListingDetails);

        JobListing newJobListing = new JobListing(
                jobTitle, company,
                newJobListingDetails,
                user);
        System.out.println(newJobListing);
        jobListingRepository.save(newJobListing);


        return "redirect:/dashboard";
    }}
