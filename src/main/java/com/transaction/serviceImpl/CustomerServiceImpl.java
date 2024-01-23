package com.transaction.serviceImpl;

import com.transaction.bean.Customer;
import com.transaction.common.bean.ResponseBean;
import com.transaction.common.dto.PaginationRequest;
import com.transaction.common.dto.PaginationResponse;
import com.transaction.common.exception.CustomException;
import com.transaction.dao.CustomerDao;
import com.transaction.service.CustomerService;
import com.transaction.util.StringUtils;

import java.util.List;
import java.util.Objects;

public class CustomerServiceImpl implements CustomerService {

    @Override
    public ResponseBean<Customer> create(Customer customer) {
        try {
            validateCustomer(customer);
            Customer inserted = CustomerDao.create(customer);
            return new ResponseBean<>(0, "Success", inserted);
        } catch (CustomException e) {
            return new ResponseBean<>(1, e.getMessage());
        } catch (Exception e) {
            return new ResponseBean<>(1, e.getMessage());
        }
    }

    @Override
    public ResponseBean<Customer> update(Customer customer, Integer id) {
        try {
            if (Objects.isNull(id))
                throw new CustomException("Id cannot be null , to check existing record");
            Customer byId = CustomerDao.getById(id);
            if (Objects.isNull(byId))
                throw new CustomException("Record not found with given id");
            validateCustomer(customer);
            Customer update = CustomerDao.update(customer, id);
            return new ResponseBean<>(0, "Success", update);
        } catch (CustomException e) {
            return new ResponseBean<>(1, e.getMessage());
        } catch (Exception e) {
            return new ResponseBean<>(1, e.getMessage());
        }
    }

    @Override
    public ResponseBean<Customer> delete(Integer id) {
        try {
            if (Objects.isNull(id))
                throw new CustomException("Id cannot be null");
            Customer customer = CustomerDao.getById(id);
            if (Objects.isNull(customer))
                throw new CustomException("Customer Not Found with given Id");
            CustomerDao.delete(id);
            return new ResponseBean<>(0, "Success", customer);
        } catch (CustomException e) {
            return new ResponseBean<>(1, e.getMessage());
        } catch (Exception e) {
            return new ResponseBean<>(1, e.getMessage());
        }
    }

    @Override
    public ResponseBean<Customer> getById(Integer id) {
        try {
            if (Objects.isNull(id))
                throw new CustomException("id cannot be null");
            Customer customer = CustomerDao.getById(id);
            return new ResponseBean<>(0, "Success", customer);
        } catch (CustomException e) {
            return new ResponseBean<>(1, e.getMessage());
        } catch (Exception e) {
            return new ResponseBean<>(1, e.getMessage());
        }
    }

    @Override
    public ResponseBean<List<Customer>> get() {
        try {
            List<Customer> customers = CustomerDao.getAll();
            return new ResponseBean<>(0, "Success", customers);
        } catch (Exception e) {
            return new ResponseBean<>(1, e.getMessage());
        }
    }

    @Override
    public ResponseBean<PaginationResponse> get(PaginationRequest paginationRequest) {
        try {
            PaginationResponse paginationResponse = CustomerDao.getAllWithPagination(paginationRequest);
            return new ResponseBean<>(0, "Success", paginationResponse);
        } catch (CustomException e) {
            return new ResponseBean<>(1, e.getMessage());
        } catch (Exception e) {
            return new ResponseBean<>(1, e.getMessage());
        }
    }

    private void validateCustomer(Customer customer) {
        if (Objects.isNull(customer))
            throw new CustomException("Customer cannot be null");
        if (StringUtils.isEmptyOrNull(customer.getContactNumber()))
            throw new CustomException("First Name  cannot be Null");
        if (StringUtils.isEmptyOrNull(customer.getLastName()))
            throw new CustomException("Last cannot be null");
        if (StringUtils.isEmptyOrNull(customer.getAddress()))
            throw new CustomException("Address cannot be null");
        if (StringUtils.isEmptyOrNull(customer.getCity()))
            throw new CustomException("city cannot be null");
        if (StringUtils.isEmptyOrNull(customer.getState()))
            throw new CustomException("State cannot be null");
        if (StringUtils.isEmptyOrNull(customer.getContactNumber()))
            throw new CustomException("contact number cannot be null");
    }
}
