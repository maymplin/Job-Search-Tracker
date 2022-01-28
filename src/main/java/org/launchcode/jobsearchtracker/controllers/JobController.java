package org.launchcode.jobsearchtracker.controllers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
@RequestMapping("jobs")
public class JobController {

    @Autowired
    private JobListingRepository jobListingRepository;

    @Autowired
    private JobListingDetailsRepository joblistingDetailsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionFactory sessionFactory;

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

    @GetMapping("{id}")
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
            model.addAttribute("id", jobListingId);
        }


        return "jobs/jobListing";
    }

    @GetMapping("edit/{id}")
    public String displayEditJobListingForm(Principal principal, Model model,
                                            @PathVariable String id) {
        int jobListingId = Integer.parseInt(id);
        String username = principal.getName();

        Optional<JobListing> result = jobListingRepository.findById(jobListingId);

        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Job Listing");
        } else {
            JobListing jobListing = result.get();
            model.addAttribute("jobListing", jobListing);
        }

//        model.addAttribute("title", "Edit a New Job Listing");
//        model.addAttribute("jobTitle")

        return "jobs/edit";
    }

    @PostMapping("edit/{id}")
    public String processEditJobListing(@PathVariable String id, Model model,
                                        String username,
                                        String jobTitle,
                                        String company,
                                        String jobListingUrl,
                                        String jobListingNumber,
                                        String jobLocation,
                                        String jobType,
                                        String salary,
                                        String jobQualifications,
                                        String jobDescription) {

        System.out.println("***************JobController: inside processEditJobListing() method***************");
        Integer jobListingId = Integer.parseInt(id);


//        // get the current hibernate session
//        Session currentSession = sessionFactory.getCurrentSession();
//
//        // retrieve/read from database using the primary key
//        JobListing theJobListing = currentSession.get(JobListing.class, jobListingId);


        Optional<JobListing> result = jobListingRepository.findById(jobListingId);

        if (result.isEmpty()) {
            model.addAttribute("Title",
                    "Job editing unsuccessful. Please try again.");
        } else {
            JobListing jobListing = result.get();
            jobListing.setJobTitle(jobTitle);
            jobListingRepository.save(jobListing);

            JobListingDetails listingDetails = jobListing.getJobListingDetails();
            int jobListingDetailsId = listingDetails.getId();
            Optional<JobListingDetails> resultDetails =
                    joblistingDetailsRepository.findById(jobListingDetailsId);
            System.out.println("******Job Details ID: " + jobListingDetailsId + "******");
            System.out.println("******resultDetails " + resultDetails + "******");

            if (resultDetails.isEmpty()) {
                model.addAttribute("Title",
                        "Job editing unsuccessful. Please try again.");
            } else {
                System.out.println("******Inside else******");
                JobListingDetails jobListingDetails = resultDetails.get();
                jobListingDetails.setCompany(company);
////                jobListingDetails.editJobListingDetails(
////                        company,
////                        jobListingUrl,
////                        jobListingNumber,
////                        jobLocation,
////                        jobType,
////                        salary,
////                        jobQualifications,
////                        jobDescription);
//
                joblistingDetailsRepository.save(jobListingDetails);
//
            }

        }

        return "redirect:/dashboard";
    }
}