package com.transaction.common.service;

import com.transaction.common.bean.ResponseBean;
import com.transaction.common.dto.PaginationRequest;
import com.transaction.common.dto.PaginationResponse;

import java.util.List;

public interface CrudService <T,ID>{
    ResponseBean<T> create(T t);
    ResponseBean<T> update(T t,ID id);
    ResponseBean<T> delete(ID id);
    ResponseBean<T> getById(ID id);
    ResponseBean<List<T>> get();
    ResponseBean<PaginationResponse> get(PaginationRequest paginationRequest);
}
