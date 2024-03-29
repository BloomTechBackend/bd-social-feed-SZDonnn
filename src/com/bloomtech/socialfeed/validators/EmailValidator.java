package com.bloomtech.socialfeed.validators;

import com.bloomtech.socialfeed.exceptions.EmailValidationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements Validator {
    /**
     * An empty constructor for email validator class.
     */
    public EmailValidator() {
    }

    /**
     * Checking if the email has the exact pattern.
     * @param email input from user.
     * @return true if valid, false if pattern is not followed.
     */
    private boolean isValidEmail(String email) {
        //TODO: Validate that email begins with a letter or number, contains only letters, numbers, "." and "_", and
        // that it follows the pattern of name@domain.identifier
        String emailRegex = "^[A-Za-z0-9_.]{2,32}@[A-Za-z0-9]{2,32}\\.[A-Za-z]";
        Pattern p = Pattern.compile(emailRegex);
        Matcher m = p.matcher(email);
        return m.find();
    }

    @Override
    public void validate(Object emailData) {
        String email = (String) emailData;
        if (!isValidEmail(email)) {
            throw new EmailValidationException("Invalid Email: Email address must include '@' " +
                    "before domain and a domain identifier after a '.'!");
        }
    }
}
