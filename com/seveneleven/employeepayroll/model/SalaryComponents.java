/**
 * Represents individual salary components like basic, HRA, allowances, and deductions.
 * Demonstrates composition as it is contained within a Payslip object.
 * Uses fluent interface pattern for building salary structures.
 * @author developer
 * @version 3.0
 */
package com.seveneleven.employeepayroll.model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SalaryComponents {
    private double basicSalary;
    private double hra;
    private double conveyanceAllowance;
    private double medicalAllowance;
    private double specialAllowance;
    private double providentFund;
    private double professionalTax;
    private double incomeTax;

    public SalaryComponents() {
    }

    public SalaryComponents withBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
        return this;
    }

    public SalaryComponents withHRA(double hra) {
        this.hra = hra;
        return this;
    }

    public SalaryComponents withConveyanceAllowance(double conveyanceAllowance) {
        this.conveyanceAllowance = conveyanceAllowance;
        return this;
    }

    public SalaryComponents withMedicalAllowance(double medicalAllowance) {
        this.medicalAllowance = medicalAllowance;
        return this;
    }

    public SalaryComponents withSpecialAllowance(double specialAllowance) {
        this.specialAllowance = specialAllowance;
        return this;
    }

    public SalaryComponents withProvidentFund(double providentFund) {
        this.providentFund = providentFund;
        return this;
    }

    public SalaryComponents withProfessionalTax(double professionalTax) {
        this.professionalTax = professionalTax;
        return this;
    }

    public SalaryComponents withIncomeTax(double incomeTax) {
        this.incomeTax = incomeTax;
        return this;
    }

    public SalaryComponents calculateFromBasic(double basic) {
        this.basicSalary = basic;
        this.hra = basic * 0.40;
        this.conveyanceAllowance = 1600;
        this.medicalAllowance = 1250;
        this.specialAllowance = basic * 0.15;
        this.providentFund = basic * 0.12;
        this.professionalTax = 200;
        this.incomeTax = basic * 0.10;
        return this;
    }

    public double getGrossSalary() {
        return getEarningsMap().values().stream()
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    public double getTotalDeductions() {
        return getDeductionsMap().values().stream()
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    public double getNetSalary() {
        return getGrossSalary() - getTotalDeductions();
    }

    public Map<String, Double> getEarningsMap() {
        Map<String, Double> earnings = new LinkedHashMap<>();
        earnings.put("Basic Salary", basicSalary);
        earnings.put("HRA", hra);
        earnings.put("Conveyance Allowance", conveyanceAllowance);
        earnings.put("Medical Allowance", medicalAllowance);
        earnings.put("Special Allowance", specialAllowance);
        return earnings;
    }

    public Map<String, Double> getDeductionsMap() {
        Map<String, Double> deductions = new LinkedHashMap<>();
        deductions.put("Provident Fund", providentFund);
        deductions.put("Professional Tax", professionalTax);
        deductions.put("Income Tax", incomeTax);
        return deductions;
    }

    public double getBasicSalary() { return basicSalary; }
    public double getHra() { return hra; }
    public double getConveyanceAllowance() { return conveyanceAllowance; }
    public double getMedicalAllowance() { return medicalAllowance; }
    public double getSpecialAllowance() { return specialAllowance; }
    public double getProvidentFund() { return providentFund; }
    public double getProfessionalTax() { return professionalTax; }
    public double getIncomeTax() { return incomeTax; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  EARNINGS:\n");
        getEarningsMap().forEach((key, value) ->
                sb.append(String.format("    %-25s Rs.%10.2f\n", key, value)));
        sb.append(String.format("    %-25s Rs.%10.2f\n", "--- GROSS SALARY ---", getGrossSalary()));
        sb.append("\n  DEDUCTIONS:\n");
        getDeductionsMap().forEach((key, value) ->
                sb.append(String.format("    %-25s Rs.%10.2f\n", key, value)));
        sb.append(String.format("    %-25s Rs.%10.2f\n", "--- TOTAL DEDUCTIONS ---", getTotalDeductions()));
        return sb.toString();
    }
}
