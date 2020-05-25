package com.barosanu.controller.persistence;

import java.io.Serializable;

public class ValidAccount implements Serializable {

    private String address;
    private Serializable password;

    public ValidAccount(String address, Serializable password) {
        this.address = address;
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Serializable getPassword() {
        return password;
    }

    public void setPassword(Serializable password) {
        this.password = password;
    }
}
