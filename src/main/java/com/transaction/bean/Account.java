package com.transaction.bean;

import com.transaction.common.util.Utils;

import java.util.Date;
import java.util.Objects;

public class Account {
    private String accountNo;
    private Integer pinCode;
    private Date dateOfCreate;
    private String accountType;
    private Long amount;
    //private Integer customerId;

    private Customer customer;
    public Account() {
    }

    public Account(String accountNo, Integer pinCode, Date dateOfCreate, String accountType, Long amount, Customer customer) {
        this.accountNo = accountNo;
        this.pinCode = pinCode;
        this.dateOfCreate = dateOfCreate;
        this.accountType = accountType;
        this.amount = amount;
        this.customer=customer;
    }

    public Account(Integer pinCode, String accountType, Long amount, Customer customer) {
        this.pinCode = pinCode;
        this.accountType = accountType;
        this.amount = amount;
        this.customer = customer;
    }

    public Account(String accountNo, Integer pinCode, Date dateOfCreate, String accountType, Long amount, Integer customerId) {
        this.accountNo = accountNo;
        this.pinCode = pinCode;
        this.dateOfCreate = dateOfCreate;
        this.accountType = accountType;
        this.amount = amount;
        if (Objects.nonNull(customerId)){
            Customer customer=new Customer();
            customer.setCustomerId(customerId);
            this.customer=customer;
        }
    }

    public Account(String accountNo, Integer pinCode, Customer customer,String accountType) {
        this.accountNo = accountNo;
        this.pinCode = pinCode;
        this.customer = customer;
        this.accountType=accountType;
    }

    public Account(String accountNo, Integer pinCode, Date dateOfCreate, String accountType, Long amount) {
        this.accountNo = accountNo;
        this.pinCode = pinCode;
        this.dateOfCreate = dateOfCreate;
        this.accountType = accountType;
        this.amount = amount;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public Integer getPinCode() {
        return pinCode;
    }

    public void setPinCode(Integer pinCode) {
        this.pinCode = pinCode;
    }

    public Date getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(Date dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getAccountNoPinCodeCustomer(){
        return "ACCOUNT DETAILS ("+
                "\nACCOUNT_NO = "+accountNo+
                "\nPIN_CODE = "+Utils.getPinCodeStarts(pinCode)+
                "\nCUSTOMER = "+customer.getFirstName()+
                "\nACCOUNT_TYPE = "+accountType;
    }
    @Override
    public String toString() {
        return "ACCOUNT_DETAIL (" +
                "\nACCOUNT_NO = " + accountNo +
                "\nPIN_CODE = " + Utils.getPinCodeStarts(pinCode) +
                "\nDATE_OF_CREATE = " + dateOfCreate +
                "\nACCOUNT_TYPE = " + accountType +
                "\nAMOUNT = " + amount +
                "\nCUSTOMER = " + customer+ ")";
    }
    public String toStringWithCustomerId() {
        return "ACCOUNT_DETAIL (" +
                "\nACCOUNT_NO = " + accountNo +
                "\nPIN_CODE = " + Utils.getPinCodeStarts(pinCode) +
                "\nDATE_OF_CREATE = " + dateOfCreate +
                "\nACCOUNT_TYPE = " + accountType +
                "\nAMOUNT = " + amount +
                "\nCUSTOMER = " + customer.getCustomerId()+ ")";
    }
}
