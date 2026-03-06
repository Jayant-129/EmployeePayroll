/*
 * Custom exception thrown when a salary or payslip calculation error occurs.
 * Carries the employee ID and month/year context for debugging calculation failures.
 * @author - developer
 * @version - 6.0
 */
package com.seveneleven.employeepayroll.exception;

public class CalcErrorException extends RuntimeException {

    private String employeeId;
    private int month;
    private int year;

    public CalcErrorException(String employeeId, int month, int year) {
        super("Calculation error for employee " + employeeId + " for period " + month + "/" + year);
        this.employeeId = employeeId;
        this.month = month;
        this.year = year;
    }

    public CalcErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getEmployeeId() { return employeeId; }
    public int getMonth() { return month; }
    public int getYear() { return year; }
}
