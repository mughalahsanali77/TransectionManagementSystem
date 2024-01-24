package com.transaction;

import com.transaction.bean.Account;
import com.transaction.bean.Transaction;
import com.transaction.dao.AccountDao;
import com.transaction.dao.TransactionDao;

import java.util.Objects;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
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
//
//        Customer customer = CustomerDao.getById(3);
//        if (Objects.nonNull(customer)) {
//            System.out.println(customer);
//
//            Account account = AccountDao.create(new Account("ACCOUNT-1", 1234, new Date(), "CURRENT", 2000L, customer));
//            if (Objects.nonNull(account)) {
//        List<Account> accounts = AccountDao.getAllAccountsWithCustomersDetails();
//        for (Account acc : accounts) {
//            System.out.println("-------------------------------------");
//            System.out.println(acc.toString());
//            System.out.println("-------------------------------------");
//        }

//        Transaction transaction= TransactionDao.getByTrxId("1");
//        if (transaction!=null)
//            System.out.println(transaction.toString());



    }

}
