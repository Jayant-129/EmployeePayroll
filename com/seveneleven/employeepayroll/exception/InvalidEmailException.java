/**
 * Custom exception thrown when an invalid email format is detected.
 * Extends PayrollException to maintain the exception hierarchy.
 * Includes the invalid email value for detailed error reporting.
 * @author developer
 * @version 6.0
 */
package com.seveneleven.employeepayroll.exception;

public class InvalidEmailException extends PayrollException {
    private String invalidEmail;

    public InvalidEmailException(String invalidEmail) {
        super("Invalid email format: " + invalidEmail, "PAY-101");
        this.invalidEmail = invalidEmail;
    }

    public String getInvalidEmail() {
        return invalidEmail;
    }
}
