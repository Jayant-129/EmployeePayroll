/*
 * Session entity tracking authenticated user login state with timeout support.
 * Stores session metadata including session ID, user info, and login timestamp.
 * Provides session validity checks based on configurable timeout duration.
 * @author - developer
 * @version - 2.0
 */
package com.seveneleven.employeepayroll.auth;

import java.time.LocalDateTime;
import java.util.UUID;

public class Session {

    private String sessionId;
    private String username;
    private String role;
    private LocalDateTime loginTime;
    private boolean active;
    private static final int SESSION_TIMEOUT_MINUTES = 30;

    public Session(String username, String role) {
        this.sessionId = UUID.randomUUID().toString();
        this.username = username;
        this.role = role;
        this.loginTime = LocalDateTime.now();
        this.active = true;
    }

    public boolean isValid() {
        if (!active) return false;
        LocalDateTime expiryTime = loginTime.plusMinutes(SESSION_TIMEOUT_MINUTES);
        return LocalDateTime.now().isBefore(expiryTime);
    }

    public void invalidate() {
        this.active = false;
    }

    public String getSessionId() { return sessionId; }
    public String getUsername() { return username; }
    public String getRole() { return role; }
    public LocalDateTime getLoginTime() { return loginTime; }
    public boolean isActive() { return active; }

    @Override
    public String toString() {
        return "=== Session ===" +
               "\nSession ID  : " + sessionId +
               "\nUsername    : " + username +
               "\nRole        : " + role +
               "\nLogin Time  : " + loginTime +
               "\nValid       : " + isValid() +
               "\n===============";
    }
}
