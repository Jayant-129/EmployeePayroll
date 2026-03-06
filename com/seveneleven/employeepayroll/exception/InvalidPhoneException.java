/**
 * Custom exception thrown when an invalid phone number format is detected.
 * Extends PayrollException to maintain the exception hierarchy.
 * Includes the invalid phone value for detailed error reporting.
 * @author developer
 * @version 6.0
 */
package com.seveneleven.employeepayroll.exception;

public class InvalidPhoneException extends PayrollException {
    private String invalidPhone;

    public InvalidPhoneException(String invalidPhone) {
        super("Invalid phone number format: " + invalidPhone, "PAY-102");
        this.invalidPhone = invalidPhone;
    }

    public String getInvalidPhone() {
        return invalidPhone;
    }
}
