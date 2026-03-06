/**
 * Provides file operations for payslip download and printing.
 * Generates unique filenames, saves payslips to text files,
 * and demonstrates File I/O with immutable data handling.
 * @author developer
 * @version 4.0
 */
package com.seveneleven.employeepayroll.service;

import com.seveneleven.employeepayroll.model.Payslip;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileService {
    private static final String PAYSLIP_DIR = "payslips";

    public FileService() {
        File dir = new File(PAYSLIP_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public String generateFileName(Payslip payslip) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        return String.format("Payslip_%s_%s_%d_%s.txt",
                payslip.getEmployee().getEmployeeId(),
                payslip.getMonth(),
                payslip.getYear(),
                timestamp);
    }

    public String savePayslip(Payslip payslip) {
        Payslip clonedPayslip = payslip.clone();
        String fileName = generateFileName(clonedPayslip);
        String filePath = PAYSLIP_DIR + File.separator + fileName;

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(clonedPayslip.toString());
            writer.write("\n\n--- This is a system-generated payslip ---");
            writer.write("\n--- Downloaded on: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + " ---");
            return filePath;
        } catch (IOException e) {
            System.out.println("    [ERROR] Failed to save payslip: " + e.getMessage());
            return null;
        }
    }

    public String readPayslip(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            return "[ERROR] Could not read file: " + e.getMessage();
        }
        return content.toString();
    }

    public void printPayslip(Payslip payslip) {
        Payslip printCopy = payslip.clone();
        System.out.println("\n    ╔══════════════════════════════════╗");
        System.out.println("    ║     PRINTING PAYSLIP...          ║");
        System.out.println("    ╚══════════════════════════════════╝");
        System.out.println(printCopy);
    }
}
