/*
 * Represents a user account linked to an employee, storing hashed credentials.
 * Demonstrates Composition as part of the Employee entity.
 * Passwords are stored as SHA-256 hashes for security.
 * @author - developer
 * @version - 1.0
 */
package com.seveneleven.employeepayroll.model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserAccount {

    private String username;
    private String passwordHash;
    private String role;
    private boolean isActive;

    public UserAccount(String username, String rawPassword) {
        this.username = username;
        this.passwordHash = hashPassword(rawPassword);
        this.role = "EMPLOYEE";
        this.isActive = true;
    }

    public UserAccount(String username, String rawPassword, String role) {
        this.username = username;
        this.passwordHash = hashPassword(rawPassword);
        this.role = role;
        this.isActive = true;
    }

    private String hashPassword(String rawPassword) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(rawPassword.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not available", e);
        }
    }

    public boolean verifyPassword(String rawPassword) {
        return passwordHash.equals(hashPassword(rawPassword));
    }

    public String getUsername() { return username; }
    public String getPasswordHash() { return passwordHash; }
    public String getRole() { return role; }
    public boolean isActive() { return isActive; }

    public void setRole(String role) { this.role = role; }
    public void setActive(boolean active) { this.isActive = active; }

    @Override
    public String toString() {
        return "UserAccount{username='" + username + "', role='" + role + "', active=" + isActive + "}";
    }
}
