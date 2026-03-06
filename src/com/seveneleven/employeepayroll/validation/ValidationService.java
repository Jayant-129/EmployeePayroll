/*
 * Centralized service providing static validation methods for all user input fields.
 * Uses regex patterns for email, phone, password strength, employee ID, and filename validation.
 * Applies fail-fast strategy by throwing typed custom exceptions on the first violation found.
 * @author - developer
 * @version - 6.0
 */
package com.seveneleven.employeepayroll.validation;

import com.seveneleven.employeepayroll.exception.InvalidEmailException;
import com.seveneleven.employeepayroll.exception.ValidationException;

public class ValidationService {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$";
    private static final String PHONE_REGEX = "^[6-9][0-9]{9}$";
    private static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
    private static final String EMP_ID_REGEX = "^EMP[0-9]{4}$";
    private static final String USERNAME_REGEX = "^[a-zA-Z0-9._%+\\-@]+$";
    private static final String FILENAME_REGEX = "^[a-zA-Z0-9_\\-]+\\.txt$";

    public static void validateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new InvalidEmailException("null/blank");
        }
        if (!email.matches(EMAIL_REGEX)) {
            throw new InvalidEmailException(email);
        }
    }

    public static void validatePhone(String phone) {
        if (phone == null || phone.isBlank()) {
            throw new ValidationException("phone", "null/blank", "10-digit Indian mobile number starting with 6-9");
        }
        if (!phone.matches(PHONE_REGEX)) {
            throw new ValidationException("phone", phone, "10-digit number starting with 6-9 (e.g. 9876543210)");
        }
    }

    public static void validatePassword(String password) {
        if (password == null || password.isBlank()) {
            throw new ValidationException("password", "null/blank", "Min 8 chars, 1 uppercase, 1 lowercase, 1 digit, 1 special char");
        }
        if (!password.matches(PASSWORD_REGEX)) {
            throw new ValidationException("password", "[HIDDEN]",
                "Min 8 chars with at least 1 uppercase, 1 lowercase, 1 digit, and 1 special character (@#$%^&+=!)");
        }
    }

    public static void validateEmployeeId(String empId) {
        if (empId == null || empId.isBlank()) {
            throw new ValidationException("employeeId", "null/blank", "EMPxxxx (e.g. EMP1234)");
        }
        if (!empId.matches(EMP_ID_REGEX)) {
            throw new ValidationException("employeeId", empId, "EMPxxxx format — EMP followed by exactly 4 digits");
        }
    }

    public static void validateUsername(String username) {
        if (username == null || username.isBlank()) {
            throw new ValidationException("username", "null/blank", "Alphanumeric with allowed specials: . _ % + - @");
        }
        if (!username.matches(USERNAME_REGEX)) {
            throw new ValidationException("username", username, "Alphanumeric with allowed specials: . _ % + - @");
        }
    }

    public static void validateFilename(String filename) {
        if (filename == null || filename.isBlank()) {
            throw new ValidationException("filename", "null/blank", "alphanumeric_with_underscores.txt");
        }
        if (!filename.matches(FILENAME_REGEX)) {
            throw new ValidationException("filename", filename, "alphanumeric/underscore/hyphen characters ending in .txt");
        }
    }

    public static void validateSalary(double salary) {
        if (salary <= 0) {
            throw new ValidationException("salary", String.valueOf(salary), "Positive number greater than 0");
        }
        if (salary > 10_000_000) {
            throw new ValidationException("salary", String.valueOf(salary), "Value must be <= 10,000,000");
        }
    }
}
