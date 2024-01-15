package com.transaction.helper;

import com.transaction.common.Constant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class ConnectionProvider {
    private static Connection connection;

    public static Connection getConnection(){
        if (Objects.isNull(connection)){
            try{
                connection=DriverManager.getConnection(Constant.DATABASE_URL,Constant.DATABASE_USERNAME,Constant.DATABASE_PASSWORD);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeConnection(){
        if (Objects.nonNull(connection)){
            try {
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }finally {
                connection=null;
            }
        }
    }
}
