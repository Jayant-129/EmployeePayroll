/**
 * Provides dashboard services implementing the Dashboard interface.
 * Uses getClass() for runtime type checking, Stream API for data processing,
 * Comparator for sorting, and abstract factory pattern for dashboard creation.
 * @author developer
 * @version 5.0
 */
package com.seveneleven.employeepayroll.service;

import com.seveneleven.employeepayroll.interfaces.Dashboard;
import com.seveneleven.employeepayroll.model.Payslip;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DashboardService {

    public static Dashboard createDashboard(String type) {
        return switch (type.toUpperCase()) {
            case "EMPLOYEE" -> new EmployeeDashboard();
            case "MANAGER" -> new ManagerDashboard();
            case "ADMIN" -> new AdminDashboard();
            default -> new EmployeeDashboard();
        };
    }

    static class EmployeeDashboard implements Dashboard {
        @Override
        public void displayOverview() {
            System.out.println("\n    ╔══════════════════════════════════╗");
            System.out.println("    ║     EMPLOYEE DASHBOARD            ║");
            System.out.println("    ╚══════════════════════════════════╝");
            System.out.println("    Dashboard Type: " + getClass().getSimpleName());
            System.out.println("    Runtime Class : " + getClass().getName());
            System.out.println("    Welcome to your personal payroll dashboard!");
        }

        @Override
        public void displayRecentPayslips(List<Payslip> payslips) {
            System.out.println("\n    --- Recent Payslips (Top 3) ---");
            payslips.stream()
                    .sorted(Comparator.comparing(Payslip::getYear).reversed()
                            .thenComparing(Comparator.comparing(Payslip::getMonth).reversed()))
                    .limit(3)
                    .forEach(p -> System.out.println("    " + p.getPayslipId() + " | " +
                            p.getMonth() + " " + p.getYear() + " | Net: Rs." +
                            String.format("%.2f", p.getNetSalary())));
        }

        @Override
        public void displayYTDSummary(List<Payslip> payslips) {
            double totalEarnings = payslips.stream()
                    .mapToDouble(Payslip::getGrossSalary)
                    .sum();
            double totalDeductions = payslips.stream()
                    .mapToDouble(p -> p.getGrossSalary() - p.getNetSalary())
                    .sum();
            double totalNet = payslips.stream()
                    .mapToDouble(Payslip::getNetSalary)
                    .sum();

            System.out.println("\n    --- YTD Earnings Summary ---");
            System.out.printf("    Total Gross     : Rs.%10.2f%n", totalEarnings);
            System.out.printf("    Total Deductions: Rs.%10.2f%n", totalDeductions);
            System.out.printf("    Total Net Pay   : Rs.%10.2f%n", totalNet);
        }

        @Override
        public String getDashboardType() { return "EMPLOYEE"; }
    }

    static class ManagerDashboard implements Dashboard {
        @Override
        public void displayOverview() {
            System.out.println("\n    ╔══════════════════════════════════╗");
            System.out.println("    ║     MANAGER DASHBOARD             ║");
            System.out.println("    ╚══════════════════════════════════╝");
            System.out.println("    Dashboard Type: " + getClass().getSimpleName());
            System.out.println("    Runtime Class : " + getClass().getName());
            System.out.println("    Welcome Manager! Team payroll overview is ready.");
        }

        @Override
        public void displayRecentPayslips(List<Payslip> payslips) {
            System.out.println("\n    --- Team Recent Payslips (Top 3) ---");
            payslips.stream()
                    .sorted(Comparator.comparing(Payslip::getYear).reversed()
                            .thenComparing(Comparator.comparing(Payslip::getMonth).reversed()))
                    .limit(3)
                    .forEach(p -> System.out.println("    " + p.getEmployee().getFullName() + " | " +
                            p.getPayslipId() + " | Net: Rs." + String.format("%.2f", p.getNetSalary())));
        }

        @Override
        public void displayYTDSummary(List<Payslip> payslips) {
            double teamTotal = payslips.stream()
                    .mapToDouble(Payslip::getNetSalary)
                    .sum();
            long employeeCount = payslips.stream()
                    .map(p -> p.getEmployee().getEmployeeId())
                    .distinct()
                    .count();

            System.out.println("\n    --- Team YTD Summary ---");
            System.out.printf("    Team Members    : %d%n", employeeCount);
            System.out.printf("    Total Team Pay  : Rs.%10.2f%n", teamTotal);
            System.out.printf("    Average Pay     : Rs.%10.2f%n", employeeCount > 0 ? teamTotal / employeeCount : 0);
        }

        @Override
        public String getDashboardType() { return "MANAGER"; }
    }

    static class AdminDashboard implements Dashboard {
        @Override
        public void displayOverview() {
            System.out.println("\n    ╔══════════════════════════════════╗");
            System.out.println("    ║     ADMIN DASHBOARD               ║");
            System.out.println("    ╚══════════════════════════════════╝");
            System.out.println("    Dashboard Type: " + getClass().getSimpleName());
            System.out.println("    Runtime Class : " + getClass().getName());
            System.out.println("    Full system oversight and analytics available.");
        }

        @Override
        public void displayRecentPayslips(List<Payslip> payslips) {
            System.out.println("\n    --- All Recent Payslips (Top 3) ---");
            payslips.stream()
                    .sorted(Comparator.comparing(Payslip::getYear).reversed()
                            .thenComparing(Comparator.comparing(Payslip::getMonth).reversed()))
                    .limit(3)
                    .forEach(p -> System.out.println("    [" + p.getEmployee().getDepartment() + "] " +
                            p.getEmployee().getFullName() + " | " + p.getPayslipId() +
                            " | Net: Rs." + String.format("%.2f", p.getNetSalary())));
        }

        @Override
        public void displayYTDSummary(List<Payslip> payslips) {
            double orgTotal = payslips.stream()
                    .mapToDouble(Payslip::getNetSalary)
                    .sum();
            long deptCount = payslips.stream()
                    .map(p -> p.getEmployee().getDepartment())
                    .distinct()
                    .count();

            System.out.println("\n    --- Organization YTD Summary ---");
            System.out.printf("    Total Payroll   : Rs.%10.2f%n", orgTotal);
            System.out.printf("    Departments     : %d%n", deptCount);
            System.out.printf("    Total Payslips  : %d%n", payslips.size());
        }

        @Override
        public String getDashboardType() { return "ADMIN"; }
    }
}
