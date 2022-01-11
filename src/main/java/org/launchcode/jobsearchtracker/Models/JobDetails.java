package org.launchcode.jobsearchtracker.Models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class JobDetails extends AbstractEntity {

    @NotNull(message="Company name is required")
    @Size(min = 3, max = 50, message = "Company name must be 3 to 50 characters")
    private String companyName;

    @Size(max = 500, message = "Job Description words limit: 500 characters")
    private String jobDescription;

    @OneToOne(mappedBy = "jobDetails")
    private Job job;

    // Constructor
    public JobDetails() {}

    public JobDetails(String companyName, String jobDescription) {
        this.companyName = companyName;
        this.jobDescription = jobDescription;
    }

    // Setter and Getter

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }
}
