package com.transaction.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
    private static Connection connection;

    private ConnectionProvider() {
        // private constructor to prevent instantiation
    }

    public static Connection getConnection() {
        if (connection == null) {
            synchronized (ConnectionProvider.class) {
                if (connection == null) {
                    try {
                        connection = DriverManager.getConnection(Constant.DATABASE_URL, Constant.DATABASE_USERNAME, Constant.DATABASE_PASSWORD);
                    } catch (SQLException e) {
                        e.printStackTrace();
                        throw new RuntimeException("Failed to establish a database connection.", e);
                    }
                }
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                connection = null;
            }
        }
    }
}
