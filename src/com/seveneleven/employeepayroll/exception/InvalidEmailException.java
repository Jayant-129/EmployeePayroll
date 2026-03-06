/*
 * Custom exception thrown when an invalid email address format is detected during validation.
 * Part of the custom exception hierarchy for typed, descriptive error handling.
 * @author - developer
 * @version - 6.0
 */
package com.seveneleven.employeepayroll.exception;

public class InvalidEmailException extends RuntimeException {

    private String invalidEmail;

    public InvalidEmailException(String invalidEmail) {
        super("Invalid email address: " + invalidEmail + ". Expected format: user@domain.com");
        this.invalidEmail = invalidEmail;
    }

    public InvalidEmailException(String invalidEmail, Throwable cause) {
        super("Invalid email address: " + invalidEmail, cause);
        this.invalidEmail = invalidEmail;
    }

    public String getInvalidEmail() { return invalidEmail; }
}
