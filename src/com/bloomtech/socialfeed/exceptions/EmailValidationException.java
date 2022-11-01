package com.bloomtech.socialfeed.exceptions;

public class EmailValidationException extends RuntimeException {
    /**
     * Throws exception error.
     * @param message display when throwing exception.
     */
    public EmailValidationException(String message) {
        super(message);
    }
}
