/**
 * Main entry point for the Employee Payroll Application.
 * Provides an interactive console menu using advanced switch statement
 * to explore different use case functionalities.
 * @author developer
 * @version 1.0
 */
package com.seveneleven.employeepayroll;

import com.seveneleven.employeepayroll.runner.RegistrationRunner;
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
            System.out.println("║  0. Exit                                 ║");
            System.out.println("╚══════════════════════════════════════════╝");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> RegistrationRunner.run();
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
