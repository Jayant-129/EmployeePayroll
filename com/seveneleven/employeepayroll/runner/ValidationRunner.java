/**
 * Runner class that demonstrates UC6 Input Validation functionality.
 * Tests the complete exception hierarchy, custom exceptions for each input type,
 * regex validation, input sanitization, and fail-fast validation pattern.
 * @author developer
 * @version 6.0
 */
package com.seveneleven.employeepayroll.runner;

import com.seveneleven.employeepayroll.exception.*;
import com.seveneleven.employeepayroll.service.ValidationService;

public class ValidationRunner {
    public static void run() {
        System.out.println("\n============================================");
        System.out.println("   UC6: INPUT VALIDATION SYSTEM");
        System.out.println("============================================\n");

        System.out.println("--- Valid Email Test ---");
        try {
            ValidationService.validateEmail("jayant@company.com");
            System.out.println("    [PASS] Email validation passed");
        } catch (InvalidEmailException e) {
            System.out.println("    [FAIL] " + e);
        }

        System.out.println("\n--- Invalid Email Test ---");
        try {
            ValidationService.validateEmail("invalid-email");
            System.out.println("    [PASS] Email validation passed");
        } catch (InvalidEmailException e) {
            System.out.println("    [CAUGHT] " + e);
            System.out.println("    Error Code: " + e.getErrorCode());
            System.out.println("    Invalid Value: " + e.getInvalidEmail());
        }

        System.out.println("\n--- Valid Phone Test ---");
        try {
            ValidationService.validatePhone("+919876543210");
            System.out.println("    [PASS] Phone validation passed");
        } catch (InvalidPhoneException e) {
            System.out.println("    [FAIL] " + e);
        }

        System.out.println("\n--- Invalid Phone Test ---");
        try {
            ValidationService.validatePhone("123");
            System.out.println("    [PASS] Phone validation passed");
        } catch (InvalidPhoneException e) {
            System.out.println("    [CAUGHT] " + e);
            System.out.println("    Error Code: " + e.getErrorCode());
        }

        System.out.println("\n--- Valid Password Test ---");
        try {
            ValidationService.validatePassword("SecurePass@123");
            System.out.println("    [PASS] Password validation passed");
        } catch (InvalidPasswordException e) {
            System.out.println("    [FAIL] " + e);
        }

        System.out.println("\n--- Weak Password Tests ---");
        String[] weakPasswords = {"short", "alllowercase1@", "ALLUPPERCASE1@", "NoDigits@here", "NoSpecial123"};
        for (String pwd : weakPasswords) {
            try {
                ValidationService.validatePassword(pwd);
            } catch (InvalidPasswordException e) {
                System.out.println("    [CAUGHT] '" + pwd + "' -> " + e.getMessage());
            }
        }

        System.out.println("\n--- Valid Employee ID Test ---");
        try {
            ValidationService.validateEmployeeId("EMP0001");
            System.out.println("    [PASS] Employee ID validation passed");
        } catch (InvalidEmployeeIdException e) {
            System.out.println("    [FAIL] " + e);
        }

        System.out.println("\n--- Invalid Employee ID Test ---");
        try {
            ValidationService.validateEmployeeId("INVALID");
            System.out.println("    [PASS] Employee ID validation passed");
        } catch (InvalidEmployeeIdException e) {
            System.out.println("    [CAUGHT] " + e);
            System.out.println("    Error Code: " + e.getErrorCode());
        }

        System.out.println("\n--- Fail-Fast Validation (All Fields) ---");
        try {
            ValidationService.validateAll("EMP0001", "jayant@company.com", "+919876543210", "SecurePass@123");
            System.out.println("    [PASS] All validations passed");
        } catch (PayrollException e) {
            System.out.println("    [CAUGHT] " + e);
        }

        System.out.println("\n--- Fail-Fast Validation (Bad Email - Stops Early) ---");
        try {
            ValidationService.validateAll("EMP0001", "bad-email", "+919876543210", "SecurePass@123");
            System.out.println("    [PASS] All validations passed");
        } catch (PayrollException e) {
            System.out.println("    [CAUGHT] " + e);
            System.out.println("    Exception Type: " + e.getClass().getSimpleName());
        }

        System.out.println("\n--- Input Sanitization ---");
        String dirty = "  <script>alert('hack')</script>  ";
        String clean = ValidationService.sanitizeInput(dirty);
        System.out.println("    Original : '" + dirty + "'");
        System.out.println("    Sanitized: '" + clean + "'");

        System.out.println("\n--- Exception Hierarchy Demo ---");
        try {
            throw new InvalidEmailException("test@");
        } catch (InvalidEmailException e) {
            System.out.println("    Caught as InvalidEmailException: " + (e instanceof InvalidEmailException));
            System.out.println("    Is PayrollException            : " + (e instanceof PayrollException));
            System.out.println("    Is Exception                   : " + (e instanceof Exception));
        }

        System.out.println("\n============================================");
        System.out.println("   UC6 COMPLETED SUCCESSFULLY");
        System.out.println("============================================\n");
    }
}
