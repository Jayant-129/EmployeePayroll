/**
 * Dashboard interface defining the contract for all dashboard implementations.
 * Demonstrates interface-based design for pluggable dashboard types.
 * Implementations provide different views based on employee roles.
 * @author developer
 * @version 5.0
 */
package com.seveneleven.employeepayroll.interfaces;

import com.seveneleven.employeepayroll.model.Payslip;
import java.util.List;

public interface Dashboard {
    void displayOverview();
    void displayRecentPayslips(List<Payslip> payslips);
    void displayYTDSummary(List<Payslip> payslips);
    String getDashboardType();
}
