package com.madeeasy.dao;

import com.madeeasy.entity.User;

import java.util.List;

public interface UserDAO {
    void addCustomer(User customer);

    void updateCustomer(String customerId, User customer);

    void deleteCustomer(String customerId);

    User getCustomerByEmail(String email);

    List<User> getAllCustomers();

    User getCustomerByEmailAndPassword(String email, String password);
}
