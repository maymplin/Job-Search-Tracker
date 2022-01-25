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
    public String displayAddJobListingForm(
            Principal principal,
            Model model) {

        String username = principal.getName();

        model.addAttribute("title", "Add a New Job Listing");
        model.addAttribute("username", username);

        return "jobs/add";
    }

    @PostMapping("add")
    public String processAddJobListing(
            String username,
            String jobTitle,
            String company,
            String jobListingUrl,
            String jobListingNumber,
            String jobLocation,
            String jobType,
            String salary,
            String jobQualifications,
            String jobDescription
            ) {

        User user = userRepository.findByUsername(username);
//        System.out.println("user.getUsername() = "+ user.getUsername());

//        JobListingDetails newJobListingDetails = new JobListingDetails(company);

        JobListingDetails newJobListingDetails =
                new JobListingDetails(company, jobListingUrl, jobListingNumber,
                        jobLocation, jobType, salary, jobQualifications,
                        jobDescription);
        joblistingDetailsRepository.save(newJobListingDetails);
//        System.out.println("newJobListingDetails.getCompany() = "+ newJobListingDetails.getCompany());

        JobListing newJobListing = new JobListing(
                jobTitle,
                newJobListingDetails,
                user);
//        System.out.println("newJobListing.getUser().getUsername() = "+ newJobListing.getUser().getUsername());
        jobListingRepository.save(newJobListing);
//        System.out.println("newJobListing.getJobTitle() = "+ newJobListing.getJobTitle());
        user.addJobListing(newJobListing);

        return "redirect:../dashboard";
    }

    public String displayEditJobListingForm(Principal principal, Model model) {
        String username = principal.getName();

        model.addAttribute("title", "Add a New Job Listing");
        model.addAttribute("username", username);

        return "jobs/add";
    }

}