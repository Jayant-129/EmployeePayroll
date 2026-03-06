/*
 * Handles Employee-specific Menu and operations.
 * @author - developer
 * @version - 7.0
 */
package com.seveneleven.employeepayroll.ui;

import com.seveneleven.employeepayroll.model.Employee;
import com.seveneleven.employeepayroll.payroll.Payslip;
import com.seveneleven.employeepayroll.service.DashboardService;

public class EmployeeMenu {

    public static boolean display() {
        System.out.println("\n--- EMPLOYEE MENU (Logged in as: " + SharedState.activeSession.getUsername() + ") ---");
        System.out.println("1. [UC3] Generate My Current Payslip");
        System.out.println("2. [UC5] View My Dashboard");
        System.out.println("0. Logout");
        System.out.print("Select option: ");
        String choice = SharedState.scanner.nextLine().trim();
        
        System.out.println("\n=======================================================");
        switch (choice) {
            case "1": generateMyPayslip(); break;
            case "2": viewEmployeeDashboard(); break;
            case "0": SharedState.logout(); break;
            default: System.out.println("Invalid choice.");
        }
        System.out.println("=======================================================");
        return true;
    }

    private static void viewEmployeeDashboard() {
        if (SharedState.loggedInEmployee == null) {
            SharedState.loggedInEmployee = new Employee("EMP9999", SharedState.activeSession.getUsername(), "user@m.com", "9123456789", 60000, "IT");
        }
        DashboardService dService = new DashboardService(SharedState.payrollService.getAllPayslips());
        dService.display(SharedState.loggedInEmployee, "EMPLOYEE");
    }

    private static void generateMyPayslip() {
        if (SharedState.loggedInEmployee == null) {
            SharedState.loggedInEmployee = new Employee("EMP9999", SharedState.activeSession.getUsername(), "user@m.com", "9123456789", 60000, "IT");
        }
        try {
            Payslip p = SharedState.payrollService.generatePayslip(SharedState.loggedInEmployee, 3, 2026);
            System.out.println(p);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
