/**
 * Runner class that demonstrates UC5 Dashboard Display functionality.
 * Tests interface-based dashboards, runtime type checking with getClass(),
 * Stream API processing, Comparator sorting, and abstract factory pattern.
 * @author developer
 * @version 5.0
 */
package com.seveneleven.employeepayroll.runner;

import com.seveneleven.employeepayroll.interfaces.Dashboard;
import com.seveneleven.employeepayroll.model.Employee;
import com.seveneleven.employeepayroll.model.Payslip;
import com.seveneleven.employeepayroll.service.DashboardService;
import com.seveneleven.employeepayroll.service.PayrollService;
import com.seveneleven.employeepayroll.service.RegistrationService;
import java.util.List;

public class DashboardRunner {
    public static void run() {
        System.out.println("\n============================================");
        System.out.println("   UC5: DASHBOARD DISPLAY SYSTEM");
        System.out.println("============================================\n");

        RegistrationService regService = new RegistrationService();
        PayrollService payrollService = new PayrollService();

        Employee emp1 = regService.registerEmployee("Jayant", "Agrawal", "jayant@company.com", "+919876543210", 75000.00, "Engineering", "jayant", "Pass@123");
        Employee emp2 = regService.registerEmployee("Priya", "Sharma", "priya@company.com", "+919123456789", 65000.00, "Marketing", "priya", "Pass@456");
        Employee emp3 = regService.registerEmployee("Rahul", "Kumar", "rahul@company.com", "+918765432109", 55000.00, "Engineering", "rahul", "Pass@789");

        payrollService.generatePayslip(emp1, "January", 2026);
        payrollService.generatePayslip(emp1, "February", 2026);
        payrollService.generatePayslip(emp1, "March", 2026);
        payrollService.generatePayslip(emp2, "January", 2026);
        payrollService.generatePayslip(emp2, "February", 2026);
        payrollService.generatePayslip(emp3, "March", 2026);

        List<Payslip> allPayslips = payrollService.getPayslipHistory();

        System.out.println("--- Abstract Factory: Employee Dashboard ---");
        Dashboard empDashboard = DashboardService.createDashboard("EMPLOYEE");
        empDashboard.displayOverview();
        System.out.println("    Interface check: " + (empDashboard instanceof Dashboard));
        empDashboard.displayRecentPayslips(payrollService.getPayslipsByEmployee("EMP0001"));
        empDashboard.displayYTDSummary(payrollService.getPayslipsByEmployee("EMP0001"));

        System.out.println("\n--- Abstract Factory: Manager Dashboard ---");
        Dashboard mgrDashboard = DashboardService.createDashboard("MANAGER");
        mgrDashboard.displayOverview();
        System.out.println("    Interface check: " + (mgrDashboard instanceof Dashboard));
        mgrDashboard.displayRecentPayslips(allPayslips);
        mgrDashboard.displayYTDSummary(allPayslips);

        System.out.println("\n--- Abstract Factory: Admin Dashboard ---");
        Dashboard adminDashboard = DashboardService.createDashboard("ADMIN");
        adminDashboard.displayOverview();
        System.out.println("    Interface check: " + (adminDashboard instanceof Dashboard));
        adminDashboard.displayRecentPayslips(allPayslips);
        adminDashboard.displayYTDSummary(allPayslips);

        System.out.println("\n--- Runtime Type Checking (getClass) ---");
        System.out.println("    Employee Dashboard: " + empDashboard.getClass().getSimpleName());
        System.out.println("    Manager Dashboard : " + mgrDashboard.getClass().getSimpleName());
        System.out.println("    Admin Dashboard   : " + adminDashboard.getClass().getSimpleName());

        System.out.println("\n============================================");
        System.out.println("   UC5 COMPLETED SUCCESSFULLY");
        System.out.println("============================================\n");
    }
}
