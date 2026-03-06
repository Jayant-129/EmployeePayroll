/*
 * Central entry point for the Employee Payroll Application.
 * Full Interactive Console Application supporting login, roles, and all Use Cases.
 * @author - developer
 * @version - 7.0
 */
package com.seveneleven.employeepayroll;

import com.seveneleven.employeepayroll.auth.Manager;
import com.seveneleven.employeepayroll.model.UserAccount;
import com.seveneleven.employeepayroll.ui.*;

public class Main {
    public static void main(String[] args) {
        setupInitialData();
        
        System.out.println("=======================================================");
        System.out.println("  WELCOME TO 7-ELEVEN EMPLOYEE PAYROLL SYSTEM (CLI)    ");
        System.out.println("=======================================================");
        
        boolean running = true;
        while (running) {
            if (SharedState.activeSession == null) {
                running = AuthMenu.displayLoginMenu();
            } else {
                if ("MANAGER".equals(SharedState.activeSession.getRole())) {
                    running = ManagerMenu.display();
                } else {
                    running = EmployeeMenu.display();
                }
            }
        }
        System.out.println("System shutting down. Goodbye!");
        SharedState.scanner.close();
    }
    
    // Setup Mock Data
    private static void setupInitialData() {
        // Create an Admin Manager
        UserAccount adminAcc = new UserAccount("admin", "Admin@123", "MANAGER");
        Manager adminUser = new Manager("admin", adminAcc, "MGR-001");
        SharedState.authService.registerUser(adminUser);
        
        // Register them so payslips can be generated
        try {
            SharedState.empService.registerEmployee("Admin User", "admin@7eleven.com", "9999999999", 150000, "Management");
        } catch (Exception e) {}
    }
}
