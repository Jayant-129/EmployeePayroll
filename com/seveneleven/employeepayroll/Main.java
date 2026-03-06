/**
 * Main entry point for the Employee Payroll Application.
 * Provides an interactive console menu using advanced switch statement
 * to explore different use case functionalities.
 * @author developer
 * @version 6.0
 */
package com.seveneleven.employeepayroll;

import com.seveneleven.employeepayroll.runner.RegistrationRunner;
import com.seveneleven.employeepayroll.runner.AuthenticationRunner;
import com.seveneleven.employeepayroll.runner.PayslipGenerationRunner;
import com.seveneleven.employeepayroll.runner.PayslipDownloadRunner;
import com.seveneleven.employeepayroll.runner.DashboardRunner;
import com.seveneleven.employeepayroll.runner.ValidationRunner;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║   EMPLOYEE PAYROLL MANAGEMENT SYSTEM     ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║  1. Employee Registration                ║");
            System.out.println("║  2. Employee Authentication & Login      ║");
            System.out.println("║  3. Payslip Generation                   ║");
            System.out.println("║  4. Payslip Print / Download             ║");
            System.out.println("║  5. Dashboard Display                    ║");
            System.out.println("║  6. Input Validation                     ║");
            System.out.println("║  0. Exit                                 ║");
            System.out.println("╚══════════════════════════════════════════╝");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> RegistrationRunner.run();
                case 2 -> AuthenticationRunner.run();
                case 3 -> PayslipGenerationRunner.run();
                case 4 -> PayslipDownloadRunner.run();
                case 5 -> DashboardRunner.run();
                case 6 -> ValidationRunner.run();
                case 0 -> {
                    running = false;
                    System.out.println("\nThank you for using Employee Payroll System!");
                }
                default -> System.out.println("\nInvalid choice! Please try again.");
            }
        }

        scanner.close();
    }
}
