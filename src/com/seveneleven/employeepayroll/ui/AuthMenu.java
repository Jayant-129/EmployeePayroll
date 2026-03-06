/*
 * Handles the Authentication Menu and Login Logic for the CLI.
 * @author - developer
 * @version - 7.0
 */
package com.seveneleven.employeepayroll.ui;

import com.seveneleven.employeepayroll.exception.AuthFailedException;
import com.seveneleven.employeepayroll.exception.ValidationException;

public class AuthMenu {
    
    public static boolean displayLoginMenu() {
        System.out.println("\n--- LOGIN MENU ---");
        System.out.println("1. Login");
        System.out.println("0. Exit System");
        System.out.print("Select option: ");
        String choice = SharedState.scanner.nextLine().trim();
        
        switch (choice) {
            case "1":
                handleLogin();
                return true;
            case "0":
                return false;
            default:
                System.out.println("Invalid choice.");
                return true;
        }
    }
    
    private static void handleLogin() {
        System.out.print("Enter Username: ");
        String user = SharedState.scanner.nextLine().trim();
        System.out.print("Enter Password: ");
        String pass = SharedState.scanner.nextLine().trim();
        
        try {
            SharedState.activeSession = SharedState.authService.login(user, pass);
            System.out.println("Login Successful! Welcome, " + user + ".");
            SharedState.loggedInEmployee = SharedState.empService.findById("EMP" + user).orElse(null);
        } catch (AuthFailedException | ValidationException e) {
            System.out.println("Login Failed: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Login Failed: User not found. (Note: Only Managers can register new employees)");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
