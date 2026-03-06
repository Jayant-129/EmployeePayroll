/*
 * Dashboard implementation for managers providing team-level payroll summary and YTD analytics.
 * Implements Dashboard interface with additional team aggregation via Stream API.
 * Uses getClass().getSimpleName() for runtime type identification in display.
 * @author - developer
 * @version - 5.0
 */
package com.seveneleven.employeepayroll.dashboard;

import com.seveneleven.employeepayroll.model.Employee;
import com.seveneleven.employeepayroll.payroll.Payslip;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ManagerDashboard implements Dashboard {

    private Employee manager;
    private List<Payslip> allPayslips;
    private int currentYear;

    public ManagerDashboard(Employee manager, List<Payslip> allPayslips, int currentYear) {
        this.manager = manager;
        this.allPayslips = allPayslips;
        this.currentYear = currentYear;
    }

    @Override
    public List<Payslip> getRecentPayslips() {
        return allPayslips.stream()
                .filter(p -> p.getYear() == currentYear)
                .sorted(Comparator.comparingInt(Payslip::getMonth).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    @Override
    public double getYtdEarnings() {
        return allPayslips.stream()
                .filter(p -> p.getYear() == currentYear)
                .mapToDouble(Payslip::getNetSalary)
                .sum();
    }

    public long getTotalEmployeesInPayroll() {
        return allPayslips.stream()
                .map(p -> p.getEmployee().getEmployeeId())
                .distinct()
                .count();
    }

    public double getAverageNetSalary() {
        return allPayslips.stream()
                .filter(p -> p.getYear() == currentYear)
                .mapToDouble(Payslip::getNetSalary)
                .average()
                .orElse(0.0);
    }

    @Override
    public void displayDashboard() {
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║         MANAGER PAYROLL DASHBOARD                    ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║ Dashboard Type   : " + getClass().getSimpleName());
        System.out.println("║ Manager          : " + manager.getName() + " (" + manager.getEmployeeId() + ")");
        System.out.println("║ Total Employees  : " + getTotalEmployeesInPayroll());
        System.out.println("║ Team YTD Total   : INR " + String.format("%.2f", getYtdEarnings()));
        System.out.println("║ Avg Net Salary   : INR " + String.format("%.2f", getAverageNetSalary()));
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║ RECENT PAYSLIPS (Top 3 - All Employees):");
        List<Payslip> recent = getRecentPayslips();
        if (recent.isEmpty()) {
            System.out.println("║   No payslips available.");
        } else {
            for (Payslip p : recent) {
                System.out.printf("║   %-12s | Emp: %-8s | Net: INR %10.2f%n",
                    p.getPayslipId(), p.getEmployee().getEmployeeId(), p.getNetSalary());
            }
        }
        System.out.println("╚══════════════════════════════════════════════════════╝");
    }
}
