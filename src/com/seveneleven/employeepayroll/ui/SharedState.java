/*
 * Holds shared application state and service instances for the CLI.
 * @author - developer
 * @version - 7.0
 */
package com.seveneleven.employeepayroll.ui;

import com.seveneleven.employeepayroll.auth.Session;
import com.seveneleven.employeepayroll.model.Employee;
import com.seveneleven.employeepayroll.service.*;
import java.util.Scanner;

public class SharedState {
    public static final AuthService authService = new AuthService();
    public static final EmployeeService empService = new EmployeeService();
    public static final PayrollService payrollService = new PayrollService();
    public static final FileService fileService = new FileService();
    public static final Scanner scanner = new Scanner(System.in);
    
    public static Session activeSession = null;
    public static Employee loggedInEmployee = null;
    
    public static void logout() {
        activeSession = null;
        loggedInEmployee = null;
        System.out.println("Logged out successfully.");
    }
}
