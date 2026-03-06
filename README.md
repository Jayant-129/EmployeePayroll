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

### UC4: Payslip Print / Download
- Generated downloadable payslip copies using deep cloning
- Implemented equals() and hashCode() for payslip comparison and deduplication
- Used Object cloning with deep copy for independent payslip copies
- Applied File I/O for saving payslips to text files
- Generated unique filenames with timestamps for version control
- Preserved original payslip data through immutable handling

### UC5: Dashboard Display
- Displayed personalized payroll dashboard based on user role
- Implemented Dashboard interface for pluggable dashboard types
- Used getClass() for runtime type checking and identification
- Applied Stream API for processing collections of payslips
- Used Comparator for sorting payslips by date
- Implemented abstract factory pattern for dashboard creation

### UC6: Input Validation
- Validated all user inputs using centralized validation service
- Implemented exception inheritance hierarchy with PayrollException as base
- Created custom exceptions for email, phone, password, employee ID, and authentication
- Applied regex patterns for format validation
- Used fail-fast validation to throw exceptions immediately on invalid input
- Implemented input sanitization to prevent injection attacks
