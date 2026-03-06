/*
 * Concrete user implementation for regular employees with role-specific authentication.
 * Extends User and overrides authenticate() demonstrating Polymorphism and Inheritance.
 * Enforces login attempt limits and generates session on successful authentication.
 * @author - developer
 * @version - 2.0
 */
package com.seveneleven.employeepayroll.auth;

import com.seveneleven.employeepayroll.model.UserAccount;

public class RegularEmployee extends User {

    private UserAccount userAccount;

    public RegularEmployee(String username, UserAccount userAccount) {
        super(username, "EMPLOYEE");
        this.userAccount = userAccount;
    }

    @Override
    public Session authenticate(String username, String password) {
        if (isLocked()) {
            throw new IllegalStateException("Account locked due to too many failed attempts. Contact administrator.");
        }
        if (!this.username.equals(username)) {
            incrementLoginAttempts();
            throw new IllegalArgumentException("Invalid username. Attempts remaining: " + (MAX_LOGIN_ATTEMPTS - loginAttempts));
        }
        if (!userAccount.verifyPassword(password)) {
            incrementLoginAttempts();
            throw new IllegalArgumentException("Invalid password. Attempts remaining: " + (MAX_LOGIN_ATTEMPTS - loginAttempts));
        }
        resetLoginAttempts();
        return new Session(username, role);
    }

    public UserAccount getUserAccount() { return userAccount; }

    @Override
    public String toString() {
        return "RegularEmployee{username='" + username + "', role='" + role + "'}";
    }
}
