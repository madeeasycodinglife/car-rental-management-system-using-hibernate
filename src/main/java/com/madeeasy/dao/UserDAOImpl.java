package com.madeeasy.dao;

import com.madeeasy.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.NativeQuery;

import java.util.List;


@SuppressWarnings("all")
public class UserDAOImpl implements UserDAO {


    private SessionFactory sessionFactory = null;

    public UserDAOImpl() {
        this.sessionFactory = new Configuration()
                .configure().buildSessionFactory();
    }

    @Override
    public void addCustomer(User customer) {

        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            try {
                session.persist(customer);
                transaction.commit();
                System.out.println("User saved successfully !!");
            } catch (ConstraintViolationException exception) {
                System.out.println("Constraint Violation : " + exception.getMessage());
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
            }
        } catch (Exception e) {
            System.out.println("Exception : {}" + e.getMessage());
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void updateCustomer(String customerId, User customer) {
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            try {
                session.update(customer);
                transaction.commit();
                System.out.println("User updated successfully !!");
            } catch (ConstraintViolationException exception) {
                System.out.println("Constraint Violation : " + exception.getMessage());
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
            }
        } catch (Exception e) {
            System.out.println("Exception : {}" + e.getMessage());
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void deleteCustomer(String customerId) {
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            try {
                session.delete(session.get(User.class, customerId));
                transaction.commit();
                System.out.println("User deleted successfully!!");
            } catch (ConstraintViolationException exception) {
                System.out.println("Constraint Violation : " + exception.getMessage());
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
            }
        } catch (Exception e) {
            System.out.println("Exception : {}" + e.getMessage());
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    @Override
    public User getCustomerByEmail(String email) {
        try (Session session = this.sessionFactory.openSession()) {
            // Use native SQL query to select all customers
            String sql = "SELECT * FROM customers WHERE email = :email";
            NativeQuery<User> query = session.createNativeQuery(sql, User.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> getAllCustomers() {
        try (Session session = this.sessionFactory.openSession()) {
            // Use native SQL query to select all customers
            String sql = "SELECT * FROM customers";
            NativeQuery<User> query = session.createNativeQuery(sql, User.class);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }
        return null;
    }

    @Override
    public User getCustomerByEmailAndPassword(String email, String password) {
        try (Session session = this.sessionFactory.openSession()) {
            // Use native SQL query to select all customers
            String sql = "SELECT * FROM users WHERE email = :email AND password = :password";
            NativeQuery<User> query = session.createNativeQuery(sql, User.class);
            query.setParameter("email", email);
            query.setParameter("password", password);
            return query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }
        return null;
    }
}
