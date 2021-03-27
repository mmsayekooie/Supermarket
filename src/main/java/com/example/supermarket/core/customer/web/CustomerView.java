package com.example.supermarket.core.customer.web;

import com.example.supermarket.core.address.web.AddresssView;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class CustomerView {
    private long id;
    private String name;
    private String email;
    private String phone;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date dateBecomeCustomer;
    private String detail;
    private Set<AddresssView> addressses = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDateBecomeCustomer() {
        return dateBecomeCustomer;
    }

    public void setDateBecomeCustomer(Date dateBecomeCustomer) {
        this.dateBecomeCustomer = dateBecomeCustomer;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Set<AddresssView> getAddressses() {
        return addressses;
    }

    public void setAddressses(Set<AddresssView> addressses) {
        this.addressses = addressses;
    }
}
