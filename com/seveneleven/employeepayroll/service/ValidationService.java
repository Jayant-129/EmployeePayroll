/**
 * Centralized validation service for all user inputs in the payroll system.
 * Uses regex patterns for email, phone, password, and employee ID validation.
 * Implements fail-fast validation by throwing custom exceptions immediately.
 * Provides reusable validation utilities and input sanitization methods.
 * @author developer
 * @version 6.0
 */
package com.seveneleven.employeepayroll.service;

import com.seveneleven.employeepayroll.exception.*;
import java.util.regex.Pattern;

public class ValidationService {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+?[0-9]{10,13}$");
    private static final Pattern ID_PATTERN = Pattern.compile("^EMP[0-9]{4}$");
    private static final Pattern PASSWORD_UPPER = Pattern.compile(".*[A-Z].*");
    private static final Pattern PASSWORD_LOWER = Pattern.compile(".*[a-z].*");
    private static final Pattern PASSWORD_DIGIT = Pattern.compile(".*[0-9].*");
    private static final Pattern PASSWORD_SPECIAL = Pattern.compile(".*[@#$%^&+=!].*");

    public static void validateEmail(String email) throws InvalidEmailException {
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new InvalidEmailException(email);
        }
    }

    public static void validatePhone(String phone) throws InvalidPhoneException {
        if (phone == null || !PHONE_PATTERN.matcher(phone).matches()) {
            throw new InvalidPhoneException(phone);
        }
    }

    public static void validatePassword(String password) throws InvalidPasswordException {
        if (password == null || password.length() < 8) {
            throw new InvalidPasswordException("Password must be at least 8 characters long");
        }
        if (!PASSWORD_UPPER.matcher(password).matches()) {
            throw new InvalidPasswordException("Password must contain at least one uppercase letter");
        }
        if (!PASSWORD_LOWER.matcher(password).matches()) {
            throw new InvalidPasswordException("Password must contain at least one lowercase letter");
        }
        if (!PASSWORD_DIGIT.matcher(password).matches()) {
            throw new InvalidPasswordException("Password must contain at least one digit");
        }
        if (!PASSWORD_SPECIAL.matcher(password).matches()) {
            throw new InvalidPasswordException("Password must contain at least one special character (@#$%^&+=!)");
        }
    }

    public static void validateEmployeeId(String employeeId) throws InvalidEmployeeIdException {
        if (employeeId == null || !ID_PATTERN.matcher(employeeId).matches()) {
            throw new InvalidEmployeeIdException(employeeId);
        }
    }

    public static String sanitizeInput(String input) {
        if (input == null) return "";
        return input.trim().replaceAll("[<>\"'&]", "");
    }

    public static void validateAll(String employeeId, String email, String phone, String password) throws PayrollException {
        validateEmployeeId(employeeId);
        validateEmail(email);
        validatePhone(phone);
        validatePassword(password);
    }
}
