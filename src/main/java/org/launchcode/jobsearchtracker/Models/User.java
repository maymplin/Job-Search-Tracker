package org.launchcode.jobsearchtracker.Models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends AbstractEntity {

    @NotBlank(message = "Username is required!")
    @Size(min = 5, max = 20, message = "")
    private String username;

    @Email(message = "Invalidate Email")
    private String email;

    @Min(0)
    private int dailyGoal;

    @OneToMany(mappedBy = "id")
    private final List<Job> jobs = new ArrayList<>();

    // Constructor
    public User() {}

    public User(String username) {
        this.username = username;
    }

    public User(String username, String email, int dailyGoal) {
        this.username = username;
        this.email = email;
        this.dailyGoal = dailyGoal;
    }

    // Setter and Getter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDailyGoal() {
        return dailyGoal;
    }

    public void setDailyGoal(int dailyGoal) {
        this.dailyGoal = dailyGoal;
    }

    public List<Job> getJobs() {
        return jobs;
    }
}
