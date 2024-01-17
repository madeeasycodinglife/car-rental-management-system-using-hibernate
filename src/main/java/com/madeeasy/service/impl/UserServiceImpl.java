package com.madeeasy.service.impl;

import com.madeeasy.dao.UserDAO;
import com.madeeasy.dao.UserDAOImpl;
import com.madeeasy.entity.User;
import com.madeeasy.service.UserService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class UserServiceImpl implements UserService {

    private Scanner scanner = new Scanner(System.in);
    private UserDAO customerDAO = new UserDAOImpl();

    @Override
    public void addUser(String role) {
        String firstName = null;
        do {
            System.out.print("Enter first name : ");
            firstName = scanner.nextLine().trim();
        } while (firstName.isBlank());

        String lastName = null;
        do {
            System.out.print("Enter last name : ");
            lastName = scanner.nextLine().trim();
        } while (lastName.isBlank());

        String email = null;
        do {
            System.out.print("Enter email : ");
            email = scanner.nextLine().trim();
        } while (email.isBlank());

        String gender = null;
        do {
            System.out.print("Enter gender : ");
            gender = scanner.nextLine().trim();
        } while (gender.isBlank());

        String password = null;
        do {
            System.out.print("Enter password : ");
            password = scanner.nextLine().trim();
        } while (password.isBlank());


        String phone = null;
        do {
            System.out.print("Enter phone number : ");
            phone = scanner.nextLine().trim();
        } while (phone.isBlank());


        User user = User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .gender(gender)
                .phoneNumber(phone)
                .build();

        if (role.equals("ADMIN")) {
            user.setUserId(generateRandomAdminId(10));
            user.setRole("ADMIN");
        } else {
            user.setUserId(generateRandomCustomerId(10));
            user.setRole("CUSTOMER");
        }

        this.customerDAO.addCustomer(user);
    }

    private String generateRandomCustomerId(int length) {
        return "CUST" + generateRandomNumber(length);
    }

    private String generateRandomAdminId(int length) {
        return "ADMIN" + generateRandomNumber(length);
    }

    private String generateRandomNumber(int length) {
        // Implement your logic to generate a random number with the specified length
        // For simplicity, you can use a random number generator
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10); // Generate a random digit (0-9)
            stringBuilder.append(digit);
        }
        return stringBuilder.toString();
    }

    @Override
    public void updateCustomer(String customerId, User customer) {

    }

    @Override
    public void deleteCustomer(String customerId) {

    }

    @Override
    public User getCustomerById(String customerId) {
        return null;
    }

    @Override
    public List<User> getAllCustomers() {
        return this.customerDAO.getAllCustomers();
    }

    @Override
    public User getCustomerByEmailAndPassword(String email, String password) {
        return null;
    }

    @Override
    public void login() {
        // login using email and password
        String email = null;
        do {
            System.out.print("Enter email : ");
            email = scanner.nextLine().trim();
        } while (email.isBlank());

        String password = null;
        do {
            System.out.print("Enter password : ");
            password = scanner.nextLine().trim();
        } while (password.isBlank());

        User user = this.customerDAO.getCustomerByEmailAndPassword(email, password);
        if (user != null) {
            if (user.getRole().equals("ADMIN")) {
                System.out.println("Login successful!");
                System.out.println("Hi admin!!");
                /**
                 * show menus corresponding to admin
                 */

                while (true) {
                    System.out.println("1. Car Management");
                    System.out.println("2. Rent Management");
                    System.out.println("3. Exit");
                    System.out.print("Enter your choice: ");
                    int choice = 0;
                    try {
                        choice = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character
                    } catch (InputMismatchException e) {
                        System.out.println("Error: " + e);
                        scanner.nextLine(); // Consume the invalid inputd restart
                    }
                    adminMenus(choice);
                }

            } else {
                System.out.println("Login successful!");
                System.out.println("Hi customer!!");
                /**
                 * show menus corresponding to customer
                 */
            }
        } else {
            System.out.println("Login failed!");
        }
    }

    private void adminMenus(int choice) {
        switch (choice) {
            case 1:
                System.out.println("Car Management");
                break;
            case 2:
                System.out.println("Rent Management");
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
