/*
 * Custom exception thrown when the dashboard fails to load or process employee data.
 * Carries the dashboard type and employee ID context for debugging data retrieval errors.
 * @author - developer
 * @version - 6.0
 */
package com.seveneleven.employeepayroll.exception;

public class DataErrorException extends RuntimeException {

    private String dashboardType;
    private String employeeId;

    public DataErrorException(String dashboardType, String employeeId) {
        super("Data error in " + dashboardType + " dashboard for employee: " + employeeId);
        this.dashboardType = dashboardType;
        this.employeeId = employeeId;
    }

    public DataErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getDashboardType() { return dashboardType; }
    public String getEmployeeId() { return employeeId; }
}
