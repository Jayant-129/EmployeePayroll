/*
 * Concrete user implementation for managers with elevated authentication privileges.
 * Extends User and overrides authenticate() with additional admin-level role assignment.
 * Demonstrates Polymorphism — same method signature, different runtime behaviour.
 * @author - developer
 * @version - 2.0
 */
package com.seveneleven.employeepayroll.auth;

import com.seveneleven.employeepayroll.model.UserAccount;

public class Manager extends User {

    private UserAccount userAccount;
    private String managerCode;

    public Manager(String username, UserAccount userAccount, String managerCode) {
        super(username, "MANAGER");
        this.userAccount = userAccount;
        this.managerCode = managerCode;
    }

    @Override
    public Session authenticate(String username, String password) {
        if (isLocked()) {
            throw new IllegalStateException("Manager account locked. Contact system administrator immediately.");
        }
        if (!this.username.equals(username)) {
            incrementLoginAttempts();
            throw new IllegalArgumentException("Invalid manager username. Attempts remaining: " + (MAX_LOGIN_ATTEMPTS - loginAttempts));
        }
        if (!userAccount.verifyPassword(password)) {
            incrementLoginAttempts();
            throw new IllegalArgumentException("Invalid manager password. Attempts remaining: " + (MAX_LOGIN_ATTEMPTS - loginAttempts));
        }
        resetLoginAttempts();
        Session session = new Session(username, role);
        System.out.println("[AUDIT] Manager login: " + username + " at " + session.getLoginTime());
        return session;
    }

    public String getManagerCode() { return managerCode; }

    @Override
    public String toString() {
        return "Manager{username='" + username + "', role='" + role + "', code='" + managerCode + "'}";
    }
}
