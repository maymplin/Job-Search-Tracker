package org.launchcode.jobsearchtracker.models;

import org.launchcode.jobsearchtracker.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Provider;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void processOAuthPostLogin(String username) {
        User existUser = userRepository.findByUsername(username);

        if (existUser == null) {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setProvider(AuthenticationProvider.GOOGLE);
            userRepository.save(newUser);
        }
    }
}
