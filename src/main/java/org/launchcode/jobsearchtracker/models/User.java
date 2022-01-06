package org.launchcode.jobsearchtracker.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User extends AbstractEntity {

    @NotBlank(message = "Username is required!")
    @Size(min = 5, max = 20, message = "")
    private String username;

    @Size(min = 2, max = 50, message = "")
    private String displayName;

    @Min(0)
    private int dailyGoal;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthenticationProvider provider;

    public User() {}

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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
