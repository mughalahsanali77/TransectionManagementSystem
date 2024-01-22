package com.transaction.common.dto;

import java.util.List;

public class PaginationResponse {
    private Long totalRecords;
    private Integer totalPages;
    private List data;

    public PaginationResponse() {

    }

    public PaginationResponse(Long totalRecords, Integer totalPages, List data) {
        this.totalRecords = totalRecords;
        this.totalPages = totalPages;
        this.data = data;
    }

    public Long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
