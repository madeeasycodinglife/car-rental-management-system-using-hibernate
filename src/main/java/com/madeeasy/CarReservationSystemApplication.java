package com.madeeasy;

import com.madeeasy.service.UserService;
import com.madeeasy.service.impl.UserServiceImpl;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CarReservationSystemApplication {
    private static final String ADMIN_SECRET_KEY = "admin123";
    private static final String CUSTOMER_ROLE = "CUSTOMER";
    private static final String ADMIN_ROLE = "ADMIN";
    private static UserService customerService = new UserServiceImpl();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("==== Car Rental System ====");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            int choice = 0;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
            } catch (InputMismatchException e) {
                System.out.println("Error: " + e);
                scanner.nextLine(); // Consume the invalid input
                continue; // Skip the rest of the loop and restart
            }

            switch (choice) {
                case 1:
                    System.out.println("Login");
                    handleLogin();
                    break;
                case 2:
                    System.out.println("Register");
                    handleRegistration(scanner);
                    break;
                case 3:
                    System.out.println("Exiting Car Rental System. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void handleLogin() {
        System.out.println("==== Login ====");
        customerService.login();
    }


    private static void handleRegistration(Scanner scanner) {
        System.out.println("==== Registration ====");

        System.out.print("Are you registering as an admin? Enter the secret key (Leave blank for customer registration): ");
        String secretKey = scanner.nextLine();

        if (secretKey.isEmpty()) {
            System.out.println("======= Customer registration =======");
            customerService.addUser(CUSTOMER_ROLE);
        } else if (secretKey.equals(ADMIN_SECRET_KEY)) {
            System.out.println("======= Admin registration =======");
            customerService.addUser(ADMIN_ROLE);
        } else {
            System.out.println("Invalid secret key.");
        }
    }

}