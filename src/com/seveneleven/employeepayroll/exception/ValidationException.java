/*
 * General-purpose validation exception thrown for any field that fails regex or rule checks.
 * Carries the field name, invalid value, and expected format for clear error messaging.
 * @author - developer
 * @version - 6.0
 */
package com.seveneleven.employeepayroll.exception;

public class ValidationException extends RuntimeException {

    private String fieldName;
    private String invalidValue;
    private String expectedFormat;

    public ValidationException(String fieldName, String invalidValue, String expectedFormat) {
        super("Validation failed for field '" + fieldName + "': value='" + invalidValue +
              "'. Expected format: " + expectedFormat);
        this.fieldName = fieldName;
        this.invalidValue = invalidValue;
        this.expectedFormat = expectedFormat;
    }

    public ValidationException(String message) {
        super(message);
    }

    public String getFieldName() { return fieldName; }
    public String getInvalidValue() { return invalidValue; }
    public String getExpectedFormat() { return expectedFormat; }
}
