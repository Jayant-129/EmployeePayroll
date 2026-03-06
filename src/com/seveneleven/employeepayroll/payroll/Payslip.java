/*
 * Represents a monthly payslip for an employee, combining salary breakdowns with employee data.
 * Demonstrates Composition (Payslip HAS-A SalaryComponents) and Aggregation (Payslip HAS-A Employee).
 * Uses Stream API for gross and net salary computation with formatted toString output.
 * @author - developer
 * @version - 3.0
 */
package com.seveneleven.employeepayroll.payroll;

import com.seveneleven.employeepayroll.model.Employee;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.DoubleStream;

public class Payslip implements Cloneable {

    private Employee employee;
    private SalaryComponents salaryComponents;
    private int month;
    private int year;
    private String payslipId;
    private long generatedAt;

    public Payslip(Employee employee, SalaryComponents salaryComponents, int month, int year) {
        this.employee = employee;
        this.salaryComponents = salaryComponents;
        this.month = month;
        this.year = year;
        this.payslipId = "PS-" + employee.getEmployeeId() + "-" + String.format("%02d", month) + "-" + year;
        this.generatedAt = System.currentTimeMillis();
    }

    public double getGrossSalary() {
        List<Double> earnings = Arrays.asList(
            salaryComponents.getBasic(),
            salaryComponents.getHra(),
            salaryComponents.getTravelAllowance(),
            salaryComponents.getMedicalAllowance()
        );
        return earnings.stream().mapToDouble(Double::doubleValue).sum();
    }

    public double getTotalDeductions() {
        List<Double> deductions = Arrays.asList(
            salaryComponents.getProvidentFund(),
            salaryComponents.getIncomeTax(),
            salaryComponents.getProfessionalTax()
        );
        return deductions.stream().mapToDouble(Double::doubleValue).sum();
    }

    public double getNetSalary() {
        return getGrossSalary() - getTotalDeductions();
    }

    @Override
    public Payslip clone() {
        try {
            Payslip cloned = (Payslip) super.clone();
            SalaryComponents clonedComponents = new SalaryComponents.Builder()
                .basic(salaryComponents.getBasic())
                .hra(salaryComponents.getHra())
                .travelAllowance(salaryComponents.getTravelAllowance())
                .medicalAllowance(salaryComponents.getMedicalAllowance())
                .providentFund(salaryComponents.getProvidentFund())
                .incomeTax(salaryComponents.getIncomeTax())
                .professionalTax(salaryComponents.getProfessionalTax())
                .build();
            cloned.salaryComponents = clonedComponents;
            cloned.generatedAt = System.currentTimeMillis();
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Payslip cloning failed", e);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Payslip)) return false;
        Payslip other = (Payslip) obj;
        return month == other.month && year == other.year &&
               Objects.equals(payslipId, other.payslipId) &&
               Objects.equals(employee.getEmployeeId(), other.employee.getEmployeeId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(payslipId, employee.getEmployeeId(), month, year);
    }

    public Employee getEmployee() { return employee; }
    public SalaryComponents getSalaryComponents() { return salaryComponents; }
    public int getMonth() { return month; }
    public int getYear() { return year; }
    public String getPayslipId() { return payslipId; }
    public long getGeneratedAt() { return generatedAt; }

    @Override
    public String toString() {
        return "============================================================" +
               "\n            EMPLOYEE PAYSLIP" +
               "\n============================================================" +
               "\nPayslip ID  : " + payslipId +
               "\nEmployee    : " + employee.getName() + " (" + employee.getEmployeeId() + ")" +
               "\nDepartment  : " + employee.getDepartment() +
               "\nPay Period  : " + Month.of(month).name() + " " + year +
               "\n------------------------------------------------------------" +
               "\nEarnings & Deductions:" +
               "\n" + salaryComponents.toString() +
               "\n------------------------------------------------------------" +
               "\n  GROSS SALARY      : INR " + String.format("%10.2f", getGrossSalary()) +
               "\n  TOTAL DEDUCTIONS  : INR " + String.format("%10.2f", getTotalDeductions()) +
               "\n  NET SALARY        : INR " + String.format("%10.2f", getNetSalary()) +
               "\n============================================================";
    }
}
