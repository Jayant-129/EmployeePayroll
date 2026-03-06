# Employee Payroll App

A Java OOP-based Employee Payroll Management System demonstrating core Object-Oriented Programming principles through real enterprise-style development.

---

## Use Cases

### UC1: Employee Registration
- Encapsulation with private fields and regex validation (email, phone, employee ID)
- Constructor overloading and `toString()` override
- Composition: `Employee` HAS-A `UserAccount`
- Classes: `Employee`, `UserAccount`, `EmployeeService`

### UC2: Employee Authentication & Login
- Inheritance: `User` (abstract) → `RegularEmployee`, `Manager`
- Polymorphism: `authenticate()` overridden per role
- SHA-256 password hashing, session management, login attempt limiting
- Classes: `User`, `RegularEmployee`, `Manager`, `AuthService`, `Session`

### UC3: Payslip Generation
- Composition: `Payslip` HAS-A `SalaryComponents`; Aggregation: `Payslip` HAS-A `Employee`
- Stream API for gross/net salary calculation
- Fluent builder interface on `SalaryComponents`
- Classes: `SalaryComponents`, `Payslip`, `PayrollService`

### UC4: Payslip Print / Download
- `equals()`, `hashCode()`, and deep `clone()` implementation on `Payslip`
- Immutable salary data, unique filename generation via UUID
- File I/O: saves payslip as `.txt` with download expiry tracking
- Classes: `FileService` (updated `Payslip`)

### UC5: Dashboard Display
- `Dashboard` interface with `displayDashboard()`, `getRecentPayslips()`, `getYtdEarnings()`
- Stream API + Comparator for top-3 recent payslips and YTD aggregation
- Abstract factory pattern for runtime dashboard selection by role
- Classes: `Dashboard`, `EmployeeDashboard`, `ManagerDashboard`, `DashboardService`

### UC6: Input Validation
- Custom exception hierarchy: `InvalidEmailException`, `AuthFailedException`, `CalcErrorException`, `CloneErrorException`, `DataErrorException`, `ValidationException`
- Centralized `ValidationService` with regex for all field types
- Fail-fast validation with typed, descriptive error messages
- Classes: 6 exception classes, `ValidationService`
