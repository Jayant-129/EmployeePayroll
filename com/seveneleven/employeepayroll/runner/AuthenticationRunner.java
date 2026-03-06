/**
 * Runner class that demonstrates UC2 Employee Authentication and Login.
 * Tests polymorphic authentication, role-based access, session management,
 * and account lockout after multiple failed attempts.
 * @author developer
 * @version 2.0
 */
package com.seveneleven.employeepayroll.runner;

import com.seveneleven.employeepayroll.model.RegularEmployee;
import com.seveneleven.employeepayroll.model.Manager;
import com.seveneleven.employeepayroll.model.User;
import com.seveneleven.employeepayroll.service.AuthenticationService;

public class AuthenticationRunner {
    public static void run() {
        System.out.println("\n============================================");
        System.out.println("   UC2: EMPLOYEE AUTHENTICATION & LOGIN");
        System.out.println("============================================\n");

        AuthenticationService authService = new AuthenticationService();

        RegularEmployee emp = new RegularEmployee("USR001", "jayant.agrawal", "SecurePass@123", "Engineering", "EMP0001");
        Manager mgr = new Manager("USR002", "priya.sharma", "AdminPass@456", "Marketing", 8);
        authService.registerUser(emp);
        authService.registerUser(mgr);

        System.out.println("--- Polymorphic Login: Regular Employee ---");
        User loggedInEmp = authService.login("jayant.agrawal", "SecurePass@123");
        if (loggedInEmp != null) {
            System.out.println("    Logged in as: " + loggedInEmp.getClass().getSimpleName());
        }

        System.out.println("\n--- Polymorphic Login: Manager ---");
        User loggedInMgr = authService.login("priya.sharma", "AdminPass@456");
        if (loggedInMgr != null) {
            System.out.println("    Logged in as: " + loggedInMgr.getClass().getSimpleName());
        }

        System.out.println("\n--- Failed Login Attempts (Account Lockout) ---");
        authService.login("jayant.agrawal", "WrongPass1");
        authService.login("jayant.agrawal", "WrongPass2");
        authService.login("jayant.agrawal", "WrongPass3");

        System.out.println("\n--- Attempting Login After Lockout ---");
        authService.login("jayant.agrawal", "SecurePass@123");

        System.out.println("\n--- Session Logout ---");
        if (loggedInMgr != null) {
            authService.logout(loggedInMgr);
        }

        System.out.println("\n--- Unknown User Login ---");
        authService.login("unknown.user", "password");

        System.out.println("\n============================================");
        System.out.println("   UC2 COMPLETED SUCCESSFULLY");
        System.out.println("============================================\n");
    }
}
