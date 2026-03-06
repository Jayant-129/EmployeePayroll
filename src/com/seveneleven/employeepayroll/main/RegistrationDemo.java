/*
 * Executes Use Case 1: Employee Registration
 * @author - developer
 * @version - 1.0
 */
package com.seveneleven.employeepayroll.main;

import com.seveneleven.employeepayroll.service.EmployeeService;

public class RegistrationDemo {
    public static void run() {
        System.out.println("--- Employee Payroll App: UC1 Employee Registration ---");
        EmployeeService service = new EmployeeService();
        
        try {
            System.out.println("\nRegistering an employee with valid data...");
            String result = service.registerEmployee("Jane Doe", "jane.doe@example.com", "9876543210", 75000, "Engineering");
            System.out.println(result);
            System.out.println("Total Registered Employees: " + service.getTotalEmployeeCount());
        } catch (Exception e) {
            System.err.println("Registration Error: " + e.getMessage());
        }
    }
}
