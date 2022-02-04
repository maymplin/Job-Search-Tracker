package org.launchcode.jobsearchtracker.models;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class JobListing extends AbstractEntity {

    @NotEmpty
    @Size(min=2, max=50, message="Job title must be between 2 and 50 characters")
    private String jobTitle;

//    @NotEmpty
//    @Size(min=2, max=50, message="Company must be between 2 and 50 characters")
//    private String company;

    private boolean jobListingStatus =  true;

    @ManyToOne
    @NotNull(message="User is required")
    private User user;

    @OneToOne(cascade = CascadeType.ALL) //, fetch = FetchType.LAZY
    @Valid
    @NotNull
    private JobListingDetails jobListingDetails;

    @ManyToMany
    private List<Contact> contacts = new ArrayList<>();

    @OneToMany(mappedBy="jobListing")
    private List<Appointment> appointments;

    public JobListing() {
    }

    public JobListing(String jobTitle,
//                      String company,
                      JobListingDetails jobListingDetails,
                      User user) {
        this.jobTitle = jobTitle;
//        if (jobListingDetails != null) {
//            jobListingDetails.setJobListing(this);
            this.jobListingDetails = jobListingDetails;
//        }
//        this.company = company;
        this.user = user;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

//    public String getCompany() {
//        return company;
//    }
//
//    public void setCompany(String company) {
//        this.company = company;
//    }

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

    public List<Contact> getContacts() {
        return contacts;
    }

    public void addContact(Contact contact) {
        this.contacts.add(contact);
    }

    public void removeContact(Contact contact) {
        this.contacts.remove(contact);
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public void addAppointment(Appointment newAppointment) {
        appointments.add(newAppointment);
    }

    public void deleteAppointment(Appointment appointment) {
        appointments.remove(appointment);
    }

}
