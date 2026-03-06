/*
 * Service class handling payslip download operations including deep cloning, file naming, and I/O.
 * Demonstrates deep copy via Payslip.clone(), equals/hashCode for duplicate detection,
 * and File I/O to persist payslips as uniquely named text files with expiry tracking.
 * @author - developer
 * @version - 4.0
 */
package com.seveneleven.employeepayroll.service;

import com.seveneleven.employeepayroll.payroll.Payslip;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FileService {

    private static final String OUTPUT_DIR = "payslips/";
    private static final long DOWNLOAD_EXPIRY_MS = 24 * 60 * 60 * 1000L;
    private Map<String, Long> downloadTimestamps = new HashMap<>();

    public String downloadPayslip(Payslip payslip) {
        Payslip payslipCopy = payslip.clone();
        validatePayslip(payslipCopy, payslip);
        String filename = generateFilename(payslipCopy);
        saveToFile(payslipCopy, filename);
        downloadTimestamps.put(filename, System.currentTimeMillis());
        return OUTPUT_DIR + filename;
    }

    private void validatePayslip(Payslip copy, Payslip original) {
        if (!copy.equals(original)) {
            throw new IllegalStateException("Payslip clone validation failed: copies do not match.");
        }
        if (copy == original) {
            throw new IllegalStateException("Clone did not produce a separate object.");
        }
    }

    private String generateFilename(Payslip payslip) {
        String uniqueId = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        return String.format("payslip_%s_%02d_%d_%s.txt",
                payslip.getEmployee().getEmployeeId(),
                payslip.getMonth(),
                payslip.getYear(),
                uniqueId);
    }

    private void saveToFile(Payslip payslip, String filename) {
        java.io.File dir = new java.io.File(OUTPUT_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(OUTPUT_DIR + filename))) {
            writer.println(payslip.toString());
            writer.println("\nGenerated at: " + LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            writer.println("Expires at  : " + LocalDateTime.now().plusDays(1).format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        } catch (IOException e) {
            throw new RuntimeException("Failed to save payslip file: " + filename, e);
        }
    }

    public boolean isDownloadExpired(String filename) {
        Long timestamp = downloadTimestamps.get(filename);
        if (timestamp == null) return true;
        return (System.currentTimeMillis() - timestamp) > DOWNLOAD_EXPIRY_MS;
    }

    public void printPayslip(Payslip payslip) {
        System.out.println(payslip.toString());
    }
}
