package com.transaction.dao;

import com.transaction.bean.Account;
import com.transaction.bean.Customer;
import com.transaction.bean.Transaction;
import com.transaction.common.dto.PaginationRequest;
import com.transaction.common.dto.PaginationResponse;
import com.transaction.common.exception.CustomException;
import com.transaction.common.util.PaginationUtils;
import com.transaction.helper.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDao {

    public static Transaction get(String trxId) {
        Transaction transaction = new Transaction();
        String sql = "SELECT T.TRX_ID AS TRANSACTION_ID, T.AMOUNT AS TRANSACTION_AMOUNT , T.DATE_OF_TRANSFER AS TRANSACTION_DATE_OF_TRANSFER," +
                "SENDER_ACCOUNT.ACCOUNT_NO AS SENDER_ACCOUNT_NUMBER ,SENDER_ACCOUNT.ACCOUNT_TYPE AS SENDER_ACCOUNT_TYPE," +
                "SENDER_CUSTOMER.CUSTOMER_ID AS SENDER_CUSTOMER_ID ,SENDER_CUSTOMER.FIRST_NAME SENDER_NAME,SENDER_CUSTOMER.CONTACT_NUMBER AS SENDER_CONTACT," +
                "RECEIVER_ACCOUNT.ACCOUNT_NO AS RECEIVER_ACCOUNT_NUMBER , RECEIVER_ACCOUNT.ACCOUNT_TYPE AS RECEIVER_ACCOUNT_TYPE," +
                "RECEIVER_CUSTOMER.CUSTOMER_ID AS RECEIVER_CUSTOMER_ID,RECEIVER_CUSTOMER.FIRST_NAME AS RECEIVER_NAME,RECEIVER_CUSTOMER.CONTACT_NUMBER AS RECEIVER_CONTACT" +
                " FROM TRANSACTION T " +
                "JOIN ACCOUNT AS SENDER_ACCOUNT ON T.SENDER_ACCOUNT_NUMBER = SENDER_ACCOUNT.ACCOUNT_NO " +
                "JOIN CUSTOMER AS SENDER_CUSTOMER ON SENDER_ACCOUNT.CUSTOMER_ID = SENDER_CUSTOMER.CUSTOMER_ID " +
                "JOIN ACCOUNT AS RECEIVER_ACCOUNT ON T.RECEIVER_ACCOUNT_NUMBER = RECEIVER_ACCOUNT.ACCOUNT_NO " +
                "JOIN CUSTOMER AS RECEIVER_CUSTOMER ON RECEIVER_ACCOUNT.CUSTOMER_ID = RECEIVER_CUSTOMER.CUSTOMER_ID " +
                "WHERE T.TRX_ID = ?";
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, trxId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                transaction = setAccountParametersFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("Error occur : " + e.getMessage());
            transaction = null;
        } finally {
            ConnectionProvider.closeConnection();
            ;
        }
        return transaction;
    }

    public static List<Transaction> get() {
        String sql = "SELECT TRX_ID,AMOUNT,DATE_OF_TRANSFER,SENDER_ACCOUNT_NUMBER,RECEIVER_ACCOUNT_NO FROM TRANSACTION";
        try (Connection connection = ConnectionProvider.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            return getListFromResultSet(resultSet);
        } catch (SQLException e) {
            System.err.println("Error occurred : " + e.getMessage());
            return null;
        } finally {
            ConnectionProvider.closeConnection();
        }
    }

    public PaginationResponse get(PaginationRequest request) {
        Long totalRecords = PaginationUtils.getTotalRecords("TRANSACTION");
        Integer totalPages = PaginationUtils.totalPages(totalRecords, request.getItemsPerPage());
        if (request.getCurrentPage() > totalPages) {
            throw new CustomException("CURRENT PAGE VALUE CANNOT BE " + request.getCurrentPage() + " BECAUSE ONLY " + totalPages + " PAGES EXISTS");
        }
        String sql = "SELECT TRX_ID,AMOUNT,DATE_OF_TRANSFER,SENDER_ACCOUNT_NUMBER,RECEIVER_ACCOUNT_NO" +
                " FROM TRANSACTION " +
                "ORDER BY ? ? LIMIT ? OFFSET ?";
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            PaginationUtils.setPaginationRequestParameter(preparedStatement, request);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Transaction> data = getListFromResultSet(resultSet);
            return new PaginationResponse(totalRecords, totalPages, data);
        } catch (SQLException e) {
            System.err.println("Error occurred : " + e.getMessage());
            return null;
        } finally {
            ConnectionProvider.closeConnection();
        }
    }

    private static Transaction setAccountParametersFromResultSet(ResultSet resultSet) throws SQLException {
        Account senderAccount = new Account();
        senderAccount.setAccountNo(resultSet.getString("SENDER_ACCOUNT_NUMBER"));
        senderAccount.setAccountType(resultSet.getString("SENDER_ACCOUNT_TYPE"));

        Customer senderCustomer = new Customer();
        senderCustomer.setCustomerId(resultSet.getInt("SENDER_CUSTOMER_ID"));
        senderCustomer.setFirstName(resultSet.getString("SENDER_NAME"));
        senderCustomer.setContactNumber(resultSet.getString("SENDER_CONTACT"));
        senderAccount.setCustomer(senderCustomer);

        Account receiverAccount = new Account();
        receiverAccount.setAccountNo(resultSet.getString("RECEIVER_ACCOUNT_NUMBER"));
        receiverAccount.setAccountType(resultSet.getString("RECEIVER_ACCOUNT_TYPE"));

        Customer receiverCustomer = new Customer();
        receiverCustomer.setCustomerId(resultSet.getInt("RECEIVER_CUSTOMER_ID"));
        receiverCustomer.setFirstName(resultSet.getString("RECEIVER_NAME"));
        receiverCustomer.setContactNumber(resultSet.getString("RECEIVER_CONTACT"));
        receiverAccount.setCustomer(receiverCustomer);
        return new Transaction(
                resultSet.getString("TRANSACTION_ID"),
                resultSet.getDate("TRANSACTION_DATE_OF_TRANSFER"),
                resultSet.getLong("TRANSACTION_AMOUNT"),
                senderAccount,
                receiverAccount
        );
    }

    private static List<Transaction> getListFromResultSet(ResultSet resultSet) throws SQLException {
        List<Transaction> transactions = new ArrayList<>();
        while (resultSet.next()) {
            Transaction transaction = new Transaction(
                    resultSet.getString("TRX_ID"),
                    resultSet.getDate("DATE_OF_TRANSFER"),
                    resultSet.getLong("AMOUNT"),
                    resultSet.getString("SENDER_ACCOUNT_NO"),
                    resultSet.getString("RECEIVER_ACCOUNT_NO")
            );
            transactions.add(transaction);
        }
        return transactions;
    }
}
