package com.example.supermarket.core.address.web;

import com.example.supermarket.base.BaseRequest;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class AddresssBaseReq extends BaseRequest implements Serializable {
    @NotEmpty
    private String country;
    @NotEmpty
    private String state;
    @NotEmpty
    private String city;
    @NotEmpty
    private String zipCode;
    @NotEmpty
    private String line1;
    private String line2;
    @NotEmpty
    private String block;
    private String detail;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
