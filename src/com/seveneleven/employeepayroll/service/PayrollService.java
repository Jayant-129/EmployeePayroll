/*
 * Service class responsible for generating monthly payslips for employees.
 * Computes salary components based on the employee's base salary and standard rules.
 * Maintains a history of generated payslips for YTD and audit purposes.
 * @author - developer
 * @version - 3.0
 */
package com.seveneleven.employeepayroll.service;

import com.seveneleven.employeepayroll.model.Employee;
import com.seveneleven.employeepayroll.payroll.Payslip;
import com.seveneleven.employeepayroll.payroll.SalaryComponents;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PayrollService {

    private List<Payslip> payslipHistory = new ArrayList<>();

    public Payslip generatePayslip(Employee employee, int month, int year) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Invalid month: " + month + ". Must be between 1 and 12.");
        }
        if (year < 2000 || year > 2100) {
            throw new IllegalArgumentException("Invalid year: " + year);
        }
        double basic = employee.getSalary();
        double hra = basic * 0.40;
        double ta = basic * 0.10;
        double medical = basic * 0.05;
        double pf = basic * 0.12;
        double tax = calculateIncomeTax(basic * 12) / 12;
        double professionalTax = 200.0;

        SalaryComponents components = new SalaryComponents.Builder()
                .basic(basic)
                .hra(hra)
                .travelAllowance(ta)
                .medicalAllowance(medical)
                .providentFund(pf)
                .incomeTax(Math.round(tax * 100.0) / 100.0)
                .professionalTax(professionalTax)
                .build();

        Payslip payslip = new Payslip(employee, components, month, year);
        payslipHistory.add(payslip);
        return payslip;
    }

    private double calculateIncomeTax(double annualIncome) {
        if (annualIncome <= 250000) return 0;
        if (annualIncome <= 500000) return (annualIncome - 250000) * 0.05;
        if (annualIncome <= 1000000) return 12500 + (annualIncome - 500000) * 0.20;
        return 112500 + (annualIncome - 1000000) * 0.30;
    }

    public List<Payslip> getPayslipsForEmployee(String employeeId) {
        return payslipHistory.stream()
                .filter(p -> p.getEmployee().getEmployeeId().equals(employeeId))
                .collect(Collectors.toList());
    }

    public double getYtdEarnings(String employeeId, int year) {
        return payslipHistory.stream()
                .filter(p -> p.getEmployee().getEmployeeId().equals(employeeId) && p.getYear() == year)
                .mapToDouble(Payslip::getNetSalary)
                .sum();
    }

    public List<Payslip> getAllPayslips() {
        return new ArrayList<>(payslipHistory);
    }
}
