package com.example.supermarket.core.order.web;

import com.example.supermarket.base.BaseRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public class OrderBaseReq extends BaseRequest implements Serializable {
    private String paidfor;
    @NotEmpty
    private List<@Valid Id> products;
    @NotNull
    private Long customerId;
    @NotNull
    private Long dDateId;

    public String getPaidfor() {
        return paidfor;
    }

    public void setPaidfor(String paidfor) {
        this.paidfor = paidfor;
    }

    public List<Id> getProducts() {
        return products;
    }

    public void setProducts(List<Id> products) {
        this.products = products;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getdDateId() {
        return dDateId;
    }

    public void setdDateId(Long dDateId) {
        this.dDateId = dDateId;
    }
}
