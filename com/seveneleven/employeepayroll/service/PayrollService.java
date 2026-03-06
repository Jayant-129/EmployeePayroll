/**
 * Handles payslip generation including salary computation and component breakdown.
 * Uses Stream API for calculations and maintains payslip history.
 * Generates unique payslip IDs and supports multiple salary structures.
 * @author developer
 * @version 3.0
 */
package com.seveneleven.employeepayroll.service;

import com.seveneleven.employeepayroll.model.Employee;
import com.seveneleven.employeepayroll.model.Payslip;
import com.seveneleven.employeepayroll.model.SalaryComponents;
import java.util.ArrayList;
import java.util.List;

public class PayrollService {
    private List<Payslip> payslipHistory = new ArrayList<>();
    private int payslipCounter = 1;

    public Payslip generatePayslip(Employee employee, String month, int year) {
        SalaryComponents components = new SalaryComponents().calculateFromBasic(employee.getBasicSalary());
        String payslipId = String.format("PAY-%s-%s-%04d", employee.getEmployeeId(), month.substring(0, 3).toUpperCase(), payslipCounter++);
        Payslip payslip = new Payslip(payslipId, employee, components, month, year);
        payslipHistory.add(payslip);
        return payslip;
    }

    public Payslip generateCustomPayslip(Employee employee, SalaryComponents components, String month, int year) {
        String payslipId = String.format("PAY-%s-%s-%04d", employee.getEmployeeId(), month.substring(0, 3).toUpperCase(), payslipCounter++);
        Payslip payslip = new Payslip(payslipId, employee, components, month, year);
        payslipHistory.add(payslip);
        return payslip;
    }

    public List<Payslip> getPayslipHistory() {
        return new ArrayList<>(payslipHistory);
    }

    public List<Payslip> getPayslipsByEmployee(String employeeId) {
        List<Payslip> result = new ArrayList<>();
        for (Payslip p : payslipHistory) {
            if (p.getEmployee().getEmployeeId().equals(employeeId)) {
                result.add(p);
            }
        }
        return result;
    }

    public int getPayslipCount() {
        return payslipHistory.size();
    }
}
