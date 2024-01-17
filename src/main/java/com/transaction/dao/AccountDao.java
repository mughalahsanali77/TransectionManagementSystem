package com.transaction.dao;

import com.transaction.bean.Account;
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
                Account account=getAccountFromResultSet(resultSet);
                accounts.add(account);
            }
        }catch (SQLException e){
            System.err.println("Error occurred : "+e.getMessage());
        }finally {
            ConnectionProvider.closeConnection();
        }
        return accounts;
    }

    public static Account getByAccountNumber(String accountNo){
        Account account=new Account();
        String sql="SELECT ACCOUNT_NO,PIN_CODE,DATE_OF_CREATE,AMOUNT,ACCOUNT_TYPE,CUSTOMER_ID FROM ACCOUNT WHERE ACCOUNT_NO=?";
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
    private static Account getAccountFromResultSet(ResultSet resultSet) throws SQLException {
        return new Account(
                resultSet.getString("ACCOUNT_NO"),
                resultSet.getInt("PIN_CODE"),
                resultSet.getDate("DATE_OF_CREATE"),
                resultSet.getString("ACCOUNT_TYPE"),
                resultSet.getLong("AMOUNT"),
                resultSet.getInt("CUSTOMER_ID")
        );
    }

    private static void setAccountParameters(PreparedStatement preparedStatement,Account account) throws SQLException {
        preparedStatement.setString(1,account.getAccountNo());
        preparedStatement.setInt(2,account.getPinCode());
        preparedStatement.setDate(3, new java.sql.Date(account.getDateOfCreate().getTime()));
        preparedStatement.setString(4,account.getAccountType());
        preparedStatement.setLong(5,account.getAmount());
        preparedStatement.setInt(6,account.getCustomerId());
    }
}
