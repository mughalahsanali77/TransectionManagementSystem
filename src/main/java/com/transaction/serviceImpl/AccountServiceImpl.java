package com.transaction.serviceImpl;

import com.transaction.bean.Account;
import com.transaction.common.bean.ResponseBean;
import com.transaction.common.dto.PaginationRequest;
import com.transaction.common.dto.PaginationResponse;
import com.transaction.common.exception.CustomException;
import com.transaction.dao.AccountDao;
import com.transaction.service.AccountService;
import com.transaction.common.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class AccountServiceImpl implements AccountService {
    @Override
    public ResponseBean<Account> create(Account account) {
        try {
            validateAccount(account);
/*
            have to implement and set ACCOUNT-COUNTER-CURRENT_DATE
            account.setAccountNo();
*/
            account.setDateOfCreate(new Date());
            Account created = AccountDao.create(account);
            return new ResponseBean<>(0, "SUCCESS", created);
        } catch (CustomException e) {
            return new ResponseBean<>(1, e.getMessage());
        } catch (Exception e) {
            return new ResponseBean<>(1, e.getMessage());
        }
    }

    @Override
    public ResponseBean<Account> update(Account account, String accountNo) {
        try {
            if (StringUtils.isEmptyOrNull(accountNo))
                throw new CustomException("account number cannot be null");
            validateAccount(account);
            Account found = AccountDao.getByAccountNumber(accountNo);
            if (Objects.isNull(found))
                throw new CustomException("Account not found with given account no");
            Account updated = AccountDao.update(account, accountNo);
            return new ResponseBean<>(0, "Success", updated);
        } catch (CustomException e) {
            return new ResponseBean<>(1, e.getMessage());
        } catch (Exception e) {
            return new ResponseBean<>(1, e.getMessage());
        }

    }

    @Override
    public ResponseBean<Account> delete(String accountNo) {
        try {
            Account account = new Account();
            ResponseBean<Account> byId = getById(accountNo);
            if (Objects.nonNull(byId.getData())) {
                account = AccountDao.deleteAccount(byId.getData());
            } else {
                return null; //because response is given from getById
            }
            return new ResponseBean<>(0, "Success", account);

        } catch (CustomException e) {
            return new ResponseBean<>(1, e.getMessage());
        } catch (Exception e) {
            return new ResponseBean<>(1, e.getMessage());
        }
    }

    @Override
    public ResponseBean<Account> getById(String accountNo) {
        try {
            if (StringUtils.isEmptyOrNull(accountNo))
                throw new CustomException("Account no cannot be null");
            Account account = AccountDao.getByAccountNumber(accountNo);
            return new ResponseBean<>(0, "Success", account);
        } catch (CustomException e) {
            return new ResponseBean<>(1, e.getMessage());
        } catch (Exception e) {
            return new ResponseBean<>(1, e.getMessage());
        }
    }

    @Override
    public ResponseBean<List<Account>> get() {
        try {
            List<Account> accounts = AccountDao.getAll();
            return new ResponseBean<>(0, "Success", accounts);
        } catch (Exception e) {
            return new ResponseBean<>(1, e.getMessage());
        }
    }

    @Override
    public ResponseBean<PaginationResponse> get(PaginationRequest paginationRequest) {
        try {
            PaginationResponse paginationResponse = AccountDao.getAllWithPagination(paginationRequest);
            return new ResponseBean<>(0, "Success", paginationResponse);
        } catch (Exception e) {
            return new ResponseBean<>(1, e.getMessage());
        }
    }

    private void validateAccount(Account account) {
        if (Objects.isNull(account))
            throw new CustomException("account cannot be null");
        if (StringUtils.isEmptyOrNull(account.getAccountType()))
            throw new CustomException("account type cannot be null");
        if (Objects.isNull(account.getPinCode()))
            throw new CustomException("pin code cannot be null");
        if (Objects.isNull(account.getCustomer()) || Objects.isNull(account.getCustomer().getCustomerId()))
            throw new CustomException("Customer or customer id cannot be null");
    }

}
