/**
 * Custom exception thrown when password does not meet strength requirements.
 * Extends PayrollException to maintain the exception hierarchy.
 * Enforces minimum length, uppercase, lowercase, digit, and special character rules.
 * @author developer
 * @version 6.0
 */
package com.seveneleven.employeepayroll.exception;

public class InvalidPasswordException extends PayrollException {
    public InvalidPasswordException(String message) {
        super(message, "PAY-103");
    }
}
