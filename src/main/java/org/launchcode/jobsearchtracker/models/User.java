package org.launchcode.jobsearchtracker.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;

@Entity
public class User extends AbstractEntity {

    @NotBlank(message = "Username is required!")
    @Size(min = 5, max = 20, message = "")
    private String username;

    @Email
    private String email;

    @Min(0)
    private int dailyGoal;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthenticationProvider provider;

    public User() {}

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

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

    public AuthenticationProvider getProvider() {
        return provider;
    }

    public void setProvider(AuthenticationProvider provider) {
        this.provider = provider;
    }
}
