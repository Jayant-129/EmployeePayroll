/*
 * Abstract base class representing a system user with authentication capability.
 * Serves as the root of the user hierarchy demonstrating Inheritance and Polymorphism.
 * Concrete subclasses must implement authenticate() for role-specific logic.
 * @author - developer
 * @version - 2.0
 */
package com.seveneleven.employeepayroll.auth;

public abstract class User {

    protected String username;
    protected String role;
    protected int loginAttempts;
    protected static final int MAX_LOGIN_ATTEMPTS = 3;

    public User(String username, String role) {
        this.username = username;
        this.role = role;
        this.loginAttempts = 0;
    }

    public abstract Session authenticate(String username, String password);

    public String getUsername() { return username; }
    public String getRole() { return role; }
    public int getLoginAttempts() { return loginAttempts; }

    public boolean isLocked() {
        return loginAttempts >= MAX_LOGIN_ATTEMPTS;
    }

    protected void incrementLoginAttempts() {
        loginAttempts++;
    }

    protected void resetLoginAttempts() {
        loginAttempts = 0;
    }

    @Override
    public String toString() {
        return "User{username='" + username + "', role='" + role + "', attempts=" + loginAttempts + "}";
    }
}
