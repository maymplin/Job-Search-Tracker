package org.launchcode.jobsearchtracker.models;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class JobListing extends AbstractEntity {

    @NotEmpty
    @Size(min=2, max=50, message="Job title must be between 2 and 50 characters")
    private String jobTitle;

    @NotEmpty
    @Size(min=2, max=50, message="Company must be between 2 and 50 characters")
    private String company;

    private boolean jobListingStatus =  true;

    @ManyToOne
//    @NotNull(message="User is required")
    private User user;

    @OneToOne(cascade = CascadeType.ALL) //, fetch = FetchType.LAZY
    @Valid
    @NotNull
    private JobListingDetails jobListingDetails;

    public JobListing() {
    }

    public JobListing(String jobTitle, String company,
                      JobListingDetails jobListingDetails, User user) {
        this.jobTitle = jobTitle;
//        if (jobListingDetails != null) {
//            jobListingDetails.setJobListing(this);
            this.jobListingDetails = jobListingDetails;
//        }
        this.company = company;
        this.user = user;
    }


//    public JobListing(String jobTilte, String company, boolean jobListingStatus, User user, JobListingDetails jobListingDetails) {
//        this.jobTilte = jobTilte;
//        this.company = company;
//        this.jobListingStatus = jobListingStatus;
//        this.user = user;
//        this.jobListingDetails = jobListingDetails;
//    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public boolean isJobListingStatus() {
        return jobListingStatus;
    }

    public void setJobListingStatus(boolean jobListingStatus) {
        this.jobListingStatus = jobListingStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public JobListingDetails getJobListingDetails() {
        return jobListingDetails;
    }

    public void setJobListingDetails(JobListingDetails jobListingDetails) {
        this.jobListingDetails = jobListingDetails;
    }
}
