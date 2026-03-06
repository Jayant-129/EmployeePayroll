/**
 * Abstract base class representing a user in the payroll system.
 * Demonstrates inheritance and abstract method pattern for authentication.
 * Subclasses must implement the authenticate() method for role-specific login.
 * @author developer
 * @version 2.0
 */
package com.seveneleven.employeepayroll.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

public abstract class User {
    private String userId;
    private String username;
    private String passwordHash;
    private String role;
    private int failedLoginAttempts;
    private boolean isLocked;
    private LocalDateTime lastLoginTime;
    private String sessionId;

    protected static final int MAX_LOGIN_ATTEMPTS = 3;

    public User(String userId, String username, String password, String role) {
        this.userId = userId;
        this.username = username;
        this.passwordHash = hashPassword(password);
        this.role = role;
        this.failedLoginAttempts = 0;
        this.isLocked = false;
    }

    public abstract boolean authenticate(String username, String password);

    protected String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            return String.valueOf(password.hashCode());
        }
    }

    protected boolean verifyCredentials(String username, String password) {
        return this.username.equals(username) && this.passwordHash.equals(hashPassword(password));
    }

    protected void createSession() {
        this.sessionId = "SESSION-" + System.currentTimeMillis();
        this.lastLoginTime = LocalDateTime.now();
        this.failedLoginAttempts = 0;
    }

    protected void recordFailedAttempt() {
        this.failedLoginAttempts++;
        if (this.failedLoginAttempts >= MAX_LOGIN_ATTEMPTS) {
            this.isLocked = true;
        }
    }

    public String getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getRole() { return role; }
    public boolean isLocked() { return isLocked; }
    public int getFailedLoginAttempts() { return failedLoginAttempts; }
    public LocalDateTime getLastLoginTime() { return lastLoginTime; }
    public String getSessionId() { return sessionId; }
    public String getPasswordHash() { return passwordHash; }

    public void unlock() {
        this.isLocked = false;
        this.failedLoginAttempts = 0;
    }

    @Override
    public String toString() {
        return "User{userId='" + userId + "', username='" + username + "', role='" + role + "', locked=" + isLocked + "}";
    }
}
