package com.example.supermarket.core.customer.web;

import com.example.supermarket.base.BaseRequest;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class CustomerBaseReq extends BaseRequest implements Serializable {
    @NotEmpty
    private String name;
    private String email;
    @NotEmpty
    private String phone;

    private Date dateBecomeCustomer;
    private String detail;
    @NotEmpty
    private List<@Valid Id> addressses;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
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

    public List<Id> getAddressses() {
        return addressses;
    }

    public void setAddressses(List<Id> addressses) {
        this.addressses = addressses;
    }
}
