/*
 * Interface defining the contract for all payroll dashboard implementations.
 * Enables pluggable dashboard types via polymorphism and runtime role-based selection.
 * All dashboards must provide payslip history, YTD earnings, and display capabilities.
 * @author - developer
 * @version - 5.0
 */
package com.seveneleven.employeepayroll.dashboard;

import com.seveneleven.employeepayroll.payroll.Payslip;
import java.util.List;

public interface Dashboard {

    void displayDashboard();

    List<Payslip> getRecentPayslips();

    double getYtdEarnings();
}
