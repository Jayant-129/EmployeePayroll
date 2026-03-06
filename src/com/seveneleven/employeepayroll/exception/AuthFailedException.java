/*
 * Custom exception thrown when authentication fails due to invalid credentials or account lock.
 * Carries the username that failed authentication for audit log purposes.
 * @author - developer
 * @version - 6.0
 */
package com.seveneleven.employeepayroll.exception;

public class AuthFailedException extends RuntimeException {

    private String username;

    public AuthFailedException(String username) {
        super("Authentication failed for user: " + username + ". Invalid credentials or account locked.");
        this.username = username;
    }

    public AuthFailedException(String username, String reason) {
        super("Authentication failed for user: " + username + ". Reason: " + reason);
        this.username = username;
    }

    public String getUsername() { return username; }
}
