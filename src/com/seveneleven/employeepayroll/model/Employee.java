/*
 * Represents an Employee entity with validated personal and salary information.
 * Demonstrates Encapsulation through private fields, Constructor Overloading,
 * and toString() override for formatted output.
 * @author - developer
 * @version - 1.0
 */
package com.seveneleven.employeepayroll.model;

import com.seveneleven.employeepayroll.model.UserAccount;
import java.util.UUID;

public class Employee {

    private String employeeId;
    private String name;
    private String email;
    private String phone;
    private double salary;
    private String department;
    private UserAccount userAccount;

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$";
    private static final String PHONE_REGEX = "^[6-9][0-9]{9}$";
    private static final String EMP_ID_REGEX = "^EMP[0-9]{4}$";

    public Employee(String name, String email, String phone, double salary, String department) {
        validateEmail(email);
        validatePhone(phone);
        this.employeeId = generateEmployeeId();
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.department = department;
        this.userAccount = new UserAccount(email, phone);
    }

    public Employee(String employeeId, String name, String email, String phone, double salary, String department) {
        validateEmployeeId(employeeId);
        validateEmail(email);
        validatePhone(phone);
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.department = department;
        this.userAccount = new UserAccount(email, phone);
    }

    private void validateEmail(String email) {
        if (email == null || !email.matches(EMAIL_REGEX)) {
            throw new IllegalArgumentException("Invalid email format: " + email);
        }
    }

    private void validatePhone(String phone) {
        if (phone == null || !phone.matches(PHONE_REGEX)) {
            throw new IllegalArgumentException("Invalid phone format: " + phone);
        }
    }

    private void validateEmployeeId(String id) {
        if (id == null || !id.matches(EMP_ID_REGEX)) {
            throw new IllegalArgumentException("Invalid employee ID format. Expected EMPxxxx: " + id);
        }
    }

    private String generateEmployeeId() {
        int suffix = (int)(Math.random() * 9000) + 1000;
        return "EMP" + suffix;
    }

    public String getEmployeeId() { return employeeId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public double getSalary() { return salary; }
    public String getDepartment() { return department; }
    public UserAccount getUserAccount() { return userAccount; }

    public void setSalary(double salary) { this.salary = salary; }
    public void setDepartment(String department) { this.department = department; }

    @Override
    public String toString() {
        return "=== Employee Profile ===" +
               "\nEmployee ID : " + employeeId +
               "\nName        : " + name +
               "\nEmail       : " + email +
               "\nPhone       : " + phone +
               "\nDepartment  : " + department +
               "\nSalary      : INR " + String.format("%.2f", salary) +
               "\n========================";
    }
}
