/**
 * Provides centralized authentication services for the payroll system.
 * Manages user registration, login verification, and session handling.
 * Demonstrates polymorphic authentication through the User hierarchy.
 * @author developer
 * @version 2.0
 */
package com.seveneleven.employeepayroll.service;

import com.seveneleven.employeepayroll.model.User;
import com.seveneleven.employeepayroll.model.RegularEmployee;
import com.seveneleven.employeepayroll.model.Manager;
import java.util.ArrayList;
import java.util.List;

public class AuthenticationService {
    private List<User> userStore = new ArrayList<>();

    public void registerUser(User user) {
        userStore.add(user);
    }

    public User login(String username, String password) {
        for (User user : userStore) {
            if (user.getUsername().equals(username)) {
                boolean success = user.authenticate(username, password);
                if (success) {
                    return user;
                }
                return null;
            }
        }
        System.out.println("    [ERROR] User not found: " + username);
        return null;
    }

    public void logout(User user) {
        System.out.println("    [LOGOUT] Session ended for: " + user.getUsername());
    }

    public User findUserByUsername(String username) {
        for (User user : userStore) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public int getUserCount() {
        return userStore.size();
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(userStore);
    }
}
