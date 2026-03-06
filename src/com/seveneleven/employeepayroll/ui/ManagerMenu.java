/*
 * Handles Manager-specific Menu and operations.
 * @author - developer
 * @version - 7.0
 */
package com.seveneleven.employeepayroll.ui;

import com.seveneleven.employeepayroll.auth.RegularEmployee;
import com.seveneleven.employeepayroll.exception.InvalidEmailException;
import com.seveneleven.employeepayroll.exception.ValidationException;
import com.seveneleven.employeepayroll.model.Employee;
import com.seveneleven.employeepayroll.model.UserAccount;
import com.seveneleven.employeepayroll.payroll.Payslip;
import com.seveneleven.employeepayroll.service.DashboardService;
import com.seveneleven.employeepayroll.validation.ValidationService;

public class ManagerMenu {

    public static boolean display() {
        System.out.println("\n--- MANAGER MENU (Logged in as: " + SharedState.activeSession.getUsername() + ") ---");
        System.out.println("1. [UC1] Register New Employee");
        System.out.println("2. [UC3] Generate Payslip for an Employee");
        System.out.println("3. [UC4] Download a stored Payslip by ID");
        System.out.println("4. [UC5] View Manager Dashboard");
        System.out.println("0. Logout");
        System.out.print("Select option: ");
        String choice = SharedState.scanner.nextLine().trim();
        
        System.out.println("\n=======================================================");
        switch (choice) {
            case "1": registerEmployee(); break;
            case "2": generatePayslip(); break;
            case "3": downloadPayslip(); break;
            case "4": viewManagerDashboard(); break;
            case "0": SharedState.logout(); break;
            default: System.out.println("Invalid choice.");
        }
        System.out.println("=======================================================");
        return true;
    }

    private static void registerEmployee() {
        try {
            System.out.print("Enter Full Name: ");
            String name = SharedState.scanner.nextLine().trim();
            
            System.out.print("Enter Email (e.g. user@domain.com): ");
            String email = SharedState.scanner.nextLine().trim();
            ValidationService.validateEmail(email); 
            
            System.out.print("Enter Phone (10 digits starting with 6-9): ");
            String phone = SharedState.scanner.nextLine().trim();
            ValidationService.validatePhone(phone); 
            
            System.out.print("Enter Department: ");
            String dept = SharedState.scanner.nextLine().trim();
            
            System.out.print("Enter Basic Salary (INR): ");
            double salary = Double.parseDouble(SharedState.scanner.nextLine().trim());
            ValidationService.validateSalary(salary); 
            
            System.out.print("Create a Password for their account (e.g. Strong@123): ");
            String pass = SharedState.scanner.nextLine().trim();
            ValidationService.validatePassword(pass); 

            SharedState.empService.registerEmployee(name, email, phone, salary, dept);
            
            System.out.print("\nAssign a unique Username for login (e.g. first.last): ");
            String user = SharedState.scanner.nextLine().trim();
            
            UserAccount acc = new UserAccount(user, pass);
            SharedState.authService.registerUser(new RegularEmployee(user, acc));
            
            System.out.println("\n>> SUCCESS: Employee registered in HR system AND given login credentials!");
            
        } catch (NumberFormatException e) {
            System.out.println("Error: Salary must be a number.");
        } catch (ValidationException | InvalidEmailException e) {
            System.out.println("Validation Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void generatePayslip() {
        System.out.print("Enter Employee Email to lookup (e.g. jane@example.com) OR precise Employee ID: ");
        String lookup = SharedState.scanner.nextLine().trim();
        
        Employee target = null;
        try {
            target = SharedState.empService.findById(lookup).orElse(null);
        } catch(Exception e) {}
        
        if (target == null) {
            System.out.println("Error: Employee not found.");
            return;
        }
        
        try {
            System.out.print("Enter Pay Month (1-12): ");
            int month = Integer.parseInt(SharedState.scanner.nextLine().trim());
            System.out.print("Enter Pay Year (e.g. 2026): ");
            int year = Integer.parseInt(SharedState.scanner.nextLine().trim());
            
            Payslip p = SharedState.payrollService.generatePayslip(target, month, year);
            System.out.println("\n" + p);
        } catch (Exception e) {
            System.out.println("Generation Error: " + e.getMessage());
        }
    }

    private static void downloadPayslip() {
        System.out.print("Enter exact Payslip ID (e.g. PS-EMP1234-03-2026): ");
        String idToFind = SharedState.scanner.nextLine().trim();
        
        Payslip found = null;
        for (Payslip p : SharedState.payrollService.getAllPayslips()) {
            if (p.getPayslipId().equals(idToFind)) {
                found = p; break;
            }
        }
        
        if (found == null) {
            System.out.println("Error: Payslip ID not found in history.");
            return;
        }
        
        try {
            String path = SharedState.fileService.downloadPayslip(found);
            System.out.println("Success! File saved precisely at: " + path);
        } catch(Exception e) {
            System.out.println("File Error: " + e.getMessage());
        }
    }

    private static void viewManagerDashboard() {
        System.out.println("Building Dashboard from live system data...");
        DashboardService dashboardService = new DashboardService(SharedState.payrollService.getAllPayslips());
        
        Employee adminEmp = null;
        try {
            adminEmp = SharedState.empService.findById("EMP8562").orElse(null);
        } catch(Exception e) {}
        
        if (adminEmp == null) {
            adminEmp = new Employee("MGR-001", "Admin User", "admin@7eleven.com", "9999999999", 150000, "Management");
        }
        
        try {
            dashboardService.display(adminEmp, "MANAGER");
        } catch (Exception e) {
            System.out.println("Dashboard Error: " + e.getMessage());
        }
    }
}
