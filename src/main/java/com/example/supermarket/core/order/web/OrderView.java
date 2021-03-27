package com.example.supermarket.core.order.web;

import com.example.supermarket.core.customer.Customer;
import com.example.supermarket.core.date.DDate;
import com.example.supermarket.core.product.Product;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public class OrderView {
    private long id;
    private String paidfor;
    private Set<Product> products = new HashSet<>();
    private Customer customer;
    private DDate dDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPaidfor() {
        return paidfor;
    }

    public void setPaidfor(String paidfor) {
        this.paidfor = paidfor;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public DDate getdDate() {
        return dDate;
    }

    public void setdDate(DDate dDate) {
        this.dDate = dDate;
    }
}
