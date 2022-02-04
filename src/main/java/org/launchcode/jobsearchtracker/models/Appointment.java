package org.launchcode.jobsearchtracker.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Appointment extends AbstractEntity {

    @NotNull
    private LocalDate apptDate;

    private LocalTime apptTime;

    @NotNull
    @ManyToOne
    private JobListing jobListing;



}
