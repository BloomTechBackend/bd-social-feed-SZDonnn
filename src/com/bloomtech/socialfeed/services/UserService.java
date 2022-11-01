package com.bloomtech.socialfeed.services;

import com.bloomtech.socialfeed.models.User;
import com.bloomtech.socialfeed.repositories.UserRepository;
import com.bloomtech.socialfeed.validators.EmailValidator;
import com.bloomtech.socialfeed.validators.UserInfoValidator;

public class UserService {
    private static final UserInfoValidator userInfoValidator = new UserInfoValidator();
    private static final EmailValidator emailValidator = new EmailValidator();
    private UserRepository userRepository;

    /**
     * UserService constructor.
     * @param userRepository - to initialize the instance UserRepository
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Getter for user's username.
     * @param username username to find
     * @return user if found
     */
    public User getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                        .orElseThrow(() -> new RuntimeException("No user found with username: " + username));
        return user;
    }

    /**
     * This method is for saving user details.
     * @param user to validate if it is a valid user.
     */
    public void save(User user) {
        userInfoValidator.validate(user);
        emailValidator.validate(user.getEmail());
        userRepository.save(user);
    }
}
