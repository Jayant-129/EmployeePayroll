/**
 * Represents a regular employee user that extends the abstract User class.
 * Implements polymorphic authenticate() method with standard verification.
 * Demonstrates inheritance and method overriding for role-based access.
 * @author developer
 * @version 2.0
 */
package com.seveneleven.employeepayroll.model;

public class RegularEmployee extends User {
    private String department;
    private String employeeId;

    public RegularEmployee(String userId, String username, String password, String department, String employeeId) {
        super(userId, username, password, "EMPLOYEE");
        this.department = department;
        this.employeeId = employeeId;
    }

    @Override
    public boolean authenticate(String username, String password) {
        if (isLocked()) {
            System.out.println("    [ALERT] Account is locked due to multiple failed attempts!");
            return false;
        }

        if (verifyCredentials(username, password)) {
            createSession();
            System.out.println("    [SUCCESS] Employee authenticated successfully.");
            System.out.println("    [SESSION] Session ID: " + getSessionId());
            System.out.println("    [ACCESS] Employee Dashboard loaded.");
            return true;
        }

        recordFailedAttempt();
        System.out.println("    [FAILED] Invalid credentials. Attempts remaining: " + (MAX_LOGIN_ATTEMPTS - getFailedLoginAttempts()));
        return false;
    }

    public String getDepartment() { return department; }
    public String getEmployeeId() { return employeeId; }

    @Override
    public String toString() {
        return "RegularEmployee{userId='" + getUserId() + "', username='" + getUsername() +
               "', department='" + department + "', employeeId='" + employeeId + "'}";
    }
}
