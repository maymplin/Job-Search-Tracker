package org.launchcode.jobsearchtracker.controllers;

import org.launchcode.jobsearchtracker.data.AppointmentRepository;
import org.launchcode.jobsearchtracker.data.JobListingRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("appointments")
public class AppointmentController {

    private AppointmentRepository appropointmentRepository;

    private JobListingRepository jobListingRepository;

    @GetMapping("{jobId}/add")
    public String displayAddAppointmentForm(@PathVariable String jobId) {

        return "appointments/add-appointment";
    }

    @PostMapping("{jobId}/add")
    public String processAddAppointmentForm() {

        return "";
    }

    @GetMapping("{apptId}/edit")
    public String displayEdit
}
