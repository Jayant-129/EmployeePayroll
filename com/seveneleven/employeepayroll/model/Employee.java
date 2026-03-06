/**
 * Represents an Employee with validated personal and salary information.
 * Demonstrates encapsulation with private fields and validation,
 * constructor overloading, toString() override, and regex validation.
 * Uses composition by containing a UserAccount object.
 * @author developer
 * @version 1.0
 */
package com.seveneleven.employeepayroll.model;

import java.util.regex.Pattern;

public class Employee {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private double basicSalary;
    private String department;
    private UserAccount userAccount;

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+?[0-9]{10,13}$");
    private static final Pattern ID_PATTERN = Pattern.compile("^EMP[0-9]{4}$");

    public Employee(String employeeId, String firstName, String lastName, String email, String phone, double basicSalary, String department) {
        setEmployeeId(employeeId);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPhone(phone);
        setBasicSalary(basicSalary);
        setDepartment(department);
    }

    public Employee(String employeeId, String firstName, String lastName, String email, String phone) {
        this(employeeId, firstName, lastName, email, phone, 0.0, "Unassigned");
    }

    public Employee(String employeeId, String firstName, String lastName) {
        this(employeeId, firstName, lastName, firstName.toLowerCase() + "." + lastName.toLowerCase() + "@company.com", "+0000000000", 0.0, "Unassigned");
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        if (!ID_PATTERN.matcher(employeeId).matches()) {
            throw new IllegalArgumentException("Employee ID must be in format EMP followed by 4 digits (e.g., EMP0001)");
        }
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be empty");
        }
        this.firstName = firstName.trim();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be empty");
        }
        this.lastName = lastName.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (!PHONE_PATTERN.matcher(phone).matches()) {
            throw new IllegalArgumentException("Phone must be 10-13 digits, optionally starting with +");
        }
        this.phone = phone;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        if (basicSalary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative");
        }
        this.basicSalary = basicSalary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        if (department == null || department.trim().isEmpty()) {
            throw new IllegalArgumentException("Department cannot be empty");
        }
        this.department = department.trim();
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "========================================\n" +
               "         EMPLOYEE DETAILS               \n" +
               "========================================\n" +
               " ID          : " + employeeId + "\n" +
               " Name        : " + getFullName() + "\n" +
               " Email       : " + email + "\n" +
               " Phone       : " + phone + "\n" +
               " Department  : " + department + "\n" +
               " Basic Salary: Rs." + String.format("%.2f", basicSalary) + "\n" +
               " Account     : " + (userAccount != null ? "Active" : "Not Created") + "\n" +
               "========================================";
    }
}
