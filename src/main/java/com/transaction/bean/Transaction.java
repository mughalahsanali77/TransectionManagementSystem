package com.transaction.bean;

import java.util.Date;

public class Transaction {
    private String trxId;
    private Date dateOfTransfer;
    private Long amount;
    private String senderAccountNumber;
    private String receiverAccountNumber;

    public Transaction() {
    }

    public Transaction(String trxId, Date dateOfTransfer, Long amount, String senderAccountNumber, String receiverAccountNumber) {
        this.trxId = trxId;
        this.dateOfTransfer = dateOfTransfer;
        this.amount = amount;
        this.senderAccountNumber = senderAccountNumber;
        this.receiverAccountNumber = receiverAccountNumber;
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

    public String getSenderAccountNumber() {
        return senderAccountNumber;
    }

    public void setSenderAccountNumber(String senderAccountNumber) {
        this.senderAccountNumber = senderAccountNumber;
    }

    public String getReceiverAccountNumber() {
        return receiverAccountNumber;
    }

    public void setReceiverAccountNumber(String receiverAccountNumber) {
        this.receiverAccountNumber = receiverAccountNumber;
    }
}
