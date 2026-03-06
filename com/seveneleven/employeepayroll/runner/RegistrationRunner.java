/**
 * Runner class that demonstrates UC1 Employee Registration functionality.
 * Creates sample employees with validated data and displays registration results.
 * @author developer
 * @version 1.0
 */
package com.seveneleven.employeepayroll.runner;

import com.seveneleven.employeepayroll.model.Employee;
import com.seveneleven.employeepayroll.service.RegistrationService;

public class RegistrationRunner {
    public static void run() {
        System.out.println("\n============================================");
        System.out.println("   UC1: EMPLOYEE REGISTRATION SYSTEM");
        System.out.println("============================================\n");

        RegistrationService service = new RegistrationService();

        System.out.println("--- Registering Employee 1 (Full Details) ---");
        Employee emp1 = service.registerEmployee("Jayant", "Agrawal", "jayant.agrawal@company.com", "+919876543210", 75000.00, "Engineering", "jayant.agrawal", "SecurePass@123");
        System.out.println(emp1);

        System.out.println("\n--- Registering Employee 2 (Full Details) ---");
        Employee emp2 = service.registerEmployee("Priya", "Sharma", "priya.sharma@company.com", "+919123456789", 65000.00, "Marketing", "priya.sharma", "StrongPass@456");
        System.out.println(emp2);

        System.out.println("\n--- Registering Employee 3 (Basic Details) ---");
        Employee emp3 = service.registerEmployee("Rahul", "Kumar", "rahul.kumar@company.com", "+918765432109");
        System.out.println(emp3);

        System.out.println("\n--- Registration Summary ---");
        System.out.println("Total Employees Registered: " + service.getEmployeeCount());

        System.out.println("\n--- Testing Validation (Invalid Email) ---");
        try {
            service.registerEmployee("Test", "User", "invalid-email", "+919999999999", 50000.00, "HR", "test.user", "Pass@123");
        } catch (IllegalArgumentException e) {
            System.out.println("Validation Error: " + e.getMessage());
        }

        System.out.println("\n--- Testing Validation (Invalid Employee ID Format) ---");
        try {
            new Employee("INVALID", "Test", "User", "test@company.com", "+919999999999");
        } catch (IllegalArgumentException e) {
            System.out.println("Validation Error: " + e.getMessage());
        }

        System.out.println("\n--- Constructor Overloading Demo (Minimal Constructor) ---");
        Employee emp4 = new Employee("EMP9999", "Demo", "User");
        System.out.println(emp4);

        System.out.println("\n============================================");
        System.out.println("   UC1 COMPLETED SUCCESSFULLY");
        System.out.println("============================================\n");
    }
}
