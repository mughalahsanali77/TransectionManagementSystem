package com.transaction.bean;

import java.util.Date;

public class Transaction {
    private String trxId;
    private Date dateOfTransfer;
    private Long amount;
    private Account senderAccount;
    private Account receiverAccount;

    public Transaction() {
    }

    public Transaction(String trxId, Date dateOfTransfer, Long amount, Account senderAccount, Account receiverAccount) {
        this.trxId = trxId;
        this.dateOfTransfer = dateOfTransfer;
        this.amount = amount;
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
    }

    public Transaction(String trxId, Date dateOfTransfer, Long amount, String senderAccountNo, String receiverAccountNo) {
        this.trxId = trxId;
        this.dateOfTransfer = dateOfTransfer;
        this.amount = amount;
        this.senderAccount.setAccountNo(senderAccountNo);
        this.receiverAccount.setAccountNo(receiverAccountNo);
    }

    public String getTrxId() {
        return trxId;
    }

    public void setTrxId(String trxId) {
        this.trxId = trxId;
    }

    public Date getDateOfTransfer() {
        return dateOfTransfer;
    }

    public void setDateOfTransfer(Date dateOfTransfer) {
        this.dateOfTransfer = dateOfTransfer;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Account getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(Account senderAccount) {
        this.senderAccount = senderAccount;
    }

    public Account getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccount(Account receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "trxId='" + trxId + '\'' +
                ", dateOfTransfer=" + dateOfTransfer +
                ", amount=" + amount +
                ", senderAccount=" + senderAccount.toString() +
                ", receiverAccount=" + receiverAccount.toString() +
                '}';
    }
}
