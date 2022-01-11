package org.launchcode.jobsearchtracker.Controllers;

import org.launchcode.jobsearchtracker.Data.JobDetailsRepository;
import org.launchcode.jobsearchtracker.Data.JobRepository;
import org.launchcode.jobsearchtracker.Data.UserRepository;
import org.launchcode.jobsearchtracker.Models.Job;
import org.launchcode.jobsearchtracker.Models.JobDetails;
import org.launchcode.jobsearchtracker.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("jobs")
public class JobController {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobDetailsRepository jobDetailsRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String displaySearchJobs (Model model) {
        model.addAttribute("title","Search Job and All Jobs");
        model.addAttribute("jobs", jobRepository.findAll());
        return "jobs/index";
    }

    @GetMapping("index")
    public String displayAllJobs (Model model) {
        model.addAttribute("title","Search Job and All Jobs");
        model.addAttribute("jobs", jobRepository.findAll());
        return "jobs/index";
    }

    @GetMapping("create/{id}")
    public String displayCreateJob (@PathVariable Integer id,
                                    Model model) {
        Optional<User> result = userRepository.findById(id);
        if (result.isEmpty()){
            model.addAttribute("title", "Invalid User Id!");
        } else {
            User user = result.get();
            model.addAttribute("title", "Create Job");
            model.addAttribute("job", new Job());
            model.addAttribute("user", user);
        }
        return "jobs/create";
    }

    @PostMapping("create")
    public String processingJobCreateForm (String jobTitle,
                                           String companyName,
                                           String jobDescription,
                                           String username
//                                           @ModelAttribute @Valid Job newJob,
//                                           Errors errors,
                                           ) {
//        if (errors.hasErrors()) {
//            model.addAttribute("title", "Create Job");
//            return "jobs/create";
//        }
//        model.addAttribute("title", "Create Job");
        User user = userRepository.findByUsername(username);
        JobDetails jobDetails = new JobDetails(companyName, jobDescription);
        jobDetailsRepository.save(jobDetails);
        Job newJob = new Job(jobTitle, jobDetails, user);
        jobRepository.save(newJob);
        return "redirect:";
    }

    @GetMapping("job_details")
    public String displayJobDetails (@RequestParam Integer jobId, Model model) {
        Optional<Job> result = jobRepository.findById(jobId);

        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Job ID: " + jobId);
        } else {
            Job job = result.get();
            model.addAttribute("title", job.getJobTitle() + " Details");
            model.addAttribute("job", job);
        }
        return "jobs/job_details";
    }
}
