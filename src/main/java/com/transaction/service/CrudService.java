package com.transaction.service;

import com.transaction.common.dto.PaginationRequest;
import com.transaction.common.dto.PaginationResponse;

import java.util.List;

public interface CrudService <T,ID>{
    T create(T t);
    T update(T t,ID id);
    T delete(ID id);
    T getById(ID id);
    List<T> get();
    PaginationResponse get(PaginationRequest paginationRequest);
}
