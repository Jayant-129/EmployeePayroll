/*
 * Executes Use Case 2: Employee Authentication & Login
 * @author - developer
 * @version - 2.0
 */
package com.seveneleven.employeepayroll.main;

import com.seveneleven.employeepayroll.auth.Manager;
import com.seveneleven.employeepayroll.auth.RegularEmployee;
import com.seveneleven.employeepayroll.auth.Session;
import com.seveneleven.employeepayroll.model.UserAccount;
import com.seveneleven.employeepayroll.service.AuthService;

public class AuthenticationDemo {
    public static void run() {
        System.out.println("--- Employee Payroll App: UC2 Authentication & Login ---");
        AuthService authService = new AuthService();
        
        System.out.println("\nSetting up mock users...");
        UserAccount empAcc = new UserAccount("john.emp", "Pass@123");
        authService.registerUser(new RegularEmployee("john.emp", empAcc));
        
        UserAccount mgrAcc = new UserAccount("admin.mgr", "Admin@123", "MANAGER");
        authService.registerUser(new Manager("admin.mgr", mgrAcc, "MGR-001"));
        
        try {
            System.out.println("\n1. Let's try Employee Login...");
            Session session1 = authService.login("john.emp", "Pass@123");
            System.out.println(session1);
            
            System.out.println("\n2. Let's try Manager Login...");
            Session session2 = authService.login("admin.mgr", "Admin@123");
            System.out.println(session2);
            
            System.out.println("\n3. Let's trigger a Login Failure...");
            authService.login("john.emp", "WrongPass");
        } catch (Exception e) {
            System.err.println("Expected Login Failure: " + e.getMessage());
        }
    }
}
