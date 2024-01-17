package com.transaction.common;

public class PaginationRequest {
    private Integer itemsPerPage;
    private Integer currentPage;
    private String sortBy;
    private String direction;

    public PaginationRequest() {
    }

    public PaginationRequest(Integer itemsPerPage, Integer currentPage, String sortBy, String direction) {
        this.itemsPerPage = itemsPerPage;
        this.currentPage = currentPage;
        this.sortBy = sortBy;
        this.direction = direction;
    }

    public Integer getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(Integer itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
