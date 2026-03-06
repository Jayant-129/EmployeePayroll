/*
 * Factory service that creates and returns the appropriate Dashboard based on user role.
 * Implements the Abstract Factory pattern for runtime dashboard selection.
 * Decouples dashboard creation from consumption, enabling extensible dashboard types.
 * @author - developer
 * @version - 5.0
 */
package com.seveneleven.employeepayroll.service;

import com.seveneleven.employeepayroll.dashboard.Dashboard;
import com.seveneleven.employeepayroll.dashboard.EmployeeDashboard;
import com.seveneleven.employeepayroll.dashboard.ManagerDashboard;
import com.seveneleven.employeepayroll.model.Employee;
import com.seveneleven.employeepayroll.payroll.Payslip;
import java.time.LocalDate;
import java.util.List;

public class DashboardService {

    private List<Payslip> allPayslips;

    public DashboardService(List<Payslip> allPayslips) {
        this.allPayslips = allPayslips;
    }

    public Dashboard getDashboard(Employee employee, String role) {
        int currentYear = LocalDate.now().getYear();
        switch (role.toUpperCase()) {
            case "MANAGER":
                return new ManagerDashboard(employee, allPayslips, currentYear);
            case "EMPLOYEE":
                return new EmployeeDashboard(employee, allPayslips, currentYear);
            default:
                throw new IllegalArgumentException("Unknown role for dashboard creation: " + role);
        }
    }

    public void display(Employee employee, String role) {
        Dashboard dashboard = getDashboard(employee, role);
        dashboard.displayDashboard();
    }
}
