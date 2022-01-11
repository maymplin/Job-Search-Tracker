package org.launchcode.jobsearchtracker.Models;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Job extends AbstractEntity {

    @NotNull
    @Size(min = 3, max = 50, message = "Job title must be 3 to 50 characters")
    private String jobTitle;

    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    @NotNull
    private JobDetails jobDetails;

    @ManyToOne
    @NotNull(message="User is required")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Constructor
    public Job() {}

    public Job(String jobTitle, JobDetails jobDetails, User user) {
        this.jobTitle = jobTitle;
        this.jobDetails = jobDetails;
        this.user = user;
    }

    // Setter and Getter
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public JobDetails getJobDetails() {
        return jobDetails;
    }

    public void setJobDetails(JobDetails jobDetails) {
        this.jobDetails = jobDetails;
    }
}
