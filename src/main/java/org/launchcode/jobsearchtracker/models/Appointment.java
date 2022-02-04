package org.launchcode.jobsearchtracker.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Appointment extends AbstractEntity {

    @NotNull
    private LocalDate apptDate;

    private LocalTime apptTime;

    @NotBlank
    @Size(min=2, message="Notes must contain a minimum of 2 characters.")
    private String notes;

    @NotNull
    @ManyToOne
    private JobListing jobListing;

    public Appointment() {};

    public Appointment(LocalDate apptDate, LocalTime apptTime, JobListing jobListing) {
        this.apptDate = apptDate;
        this.apptTime = apptTime;
        this.jobListing = jobListing;
    }

    public LocalDate getApptDate() {
        return apptDate;
    }

    public void setApptDate(LocalDate apptDate) {
        this.apptDate = apptDate;
    }

    public LocalTime getApptTime() {
        return apptTime;
    }

    public void setApptTime(LocalTime apptTime) {
        this.apptTime = apptTime;
    }

    public JobListing getJobListing() {
        return jobListing;
    }

    public void setJobListing(JobListing jobListing) {
        this.jobListing = jobListing;
    }
}
