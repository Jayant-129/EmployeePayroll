/*
 * Custom exception thrown when payslip cloning fails or produces an inconsistent copy.
 * Carries the payslip ID that caused the cloning failure for traceability.
 * @author - developer
 * @version - 6.0
 */
package com.seveneleven.employeepayroll.exception;

public class CloneErrorException extends RuntimeException {

    private String payslipId;

    public CloneErrorException(String payslipId) {
        super("Failed to clone payslip: " + payslipId + ". Original and cloned objects do not match.");
        this.payslipId = payslipId;
    }

    public CloneErrorException(String payslipId, Throwable cause) {
        super("Cloning error for payslip: " + payslipId, cause);
        this.payslipId = payslipId;
    }

    public String getPayslipId() { return payslipId; }
}
