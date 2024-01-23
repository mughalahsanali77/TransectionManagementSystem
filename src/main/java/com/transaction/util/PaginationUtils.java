package com.transaction.util;


import com.transaction.common.dto.PaginationRequest;
import com.transaction.common.exception.CustomException;

import java.sql.*;

public class PaginationUtils {
    public static Integer totalPages(Long totalRecords,Integer itemsPerPage){
        return Math.toIntExact(totalRecords / itemsPerPage);
    }

    public static Integer offset(PaginationRequest request){
        return request.getCurrentPage()* request.getItemsPerPage();
    }


    public static Long getTotalRecords(String tableName){
        String sql="SELECT COUNT(*) FROM "+tableName;
        Long totalRecords=null;
        try(Connection connection=ConnectionProvider.getConnection();
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(sql)) {
            if (resultSet.next()){
                totalRecords=resultSet.getLong(1);
            }else {
               throw new CustomException("Result set has no data");
            }
        }catch (SQLException e){
            System.err.println("Error occurred : "+ e.getMessage());
        }finally {
            ConnectionProvider.closeConnection();
        }
        return totalRecords;
    }

    public static void setPaginationRequestParameter(PreparedStatement preparedStatement,PaginationRequest paginationRequest) throws SQLException {
        preparedStatement.setString(1,paginationRequest.getSortBy());
        preparedStatement.setString(2,paginationRequest.getDirection());
        preparedStatement.setInt(3,paginationRequest.getItemsPerPage());
        preparedStatement.setInt(4,offset(paginationRequest));
    }
}
