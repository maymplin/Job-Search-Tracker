package org.launchcode.jobsearchtracker.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class JobListingDetails extends AbstractEntity {

    @NotEmpty
    @Size(min=2, max=50, message="Company must be between 2 and 50 characters")
    private String company;
//    @NotEmpty
    private String jobListingUrl;

    private String jobListingNumber;

    private String jobDescription;

    // https://vladmihalcea.com/the-best-way-to-map-a-onetoone-relationship-with-jpa-and-hibernate/
    @OneToOne(//fetch = FetchType.LAZY,
            mappedBy = "jobListingDetails")
//    @MapsId
    private JobListing jobListing;

    public JobListingDetails() {
    }

//    public JobListingDetails(JobListing jobListing) {
//
//        this(jobListing, "", "", "");
//    }


    public JobListingDetails(String company) {
        this.company = company;
    }

    public JobListingDetails(
            String company,
//            JobListing jobListing,
                             String jobListingUrl,
                             String jobListingNumber,
                             String jobDescription) {
//        this.jobListing = jobListing;
        this.company = company;
        this.jobListingUrl = jobListingUrl;
        this.jobListingNumber = jobListingNumber;
        this.jobDescription = jobDescription;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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
