/*
 * Executes Use Case 4: Payslip Print/Download
 * @author - developer
 * @version - 4.0
 */
package com.seveneleven.employeepayroll.main;

import com.seveneleven.employeepayroll.model.Employee;
import com.seveneleven.employeepayroll.payroll.Payslip;
import com.seveneleven.employeepayroll.service.FileService;
import com.seveneleven.employeepayroll.service.PayrollService;

public class PayslipDownloadDemo {
    public static void run() {
        System.out.println("--- Employee Payroll App: UC4 Payslip Print/Download ---");
        PayrollService payrollService = new PayrollService();
        FileService fileService = new FileService();
        
        try {
            Employee emp = new Employee("EMP5555", "Bob Ross", "bob@email.com", "9123456789", 85000, "Art");
            System.out.println("\n1. Generating payslip...");
            Payslip payslip = payrollService.generatePayslip(emp, 4, 2026);
            
            System.out.println("\n2. Downloading Payslip to File...");
            String savedFilePath = fileService.downloadPayslip(payslip);
            System.out.println("Success! File saved at: " + savedFilePath);
            
            System.out.println("\n3. Testing Download Expiry...");
            boolean expired = fileService.isDownloadExpired(savedFilePath.replace("payslips/", ""));
            System.out.println("Is the download link expired? " + expired);
            
        } catch (Exception e) {
            System.err.println("File Exception: " + e.getMessage());
        }
    }
}
