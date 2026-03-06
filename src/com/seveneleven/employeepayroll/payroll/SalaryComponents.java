/*
 * Represents the breakdown of an employee's salary into its constituent components.
 * Uses a fluent builder interface for readable, chainable salary construction.
 * Immutability is enforced after build to prevent accidental mutation.
 * @author - developer
 * @version - 3.0
 */
package com.seveneleven.employeepayroll.payroll;

public class SalaryComponents {

    private final double basic;
    private final double hra;
    private final double travelAllowance;
    private final double medicalAllowance;
    private final double providentFund;
    private final double incomeTax;
    private final double professionalTax;

    private SalaryComponents(Builder builder) {
        this.basic = builder.basic;
        this.hra = builder.hra;
        this.travelAllowance = builder.travelAllowance;
        this.medicalAllowance = builder.medicalAllowance;
        this.providentFund = builder.providentFund;
        this.incomeTax = builder.incomeTax;
        this.professionalTax = builder.professionalTax;
    }

    public double getBasic() { return basic; }
    public double getHra() { return hra; }
    public double getTravelAllowance() { return travelAllowance; }
    public double getMedicalAllowance() { return medicalAllowance; }
    public double getProvidentFund() { return providentFund; }
    public double getIncomeTax() { return incomeTax; }
    public double getProfessionalTax() { return professionalTax; }

    public static class Builder {
        private double basic;
        private double hra;
        private double travelAllowance;
        private double medicalAllowance;
        private double providentFund;
        private double incomeTax;
        private double professionalTax;

        public Builder basic(double basic) { this.basic = basic; return this; }
        public Builder hra(double hra) { this.hra = hra; return this; }
        public Builder travelAllowance(double ta) { this.travelAllowance = ta; return this; }
        public Builder medicalAllowance(double ma) { this.medicalAllowance = ma; return this; }
        public Builder providentFund(double pf) { this.providentFund = pf; return this; }
        public Builder incomeTax(double tax) { this.incomeTax = tax; return this; }
        public Builder professionalTax(double pt) { this.professionalTax = pt; return this; }

        public SalaryComponents build() {
            if (basic <= 0) {
                throw new IllegalArgumentException("Basic salary must be greater than zero.");
            }
            return new SalaryComponents(this);
        }
    }

    @Override
    public String toString() {
        return String.format(
            "  Basic             : INR %10.2f%n" +
            "  HRA               : INR %10.2f%n" +
            "  Travel Allowance  : INR %10.2f%n" +
            "  Medical Allowance : INR %10.2f%n" +
            "  --------------------------------%n" +
            "  Provident Fund    : INR %10.2f (deduction)%n" +
            "  Income Tax        : INR %10.2f (deduction)%n" +
            "  Professional Tax  : INR %10.2f (deduction)",
            basic, hra, travelAllowance, medicalAllowance,
            providentFund, incomeTax, professionalTax
        );
    }
}
