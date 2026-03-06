/**
 * Runner class that demonstrates UC3 Payslip Generation functionality.
 * Generates payslips using default and custom salary structures,
 * demonstrates fluent interface and Stream API calculations.
 * @author developer
 * @version 3.0
 */
package com.seveneleven.employeepayroll.runner;

import com.seveneleven.employeepayroll.model.Employee;
import com.seveneleven.employeepayroll.model.Payslip;
import com.seveneleven.employeepayroll.model.SalaryComponents;
import com.seveneleven.employeepayroll.service.PayrollService;
import com.seveneleven.employeepayroll.service.RegistrationService;

public class PayslipGenerationRunner {
    public static void run() {
        System.out.println("\n============================================");
        System.out.println("   UC3: PAYSLIP GENERATION SYSTEM");
        System.out.println("============================================\n");

        RegistrationService regService = new RegistrationService();
        PayrollService payrollService = new PayrollService();

        Employee emp1 = regService.registerEmployee("Jayant", "Agrawal", "jayant.agrawal@company.com", "+919876543210", 75000.00, "Engineering", "jayant.agrawal", "Pass@123");
        Employee emp2 = regService.registerEmployee("Priya", "Sharma", "priya.sharma@company.com", "+919123456789", 65000.00, "Marketing", "priya.sharma", "Pass@456");

        System.out.println("--- Generating Payslip (Default Structure) ---");
        Payslip payslip1 = payrollService.generatePayslip(emp1, "March", 2026);
        System.out.println(payslip1);

        System.out.println("\n--- Generating Payslip (Fluent Interface / Custom Structure) ---");
        SalaryComponents customComponents = new SalaryComponents()
                .withBasicSalary(65000)
                .withHRA(26000)
                .withConveyanceAllowance(2000)
                .withMedicalAllowance(1500)
                .withSpecialAllowance(10000)
                .withProvidentFund(7800)
                .withProfessionalTax(200)
                .withIncomeTax(6500);
        Payslip payslip2 = payrollService.generateCustomPayslip(emp2, customComponents, "March", 2026);
        System.out.println(payslip2);

        System.out.println("\n--- Payslip History ---");
        System.out.println("Total Payslips Generated: " + payrollService.getPayslipCount());

        System.out.println("\n============================================");
        System.out.println("   UC3 COMPLETED SUCCESSFULLY");
        System.out.println("============================================\n");
    }
}
