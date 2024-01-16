package com.transaction;

import com.transaction.bean.Customer;
import com.transaction.dao.CustomerDao;

import java.util.List;
import java.util.Objects;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//     List<Customer> customers=CustomerDao.getAll();
//     for (Customer customer:customers){
//         System.out.println(customer);
//     }
        Customer byId = CustomerDao.getById(4);
        if (byId!=null){
            CustomerDao.delete(4);
        }else {
            System.out.println("Customer not exists");
        }
    }
}
