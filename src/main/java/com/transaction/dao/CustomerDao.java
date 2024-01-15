package com.transaction.dao;

import com.transaction.bean.Customer;
import com.transaction.helper.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {

    public static Customer create(Customer customer) {
        String sql = "INSERT INTO CUSTOMER (FIRST_NAME, LAST_NAME, CITY, STATE, ADDRESS, CONTACT_NUMBER) VALUES (?,?,?,?,?,?)";
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getCity());
            preparedStatement.setString(4, customer.getState());
            preparedStatement.setString(5, customer.getAddress());
            preparedStatement.setString(6, customer.getContactNumber());

            // Execute the insert statement
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                // Get the generated keys (including auto-generated CUSTOMER_ID)
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    // Update the Customer object with the generated CUSTOMER_ID
                    customer.setCustomerId(generatedKeys.getInt(1));
                    System.out.println("Customer inserted successfully.");
                    return customer;
                } else {
                    throw new SQLException("Failed to get the auto-generated CUSTOMER_ID.");
                }
            } else {
                throw new SQLException("Failed to insert customer.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Customer> getAll() {
        List<Customer> listOfCustomers = new ArrayList<>();
        String sql = "SELECT CUSTOMER_ID,FIRST_NAME,LAST_NAME,CITY,STATE,ADDRESS,CONTACT_NUMBER FROM CUSTOMER";
        try (Connection connection = ConnectionProvider.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Customer customer = new Customer(
                        resultSet.getInt("CUSTOMER_ID"),
                        resultSet.getString("FIRST_NAME"),
                        resultSet.getString("LAST_NAME"),
                        resultSet.getString("CITY"),
                        resultSet.getString("STATE"),
                        resultSet.getString("ADDRESS"),
                        resultSet.getString("CONTACT_NUMBER"));
                listOfCustomers.add(customer);
            }
        } catch (SQLException s) {
            s.printStackTrace();
        }
        return listOfCustomers;
    }

}
