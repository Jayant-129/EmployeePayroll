/**
 * Handles employee registration including validation, object creation,
 * unique ID generation, and credential management.
 * Provides methods to register new employees and manage the employee registry.
 * @author developer
 * @version 1.0
 */
package com.seveneleven.employeepayroll.service;

import com.seveneleven.employeepayroll.model.Employee;
import com.seveneleven.employeepayroll.model.UserAccount;
import java.util.ArrayList;
import java.util.List;

public class RegistrationService {
    private List<Employee> employeeRegistry = new ArrayList<>();
    private int idCounter = 1;

    public Employee registerEmployee(String firstName, String lastName, String email, String phone, double basicSalary, String department, String username, String password) {
        String employeeId = generateEmployeeId();
        Employee employee = new Employee(employeeId, firstName, lastName, email, phone, basicSalary, department);
        UserAccount account = new UserAccount(username, password);
        employee.setUserAccount(account);
        employeeRegistry.add(employee);
        return employee;
    }

    public Employee registerEmployee(String firstName, String lastName, String email, String phone) {
        String employeeId = generateEmployeeId();
        Employee employee = new Employee(employeeId, firstName, lastName, email, phone);
        employeeRegistry.add(employee);
        return employee;
    }

    private String generateEmployeeId() {
        return String.format("EMP%04d", idCounter++);
    }

    public Employee findEmployeeById(String employeeId) {
        for (Employee emp : employeeRegistry) {
            if (emp.getEmployeeId().equals(employeeId)) {
                return emp;
            }
        }
        return null;
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employeeRegistry);
    }

    public int getEmployeeCount() {
        return employeeRegistry.size();
    }
}
