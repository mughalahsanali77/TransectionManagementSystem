package com.transaction.dao;

import com.transaction.bean.Account;
import com.transaction.bean.Customer;
import com.transaction.util.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {

    public static Account create(Account account){
        String sql="INSERT INTO ACCOUNT (ACCOUNT_NO,PIN_CODE,DATE_OF_CREATE,ACCOUNT_TYPE,AMOUNT,CUSTOMER_ID) VALUES(?,?,?,?,?,?)";
        try(Connection connection= ConnectionProvider.getConnection();
            PreparedStatement preparedStatement= connection.prepareStatement(sql)) {
            setAccountParameters(preparedStatement,account);
            int rowAffected = preparedStatement.executeUpdate();
            if (rowAffected>0){
                return account;
            }else {
                throw new SQLException("FAILED TO INSERT ACCOUNT`S DETAILS");
            }
        }catch (SQLException e){
            System.err.println("Error occurred : "+e.getMessage());
            return null;
        }
    }
    public static List<Account> getAll(){
        List<Account> accounts=new ArrayList<>();
        String sql="SELECT ACCOUNT_NO,PIN_CODE,DATE_OF_CREATE,ACCOUNT_TYPE,AMOUNT,CUSTOMER_ID FROM ACCOUNT";
        try(Connection connection=ConnectionProvider.getConnection();
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(sql)) {
            while (resultSet.next()){
                Account account = new Account(
                        resultSet.getString("ACCOUNT_NO"),
                        resultSet.getInt("PIN_CODE"),
                        resultSet.getDate("DATE_OF_CREATE"),
                        resultSet.getString("ACCOUNT_TYPE"),
                        resultSet.getLong("AMOUNT"),
                        resultSet.getInt("CUSTOMER_ID")
                        );
                accounts.add(account);
            }
        }catch (SQLException e){
            System.err.println("Error occurred : "+e.getMessage());
        }finally {
            ConnectionProvider.closeConnection();
        }
        return accounts;
    }
    public static List<Account>getAllAccountsWithCustomersDetails(){
        List <Account> accounts=new ArrayList<>();
        String sql="SELECT A.ACCOUNT_NO,A.ACCOUNT_TYPE,A.PIN_CODE,A.AMOUNT,A.DATE_OF_CREATE," +
                "C.CUSTOMER_ID,C.FIRST_NAME,C.LAST_NAME,C.CITY,C.STATE,C.ADDRESS,C.CONTACT_NUMBER " +
                "FROM ACCOUNT A " +
                "JOIN CUSTOMER C " +
                "WHERE A.CUSTOMER_ID=C.CUSTOMER_ID";
        try (Connection connection=ConnectionProvider.getConnection();
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery(sql)){
            while (resultSet.next()){
                Account account=getAccountFromResultSet(resultSet);
                accounts.add(account);
            }
            return accounts;
        }catch (SQLException e){
            System.err.println("Error occurred : "+e.getMessage());
            return null;
        }finally {
            ConnectionProvider.closeConnection();
        }
    }

    public static Account getByAccountNumber(String accountNo){
        Account account=new Account();
        String sql="SELECT A.ACCOUNT_NO,A.PIN_CODE,A.DATE_OF_CREATE,A.ACCOUNT_TYPE,A.AMOUNT," +
                "C.CUSTOMER_ID ,C.FIRST_NAME,C.LAST_NAME,C.CITY,C.STATE,C.ADDRESS,C.CONTACT_NUMBER " +
                "FROM ACCOUNT A " +
                "JOIN CUSTOMER C " +
                "ON A.CUSTOMER_ID = C.CUSTOMER_ID " +
                "WHERE A.ACCOUNT_NO = ?";
        try(Connection connection=ConnectionProvider.getConnection();
        PreparedStatement preparedStatement= connection.prepareStatement(sql)) {
            preparedStatement.setString(1,accountNo);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                account=getAccountFromResultSet(resultSet);
            }else {
                System.err.println("ACCOUNT NOT FOUND WITH GIVEN ACCOUNT_NO : "+accountNo);
                return null;
            }

        }catch (SQLException e){
            System.err.println("Error occurred : "+e.getMessage());
            return null;
        }finally {
            ConnectionProvider.closeConnection();
        }
        return account;
    }

    public static Account update(Account account,String accountNo){
        String sql="UPDATE ACCOUNT SET PIN_CODE=? , ACCOUNT_TYPE=? , CUSTOMER_ID =? WHERE ACCOUNT_NO=?";
        try (Connection connection=ConnectionProvider.getConnection();PreparedStatement preparedStatement=connection.prepareStatement(sql)){
            preparedStatement.setLong(1,account.getPinCode());
            preparedStatement.setString(2,account.getAccountType());
            preparedStatement.setInt(3,account.getCustomer().getCustomerId());
            preparedStatement.setString(4,accountNo);
            int rowsEffected = preparedStatement.executeUpdate();
            if (rowsEffected>0){
                account=getByAccountNumber(accountNo);
            }else {
                System.err.println("ACCOUNT NOT UPDATED");
                return null;
            }
        }catch (SQLException e){
            System.err.println("Error occurred : "+e.getMessage());
            return null;
        }finally {
            ConnectionProvider.closeConnection();
        }
        return account;
    }

    public static Account getAccountByAccountNoAndPinCode(String accountNo,Integer pinCode){
        String sql="SELECT A.ACCOUNT_NO , A.PIN_CODE ,A.ACCOUNT_TYPE,C.FIRST_NAME " +
                "FROM ACCOUNT A " +
                "JOIN CUSTOMER C " +
                "ON C.CUSTOMER_ID = A.CUSTOMER_ID " +
                "WHERE ACCOUNT_NO=? AND PIN_CODE=? ";
        try(Connection connection=ConnectionProvider.getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement(sql)) {
            preparedStatement.setString(1,accountNo);
            preparedStatement.setInt(2,pinCode);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                Customer customer=new Customer();
                customer.setFirstName(resultSet.getString("FIRST_NAME"));
              return   new Account(resultSet.getString("ACCOUNT_NO"),resultSet.getInt("PIN_CODE"),customer,resultSet.getString("ACCOUNT_TYPE"));
            }else {
                System.err.println("ACCOUNT NOT FOUND WITH GIVEN NUMBER AND PIN CODE");
                return null;
            }
        }catch (SQLException e){
            System.err.println("ERROR OCCURRED : "+e.getMessage());
            return null;
        }finally {
            ConnectionProvider.closeConnection();
        }
    }
    private static Account getAccountFromResultSet(ResultSet resultSet) throws SQLException {
        Customer customer=CustomerDao.createCustomerFromResultSet(resultSet);
        return new Account(
                resultSet.getString("ACCOUNT_NO"),
                resultSet.getInt("PIN_CODE"),
                resultSet.getDate("DATE_OF_CREATE"),
                resultSet.getString("ACCOUNT_TYPE"),
                resultSet.getLong("AMOUNT"),
                customer
        );
    }

    private static void setAccountParameters(PreparedStatement preparedStatement,Account account) throws SQLException {
        preparedStatement.setString(1,account.getAccountNo());
        preparedStatement.setInt(2,account.getPinCode());
        preparedStatement.setDate(3, new java.sql.Date(account.getDateOfCreate().getTime()));
        preparedStatement.setString(4,account.getAccountType());
        preparedStatement.setLong(5,account.getAmount());
        preparedStatement.setInt(6,account.getCustomer().getCustomerId());
    }
}
