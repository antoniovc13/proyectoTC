package com.tivit.talmatc.data.remote.model;

import com.tivit.talmatc.data.local.model.Order;

import java.util.List;

/**
 * Created by Alexzander Guillermo on 12/09/2017.
 */

public class OrderResponse {

    private boolean last;
    private int totalElements;
    private int totalPages;
    private int size;
    private int number;
    private String sort;
    private int numberOfElements;

    private List<Order> content;

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public List<Order> getContent() {
        return content;
    }

    public void setContent(List<Order> content) {
        this.content = content;
    }
}
