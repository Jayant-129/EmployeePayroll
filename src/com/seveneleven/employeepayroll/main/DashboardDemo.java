/*
 * Executes Use Case 5: Dashboard Display
 * @author - developer
 * @version - 5.0
 */
package com.seveneleven.employeepayroll.main;

import com.seveneleven.employeepayroll.model.Employee;
import com.seveneleven.employeepayroll.service.DashboardService;
import com.seveneleven.employeepayroll.service.PayrollService;

public class DashboardDemo {
    public static void run() {
        System.out.println("--- Employee Payroll App: UC5 Dashboard Display ---");
        PayrollService payrollService = new PayrollService();
        
        // Setup data
        Employee empE = new Employee("EMP1001", "Charlie", "charlie@email.com", "9000000001", 50000, "Sales");
        Employee empM = new Employee("EMP9001", "Diana (Mgr)", "diana@email.com", "9000000002", 120000, "Sales");
        
        payrollService.generatePayslip(empE, 1, 2026);
        payrollService.generatePayslip(empE, 2, 2026);
        payrollService.generatePayslip(empM, 1, 2026);
        payrollService.generatePayslip(empM, 2, 2026);
        
        DashboardService dashboardService = new DashboardService(payrollService.getAllPayslips());
        
        System.out.println("\n1. Displaying Employee Dashboard:");
        dashboardService.display(empE, "EMPLOYEE");
        
        System.out.println("\n2. Displaying Manager Dashboard:");
        dashboardService.display(empM, "MANAGER");
    }
}
