/**
 * Runner class that demonstrates UC4 Payslip Print and Download functionality.
 * Tests object cloning, equals/hashCode comparison, file I/O operations,
 * and immutable payslip handling for safe downloads.
 * @author developer
 * @version 4.0
 */
package com.seveneleven.employeepayroll.runner;

import com.seveneleven.employeepayroll.model.Employee;
import com.seveneleven.employeepayroll.model.Payslip;
import com.seveneleven.employeepayroll.service.PayrollService;
import com.seveneleven.employeepayroll.service.FileService;
import com.seveneleven.employeepayroll.service.RegistrationService;

public class PayslipDownloadRunner {
    public static void run() {
        System.out.println("\n============================================");
        System.out.println("   UC4: PAYSLIP PRINT / DOWNLOAD");
        System.out.println("============================================\n");

        RegistrationService regService = new RegistrationService();
        PayrollService payrollService = new PayrollService();
        FileService fileService = new FileService();

        Employee emp = regService.registerEmployee("Jayant", "Agrawal", "jayant.agrawal@company.com", "+919876543210", 75000.00, "Engineering", "jayant.agrawal", "Pass@123");

        Payslip originalPayslip = payrollService.generatePayslip(emp, "March", 2026);

        System.out.println("--- Deep Clone Demo ---");
        Payslip clonedPayslip = originalPayslip.clone();
        System.out.println("    Original Payslip ID : " + originalPayslip.getPayslipId());
        System.out.println("    Cloned Payslip ID   : " + clonedPayslip.getPayslipId());
        System.out.println("    Are same object?    : " + (originalPayslip == clonedPayslip));
        System.out.println("    Are equal (equals)? : " + originalPayslip.equals(clonedPayslip));
        System.out.println("    Same hashCode?      : " + (originalPayslip.hashCode() == clonedPayslip.hashCode()));

        System.out.println("\n--- Save Payslip to File ---");
        String filePath = fileService.savePayslip(originalPayslip);
        if (filePath != null) {
            System.out.println("    Payslip saved to: " + filePath);

            System.out.println("\n--- Read Saved Payslip ---");
            String content = fileService.readPayslip(filePath);
            System.out.println(content);
        }

        System.out.println("--- Print Payslip ---");
        fileService.printPayslip(originalPayslip);

        System.out.println("\n--- Deduplication Check ---");
        Payslip anotherClone = originalPayslip.clone();
        System.out.println("    Original equals Clone1 : " + originalPayslip.equals(clonedPayslip));
        System.out.println("    Original equals Clone2 : " + originalPayslip.equals(anotherClone));
        System.out.println("    Clone1 equals Clone2   : " + clonedPayslip.equals(anotherClone));

        System.out.println("\n============================================");
        System.out.println("   UC4 COMPLETED SUCCESSFULLY");
        System.out.println("============================================\n");
    }
}
