package com.example.supermarket.core.supplier.web;

import javax.validation.constraints.NotEmpty;


public class SupplierBaseReq {
    @NotEmpty
    private String name;
    @NotEmpty
    private String phone;
    @NotEmpty
    private String factory;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
