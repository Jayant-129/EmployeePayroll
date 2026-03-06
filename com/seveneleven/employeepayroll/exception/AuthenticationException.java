/**
 * Custom exception thrown when authentication fails.
 * Extends PayrollException to maintain the exception hierarchy.
 * Covers scenarios like invalid credentials and locked accounts.
 * @author developer
 * @version 6.0
 */
package com.seveneleven.employeepayroll.exception;

public class AuthenticationException extends PayrollException {
    public AuthenticationException(String message) {
        super(message, "PAY-105");
    }
}
