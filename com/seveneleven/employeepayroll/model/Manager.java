/**
 * Represents a manager user that extends the abstract User class.
 * Overrides authenticate() with enhanced security and admin access.
 * Demonstrates polymorphism with different authentication behavior than RegularEmployee.
 * @author developer
 * @version 2.0
 */
package com.seveneleven.employeepayroll.model;

public class Manager extends User {
    private String department;
    private int teamSize;

    public Manager(String userId, String username, String password, String department, int teamSize) {
        super(userId, username, password, "MANAGER");
        this.department = department;
        this.teamSize = teamSize;
    }

    @Override
    public boolean authenticate(String username, String password) {
        if (isLocked()) {
            System.out.println("    [ALERT] Manager account is locked! Contact system admin.");
            return false;
        }

        if (verifyCredentials(username, password)) {
            createSession();
            System.out.println("    [SUCCESS] Manager authenticated with elevated privileges.");
            System.out.println("    [SESSION] Session ID: " + getSessionId());
            System.out.println("    [ACCESS] Manager Dashboard loaded with admin controls.");
            System.out.println("    [TEAM] Managing " + teamSize + " team members in " + department);
            return true;
        }

        recordFailedAttempt();
        System.out.println("    [FAILED] Invalid manager credentials. Attempts remaining: " + (MAX_LOGIN_ATTEMPTS - getFailedLoginAttempts()));
        return false;
    }

    public String getDepartment() { return department; }
    public int getTeamSize() { return teamSize; }

    @Override
    public String toString() {
        return "Manager{userId='" + getUserId() + "', username='" + getUsername() +
               "', department='" + department + "', teamSize=" + teamSize + "}";
    }
}
