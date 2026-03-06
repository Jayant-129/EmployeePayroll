/**
 * Represents a monthly payslip containing employee details and salary breakdown.
 * Demonstrates aggregation with Employee and composition with SalaryComponents.
 * Overrides toString() for professional payslip formatting.
 * @author developer
 * @version 3.0
 */
package com.seveneleven.employeepayroll.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Payslip {
    private String payslipId;
    private Employee employee;
    private SalaryComponents salaryComponents;
    private String month;
    private int year;
    private LocalDate generatedDate;

    public Payslip(String payslipId, Employee employee, SalaryComponents salaryComponents, String month, int year) {
        this.payslipId = payslipId;
        this.employee = employee;
        this.salaryComponents = salaryComponents;
        this.month = month;
        this.year = year;
        this.generatedDate = LocalDate.now();
    }

    public String getPayslipId() { return payslipId; }
    public Employee getEmployee() { return employee; }
    public SalaryComponents getSalaryComponents() { return salaryComponents; }
    public String getMonth() { return month; }
    public int getYear() { return year; }
    public LocalDate getGeneratedDate() { return generatedDate; }

    public double getNetSalary() {
        return salaryComponents.getNetSalary();
    }

    public double getGrossSalary() {
        return salaryComponents.getGrossSalary();
    }

    @Override
    public String toString() {
        return "\n╔══════════════════════════════════════════════════╗\n" +
               "║              EMPLOYEE PAYSLIP                    ║\n" +
               "╠══════════════════════════════════════════════════╣\n" +
               "  Payslip ID   : " + payslipId + "\n" +
               "  Employee     : " + employee.getFullName() + " (" + employee.getEmployeeId() + ")\n" +
               "  Department   : " + employee.getDepartment() + "\n" +
               "  Pay Period   : " + month + " " + year + "\n" +
               "  Generated On : " + generatedDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "\n" +
               "╠══════════════════════════════════════════════════╣\n" +
               salaryComponents.toString() +
               "╠══════════════════════════════════════════════════╣\n" +
               String.format("  NET SALARY                      Rs.%10.2f\n", getNetSalary()) +
               "╚══════════════════════════════════════════════════╝";
    }
}
