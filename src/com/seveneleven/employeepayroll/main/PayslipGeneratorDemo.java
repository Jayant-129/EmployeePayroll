/*
 * Executes Use Case 3: Payslip Generation
 * @author - developer
 * @version - 3.0
 */
package com.seveneleven.employeepayroll.main;

import com.seveneleven.employeepayroll.model.Employee;
import com.seveneleven.employeepayroll.payroll.Payslip;
import com.seveneleven.employeepayroll.service.PayrollService;

public class PayslipGeneratorDemo {
    public static void run() {
        System.out.println("--- Employee Payroll App: UC3 Payslip Generation ---");
        PayrollService payrollService = new PayrollService();
        
        try {
            Employee emp = new Employee("EMP1234", "Alice Smith", "alice@email.com", "9876543210", 60000, "HR");
            
            System.out.println("\nGenerating payslip for March, 2026...");
            Payslip payslip = payrollService.generatePayslip(emp, 3, 2026);
            
            System.out.println(payslip);
            System.out.println("\nTotal Payslips stored in Payroll History: " + payrollService.getAllPayslips().size());
        } catch (Exception e) {
            System.err.println("Payslip Error: " + e.getMessage());
        }
    }
}
