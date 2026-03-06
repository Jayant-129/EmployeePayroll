/**
 * Base exception class for the Employee Payroll system.
 * Serves as the root of the custom exception hierarchy.
 * All payroll-specific exceptions extend this class for consistent error handling.
 * @author developer
 * @version 6.0
 */
package com.seveneleven.employeepayroll.exception;

public class PayrollException extends Exception {
    private String errorCode;

    public PayrollException(String message) {
        super(message);
        this.errorCode = "PAY-000";
    }

    public PayrollException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public PayrollException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = "PAY-000";
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String toString() {
        return "[" + errorCode + "] " + getClass().getSimpleName() + ": " + getMessage();
    }
}
