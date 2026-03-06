/**
 * Custom exception thrown when an invalid employee ID format is detected.
 * Extends PayrollException to maintain the exception hierarchy.
 * Employee IDs must follow the EMP followed by 4 digits pattern.
 * @author developer
 * @version 6.0
 */
package com.seveneleven.employeepayroll.exception;

public class InvalidEmployeeIdException extends PayrollException {
    private String invalidId;

    public InvalidEmployeeIdException(String invalidId) {
        super("Invalid employee ID format: " + invalidId + ". Expected format: EMP followed by 4 digits", "PAY-104");
        this.invalidId = invalidId;
    }

    public String getInvalidId() {
        return invalidId;
    }
}
