package com.transaction.serviceImpl;

import com.transaction.bean.Account;
import com.transaction.bean.Transaction;
import com.transaction.common.bean.ResponseBean;
import com.transaction.common.dto.PaginationRequest;
import com.transaction.common.dto.PaginationResponse;
import com.transaction.common.exception.CustomException;
import com.transaction.common.util.DateUtils;
import com.transaction.common.util.StringUtils;
import com.transaction.dao.AccountDao;
import com.transaction.dao.TransactionDao;
import com.transaction.service.TransactionService;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class TransactionServiceImpl implements TransactionService {
    @Override
    public ResponseBean<Transaction> create(Transaction transaction) {
        try{
            transaction= validateTransaction(transaction);
            transaction.setTrxId(formattedTxrId(new Date()));
            transaction.setDateOfTransfer(new Date());
            Transaction inserted = TransactionDao.create(transaction);
            return new ResponseBean<>(0,"Success",inserted);
        }catch (CustomException e){
            return new ResponseBean<>(1,e.getMessage());
        }catch (Exception e){
            return new ResponseBean<>(1,e.getMessage());
        }
    }

    @Override
    public ResponseBean<Transaction> update(Transaction transaction, String s) {
        return null;
    }

    @Override
    public ResponseBean<Transaction> delete(String s) {
        return null;
    }

    @Override
    public ResponseBean<Transaction> getById(String s) {
        return null;
    }

    @Override
    public ResponseBean<List<Transaction>> get() {
        return null;
    }

    @Override
    public ResponseBean<PaginationResponse> get(PaginationRequest paginationRequest) {
        return null;
    }

    private Transaction validateTransaction(Transaction transaction){
        if (Objects.isNull(transaction.getSenderAccount()) ||
                StringUtils.isEmptyOrNull(transaction.getSenderAccount().getAccountNo()))
            throw new CustomException("Sender account or sender account no cannot be null");
        if (Objects.isNull(transaction.getReceiverAccount()) ||
                StringUtils.isEmptyOrNull(transaction.getReceiverAccount().getAccountNo()))
            throw new CustomException("receiver account or receiver account no cannot be null");
        if (transaction.getAmount()==null)
            throw new CustomException("Amount for transaction cannot be null");
        Account sender = AccountDao.getByAccountNumber(transaction.getSenderAccount().getAccountNo());
        if (Objects.isNull(sender))
            throw new CustomException("Sender account not found with id : "+transaction.getSenderAccount().getAccountNo());
        Account receiver = AccountDao.getByAccountNumber(transaction.getReceiverAccount().getAccountNo());
        if (Objects.isNull(receiver))
            throw new CustomException("Sender account not found with id : "+transaction.getSenderAccount().getAccountNo());
        if (sender.getAmount()< transaction.getAmount())
            throw new CustomException("Sender have insufficient balance");
        List<Account> accounts = AccountDao.updateAmount(sender.getAccountNo(), receiver.getAccountNo(), transaction.getAmount());
        if (Objects.nonNull(accounts)){
            System.out.println("Accounts");
            accounts.forEach(account -> {
                System.out.println(account);
            });

            transaction.setSenderAccount(accounts.get(0));
            transaction.setReceiverAccount(accounts.get(1));
        }
        return transaction;
    }

    private final String formattedTxrId(Date date){
        try {
            Long counter= TransactionDao.getCountOfTransactionsByDate(date);
            String formattedDate = DateUtils.formatDate(date);
            return "TRX-ID-"+(++counter)+"-"+formattedDate;
        }catch (Exception e){
            System.err.println("Error occurred:"+e.getMessage());
        }
        return null;
    }
}
