package org.launchcode.jobsearchtracker.controllers;

import org.launchcode.jobsearchtracker.data.ContactRepository;
import org.launchcode.jobsearchtracker.data.JobListingRepository;
import org.launchcode.jobsearchtracker.data.UserRepository;
import org.launchcode.jobsearchtracker.models.Contact;
import org.launchcode.jobsearchtracker.models.JobListing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("contacts")
public class ContactController {

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    JobListingRepository jobListingRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("add/{id}")
    public String displayAddContactForm(@PathVariable String id, Model model) {

        Integer jobId = Integer.parseInt(id);

        model.addAttribute("title", "Add Contact");
        model.addAttribute("subtitle", jobListingRepository.getById(jobId).getJobTitle());
        model.addAttribute("company", jobListingRepository.getById(jobId).getJobListingDetails().getCompany());
        model.addAttribute("contact", new Contact());
        return "contacts/add-contact";
    }

    @PostMapping("add/{id}")
    public String processAddContactForm(@ModelAttribute @Valid Contact newContact,
                                        @PathVariable String id,
                                        Model model) {

        System.out.println("***** Inside ContactController: processAddContactForm() method *****");

//        model.addAttribute("contact", newContact);

        Optional<JobListing> result  = jobListingRepository.findById((Integer.parseInt(id)));

        if (result.isPresent()) {
            JobListing jobListing = result.get();

            if (!jobListing.getContacts().contains(newContact)) {
                jobListing.addContact(newContact);
            }
        }

        System.out.println("*****" + newContact.getFirstName() + "*****");

        contactRepository.save(newContact);

        return "redirect:../../jobs/" + id;
    }
}
