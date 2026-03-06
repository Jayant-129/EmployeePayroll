/*
 * Dashboard implementation for regular employees showing recent payslips and YTD earnings.
 * Implements the Dashboard interface using Stream API and Comparator for top-3 payslip display.
 * Demonstrates interface implementation and runtime type checking via getClass().
 * @author - developer
 * @version - 5.0
 */
package com.seveneleven.employeepayroll.dashboard;

import com.seveneleven.employeepayroll.model.Employee;
import com.seveneleven.employeepayroll.payroll.Payslip;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeDashboard implements Dashboard {

    private Employee employee;
    private List<Payslip> allPayslips;
    private int currentYear;

    public EmployeeDashboard(Employee employee, List<Payslip> allPayslips, int currentYear) {
        this.employee = employee;
        this.allPayslips = allPayslips;
        this.currentYear = currentYear;
    }

    @Override
    public List<Payslip> getRecentPayslips() {
        return allPayslips.stream()
                .filter(p -> p.getEmployee().getEmployeeId().equals(employee.getEmployeeId()))
                .sorted(Comparator.comparingInt(Payslip::getYear)
                        .thenComparingInt(Payslip::getMonth)
                        .reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    @Override
    public double getYtdEarnings() {
        return allPayslips.stream()
                .filter(p -> p.getEmployee().getEmployeeId().equals(employee.getEmployeeId())
                          && p.getYear() == currentYear)
                .mapToDouble(Payslip::getNetSalary)
                .sum();
    }

    @Override
    public void displayDashboard() {
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║         EMPLOYEE PAYROLL DASHBOARD                   ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║ Dashboard Type : " + getClass().getSimpleName());
        System.out.println("║ Employee       : " + employee.getName() + " (" + employee.getEmployeeId() + ")");
        System.out.println("║ Department     : " + employee.getDepartment());
        System.out.println("║ YTD Earnings   : INR " + String.format("%.2f", getYtdEarnings()));
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║ RECENT PAYSLIPS (Top 3):");
        List<Payslip> recent = getRecentPayslips();
        if (recent.isEmpty()) {
            System.out.println("║   No payslips available.");
        } else {
            for (Payslip p : recent) {
                System.out.printf("║   %-12s | Gross: INR %10.2f | Net: INR %10.2f%n",
                    p.getPayslipId(), p.getGrossSalary(), p.getNetSalary());
            }
        }
        System.out.println("╚══════════════════════════════════════════════════════╝");
    }
}
