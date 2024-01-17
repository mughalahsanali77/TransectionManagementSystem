package com.transaction.dao;

import com.transaction.bean.Customer;
import com.transaction.common.PaginationRequest;
import com.transaction.util.ConnectionProvider;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {

    public static Customer create(Customer customer) {
        String sql = "INSERT INTO CUSTOMER (FIRST_NAME, LAST_NAME, CITY, STATE, ADDRESS, CONTACT_NUMBER) VALUES (?,?,?,?,?,?)";
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            setCustomerParameters(preparedStatement, customer);
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
            System.err.println("Error occurred: " + e.getMessage());

            return null;
        } finally {
            ConnectionProvider.closeConnection();
        }
    }

    public static List<Customer> getAll() {
        List<Customer> listOfCustomers = new ArrayList<>();
        String sql = "SELECT CUSTOMER_ID,FIRST_NAME,LAST_NAME,CITY,STATE,ADDRESS,CONTACT_NUMBER FROM CUSTOMER";
        try (Connection connection = ConnectionProvider.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Customer customer = createCustomerFromResultSet(resultSet);
                listOfCustomers.add(customer);
            }
        } catch (SQLException s) {
            System.err.println("Error occurred: " + s.getMessage());

        } finally {
            ConnectionProvider.closeConnection();
        }
        return listOfCustomers;
    }

    public static Customer getById(Integer id) {
        String sql = "SELECT CUSTOMER_ID,FIRST_NAME,LAST_NAME,CITY,STATE,ADDRESS,CONTACT_NUMBER FROM CUSTOMER WHERE CUSTOMER_ID =?";
        Customer customer = null;
        try (Connection connection = ConnectionProvider.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                customer = createCustomerFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.err.println("Error occurred: " + e.getMessage());
        } finally {
            ConnectionProvider.closeConnection();
        }
        return customer;
    }


    public static void delete(Integer id) {
        String sql = "DELETE FROM CUSTOMER WHERE CUSTOMER_ID = ?";
        try (Connection connection = ConnectionProvider.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int i = preparedStatement.executeUpdate();
            if (i<=0){
                throw new SQLException("Failed to delete");
            }
        } catch (SQLException e) {
            System.err.println("Error occurred: " + e.getMessage());
        } finally {
            ConnectionProvider.closeConnection();
        }
    }

    public static Customer update(Customer customer, Integer id) {
        String sql = "UPDATE CUSTOMER SET FIRST_NAME=?,LAST_NAME=?,CITY=?,STATE=?,ADDRESS=?,CONTACT_NUMBER=? WHERE CUSTOMER_ID=?";
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            setCustomerParameters(preparedStatement, customer);
            preparedStatement.setInt(7, id);
            int i = preparedStatement.executeUpdate();
            if (i>0){
                customer.setCustomerId(id);
            }else {
                throw new SQLException("Failed to Update");
            }
        } catch (SQLException e) {
            System.err.println("Error occurred :" + e.getMessage());
            customer=null;
        }finally {
            ConnectionProvider.closeConnection();
        }
        return customer;
    }

    public static List<Customer> getAllWithPagination(PaginationRequest page){
        List<Customer> customers=new ArrayList<>();
        Integer totalPages=totalPages(getTotalRecords(),page.getItemsPerPage());
        if (page.getCurrentPage()>totalPages){
            System.err.println("CURRENT PAGE IS GREATER THEN THE COUNT OF TOTAL PAGES\nONLY "+totalPages+" EXIST");
            return customers;
        }else {
            Integer offset=page.getCurrentPage()*page.getItemsPerPage();
            String sql="SELECT CUSTOMER_ID,FIRST_NAME,LAST_NAME,CITY,STATE,ADDRESS,CONTACT_NUMBER " +
                    "FROM CUSTOMER ORDER BY ? ? LIMIT ? OFFSET ?";
            try(Connection connection=ConnectionProvider.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
                preparedStatement.setString(1,page.getSortBy());
                preparedStatement.setString(2,page.getDirection());
                preparedStatement.setInt(3,page.getItemsPerPage());
                preparedStatement.setInt(4,offset);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    Customer customer=createCustomerFromResultSet(resultSet);
                    customers.add(customer);
                }
            }catch (SQLException e) {
                System.err.println("Error occurred : " + e.getMessage());
            }finally {
                ConnectionProvider.closeConnection();
            }
        }
        return customers;
    }

    private static Integer totalPages(Integer totalRecords,Integer itemsPerPage){
        return totalRecords/itemsPerPage;
    }
    private static Integer getTotalRecords(){
        String sql="SELECT COUNT(*) FROM CUSTOMER";
        Integer totalRecords=null;
        try(Connection connection=ConnectionProvider.getConnection();
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(sql)) {
            if (resultSet.next()){
                totalRecords=resultSet.getInt(1);
            }else {
                System.err.println("Result Set have nothing");
            }
        }catch (SQLException e){
            System.err.println("Error occurred : "+ e.getMessage());
        }finally {
            ConnectionProvider.closeConnection();
        }
        return totalRecords;
    }


    private static void setCustomerParameters(PreparedStatement preparedStatement, Customer customer) throws SQLException {
        preparedStatement.setString(1, customer.getFirstName());
        preparedStatement.setString(2, customer.getLastName());
        preparedStatement.setString(3, customer.getCity());
        preparedStatement.setString(4, customer.getState());
        preparedStatement.setString(5, customer.getAddress());
        preparedStatement.setString(6, customer.getContactNumber());
    }

    private static Customer createCustomerFromResultSet(ResultSet resultSet) throws SQLException {
        return new Customer(
                resultSet.getInt("CUSTOMER_ID"),
                resultSet.getString("FIRST_NAME"),
                resultSet.getString("LAST_NAME"),
                resultSet.getString("CITY"),
                resultSet.getString("STATE"),
                resultSet.getString("ADDRESS"),
                resultSet.getString("CONTACT_NUMBER")
        );
    }
}
