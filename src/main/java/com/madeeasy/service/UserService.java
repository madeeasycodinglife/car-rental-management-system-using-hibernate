package com.madeeasy.service;

import com.madeeasy.entity.User;

import java.util.List;

public interface UserService {
    void addUser(String role);

    void updateCustomer(String customerId, User customer);

    void deleteCustomer(String customerId);

    User getCustomerById(String customerId);

    List<User> getAllCustomers();

    User getCustomerByEmailAndPassword(String email, String password);

    void login();
}
