package org.launchcode.jobsearchtracker.controllers;

import org.launchcode.jobsearchtracker.data.ContactRepository;
import org.launchcode.jobsearchtracker.data.JobListingRepository;
import org.launchcode.jobsearchtracker.data.UserRepository;
import org.launchcode.jobsearchtracker.models.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("contacts")
public class ContactController {

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    JobListingRepository jobListingRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("add")
    public String displayAddContactForm(Model model) {

        model.addAttribute("contact", new Contact());
        return "contacts/add-contact";
    }

    @PostMapping("add")
    public String processAddContactForm(@ModelAttribute @Valid Contact newContact,
                                        Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "contacts/add-contact";
        }

        contactRepository.save(newContact);

        return "";
    }
}
