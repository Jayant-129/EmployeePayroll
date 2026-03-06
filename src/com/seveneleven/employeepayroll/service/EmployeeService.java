/*
 * Service class responsible for employee registration business logic.
 * Orchestrates validation, object creation, and in-memory persistence.
 * Acts as the primary entry point for the employee registration use case.
 * @author - developer
 * @version - 1.0
 */
package com.seveneleven.employeepayroll.service;

import com.seveneleven.employeepayroll.model.Employee;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeService {

    private List<Employee> employeeRegistry = new ArrayList<>();

    public String registerEmployee(String name, String email, String phone, double salary, String department) {
        for (Employee existing : employeeRegistry) {
            if (existing.getEmail().equalsIgnoreCase(email)) {
                throw new IllegalStateException("Employee with email already exists: " + email);
            }
        }
        Employee newEmployee = new Employee(name, email, phone, salary, department);
        employeeRegistry.add(newEmployee);
        return "Registration Successful!\n" + newEmployee.toString();
    }

    public String registerEmployee(String empId, String name, String email, String phone, double salary, String department) {
        for (Employee existing : employeeRegistry) {
            if (existing.getEmployeeId().equals(empId)) {
                throw new IllegalStateException("Employee ID already exists: " + empId);
            }
        }
        Employee newEmployee = new Employee(empId, name, email, phone, salary, department);
        employeeRegistry.add(newEmployee);
        return "Registration Successful!\n" + newEmployee.toString();
    }

    public Optional<Employee> findByEmail(String email) {
        return employeeRegistry.stream()
                .filter(e -> e.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    public Optional<Employee> findById(String employeeId) {
        return employeeRegistry.stream()
                .filter(e -> e.getEmployeeId().equals(employeeId))
                .findFirst();
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employeeRegistry);
    }

    public int getTotalEmployeeCount() {
        return employeeRegistry.size();
    }
}
