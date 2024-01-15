package com.transaction.dao;

import com.transaction.bean.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDao {
    private static Connection connection;

    public CustomerDao(Connection connection) {
        this.connection = connection;
    }

    public static Customer create(Customer customer){
        String sql="INSERT INTO CUSTOMER (FIRST_NAME, LAST_NAME, CITY, STATE, ADDRESS, CONTACT_NUMBER) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,customer.getFirstName());
            preparedStatement.setString(2,customer.getLastName());
            preparedStatement.setString(3,customer.getCity());
            preparedStatement.setString(4,customer.getState());
            preparedStatement.setString(5,customer.getAddress());
            preparedStatement.setString(6,customer.getContactNumber());
            preparedStatement.executeQuery();

            // Get the generated keys (including auto-generated CUSTOMER_ID)
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                // Update the Customer object with the generated CUSTOMER_ID
                customer.setCustomerId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Failed to get the auto-generated CUSTOMER_ID.");
            }

            System.out.println("Customer inserted successfully.");
            return customer;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }


}
