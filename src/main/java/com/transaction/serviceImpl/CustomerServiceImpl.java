package com.transaction.serviceImpl;

import com.transaction.bean.Customer;
import com.transaction.common.dto.PaginationRequest;
import com.transaction.common.dto.PaginationResponse;
import com.transaction.common.exception.CustomerException;
import com.transaction.dao.CustomerDao;
import com.transaction.service.CustomerService;

import java.util.List;
import java.util.Objects;

public class CustomerServiceImpl implements CustomerService {

    @Override
    public Customer create(Customer customer) {
        if (Objects.isNull(customer))
            throw new CustomerException("Customer cannot be null");
        Customer inserted= CustomerDao.create(customer);
        return inserted;
    }

    @Override
    public Customer update(Customer customer, Integer customerId) {
        if (Objects.isNull(customer) || Objects.isNull(customerId))
            throw new CustomerException("Customer or Customer id cannot be null");
        Customer found=CustomerDao.getById(customerId);
        if (Objects.isNull(found)){
            throw new CustomerException("Customer not found with given id");
        }
        return CustomerDao.update(customer,customerId);
    }

    @Override
    public Customer delete(Integer id) {
        Customer customer=getById(id);
        if (Objects.nonNull(customer)){
            CustomerDao.delete(id);
        }
        return customer;
    }

    @Override
    public Customer getById(Integer id) {
        if (Objects.isNull(id))
            throw new CustomerException("id cannot be null");
        Customer customer=CustomerDao.getById(id);
        if (Objects.isNull(customer))
            throw new CustomerException("Customer Not Found With given id");
        return customer;
    }

    @Override
    public List<Customer> get() {
        return CustomerDao.getAll();
    }

    @Override
    public PaginationResponse get(PaginationRequest paginationRequest) {
        PaginationResponse response= CustomerDao.getAllWithPagination(paginationRequest);
        return response;
    }

}
