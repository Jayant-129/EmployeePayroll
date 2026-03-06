/**
 * Represents a user account associated with an Employee.
 * Stores encrypted credentials and demonstrates composition
 * as part of the Employee registration system.
 * @author developer
 * @version 1.0
 */
package com.seveneleven.employeepayroll.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class UserAccount {
    private String accountId;
    private String username;
    private String passwordHash;
    private String role;
    private boolean isActive;

    public UserAccount(String username, String password, String role) {
        this.accountId = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.username = username;
        this.passwordHash = hashPassword(password);
        this.role = role;
        this.isActive = true;
    }

    public UserAccount(String username, String password) {
        this(username, password, "EMPLOYEE");
    }

    private String hashPassword(String password) {
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

    public boolean verifyPassword(String password) {
        return this.passwordHash.equals(hashPassword(password));
    }

    public String getAccountId() {
        return accountId;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
               "accountId='" + accountId + '\'' +
               ", username='" + username + '\'' +
               ", role='" + role + '\'' +
               ", isActive=" + isActive +
               '}';
    }
}
