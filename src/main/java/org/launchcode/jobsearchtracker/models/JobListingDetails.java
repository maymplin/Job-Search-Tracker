package org.launchcode.jobsearchtracker.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

@Entity
public class JobListingDetails extends AbstractEntity {

    // https://vladmihalcea.com/the-best-way-to-map-a-onetoone-relationship-with-jpa-and-hibernate/
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private JobListing jobListing;

//    @NotEmpty
    private String jobListingUrl;

    private String jobListingNumber;

    private String jobDescription;

    public JobListingDetails() {
    }

    public JobListingDetails(JobListing jobListing) {
        this(jobListing, "", "", "");
    }

    public JobListingDetails(JobListing jobListing, String jobListingUrl, String jobListingNumber,
                             String jobDescription) {
        this.jobListing = jobListing;
        this.jobListingUrl = jobListingUrl;
        this.jobListingNumber = jobListingNumber;
        this.jobDescription = jobDescription;
    }

    public JobListing getJobListing() {
        return jobListing;
    }

    public void setJobListing(JobListing jobListing) {
        this.jobListing = jobListing;
    }

    public String getJobListingUrl() {
        return jobListingUrl;
    }

    public void setJobListingUrl(String jobListingUrl) {
        this.jobListingUrl = jobListingUrl;
    }

    public String getJobListingNumber() {
        return jobListingNumber;
    }

    public void setJobListingNumber(String jobListingNumber) {
        this.jobListingNumber = jobListingNumber;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }
}
