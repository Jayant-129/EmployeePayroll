/*
 * Service class managing authentication of employees and managers.
 * Maintains an in-memory user registry and delegates auth to polymorphic User subtypes.
 * Provides session management including login, logout, and session validation.
 * @author - developer
 * @version - 2.0
 */
package com.seveneleven.employeepayroll.service;

import com.seveneleven.employeepayroll.auth.User;
import com.seveneleven.employeepayroll.auth.Session;
import java.util.HashMap;
import java.util.Map;

public class AuthService {

    private Map<String, User> userRegistry = new HashMap<>();
    private Map<String, Session> activeSessions = new HashMap<>();

    public void registerUser(User user) {
        userRegistry.put(user.getUsername(), user);
    }

    public Session login(String username, String password) {
        User user = userRegistry.get(username);
        if (user == null) {
            throw new IllegalArgumentException("No user found with username: " + username);
        }
        Session session = user.authenticate(username, password);
        activeSessions.put(session.getSessionId(), session);
        System.out.println("Login successful for: " + username);
        return session;
    }

    public void logout(String sessionId) {
        Session session = activeSessions.get(sessionId);
        if (session != null) {
            session.invalidate();
            activeSessions.remove(sessionId);
            System.out.println("Logged out session: " + sessionId);
        }
    }

    public boolean isSessionValid(String sessionId) {
        Session session = activeSessions.get(sessionId);
        return session != null && session.isValid();
    }

    public Session getSession(String sessionId) {
        return activeSessions.get(sessionId);
    }

    public int getActiveSessionCount() {
        return (int) activeSessions.values().stream().filter(Session::isValid).count();
    }
}
