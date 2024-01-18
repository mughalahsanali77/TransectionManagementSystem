package com.transaction;

import com.transaction.bean.Account;
import com.transaction.bean.Customer;
import com.transaction.common.PaginationRequest;
import com.transaction.dao.AccountDao;
import com.transaction.dao.CustomerDao;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
//     List<Customer> customers=CustomerDao.getAll();
//     for (Customer customer:customers){
//         System.out.println(customer);
//     }
//        Customer byId = CustomerDao.getById(4);
//        if (byId!=null){
//            CustomerDao.delete(4);
//        }else {
//            System.out.println("Customer not exists");
//        }
//        List<Customer> customers=CustomerDao.getAllWithPagination(new PaginationRequest(2,4,"CUSTOMER_ID","DESC"));
//        for (Customer customer:customers){
//            System.out.println(customer.toString());
//        }

//        Account account= AccountDao.create(
//                new Account(
//                        "ACCOUNT-3",1234,new Date(),"DEBIT",2000L,6
//                )
//        );
//         System.out.println(account.toString());

//        Account account=AccountDao.getByAccountNumber("Account-2");
//        if (Objects.nonNull(account))
//            System.out.println(account.toString());

        ///after using join
//        Account account=AccountDao.getAccountByAccountNoAndPinCode("ACCOUNT-1",1234);
//        if (Objects.nonNull(account)){
//            System.out.println(account.getAccountNoPinCodeCustomer());
//        }

//        Account account=AccountDao.getByAccountNumber("ACCOUNT-1");
//        if (Objects.nonNull(account)){
//            System.out.println(account.toString());
//        }


//        List<Account>accounts=AccountDao.getAll();
//        for (Account account:accounts){
//            System.out.println("-------------------------------------");
//            System.out.println(account.toStringWithCustomerId());
//            System.out.println("-------------------------------------");
//        }

//        List<Account>accounts=AccountDao.getAllAccountsWithCustomersDetails();
//        for (Account account:accounts){
//            System.out.println("-------------------------------------");
//            System.out.println(account.toString());
//            System.out.println("-------------------------------------");
//        }

    }

}
