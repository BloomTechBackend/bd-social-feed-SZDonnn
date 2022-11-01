package com.bloomtech.socialfeed.exceptions;

public class UserValidationException extends RuntimeException {
    /**
     * Method for providing exception error messages.
     * @param message to be displayed when error occurs
     */
    public UserValidationException(String message) {
        super(message);
    }
}
