package com.transaction.bean;

import java.util.Date;

public class Account {
    private String accountNo;
    private Integer pinCode;
    private Date dateOfCreate;
    private String accountType;
    private Long amount;
    private Integer customerId;

    public Account() {
    }

    public Account(String accountNo, Integer pinCode, Date dateOfCreate, String accountType, Long amount, Integer customerId) {
        this.accountNo = accountNo;
        this.pinCode = pinCode;
        this.dateOfCreate = dateOfCreate;
        this.accountType = accountType;
        this.amount = amount;
        this.customerId = customerId;
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

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
