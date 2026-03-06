# Employee Payroll App

## Overview
Employee Payroll Management System built using Java OOP concepts.

## Use Cases

### UC1: Employee Registration
- Registered new employees with validated personal and salary information
- Implemented encapsulation with private fields and validation using regex
- Used constructor overloading for flexible employee creation
- Overridden toString() for formatted employee display
- Applied composition pattern with Employee containing UserAccount
- Generated unique employee IDs and stored encrypted credentials

### UC2: Employee Authentication & Login
- Authenticated employees and provided role-based dashboard access
- Implemented inheritance hierarchy with abstract User class
- Used polymorphism with overridden authenticate() in RegularEmployee and Manager
- Applied password hashing using SHA-256 for secure credential storage
- Managed sessions with unique session IDs and login tracking
- Implemented account lockout after multiple failed login attempts

### UC3: Payslip Generation
- Generated detailed monthly payslip with salary breakdown
- Implemented composition with Payslip containing SalaryComponents
- Used aggregation with Payslip referencing Employee
- Applied Stream API for salary calculations
- Built fluent interface for flexible salary structure configuration
- Overridden toString() for professional payslip formatting
