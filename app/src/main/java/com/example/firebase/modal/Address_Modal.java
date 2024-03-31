package com.example.firebase.modal;

import java.io.Serializable;

public class Address_Modal implements Serializable {
    String Address;
    String name;
    String phone_no;




    public Address_Modal() {
    }

    public Address_Modal(String address, String name, String phone_no) {
        Address = address;
        this.name = name;
        this.phone_no = phone_no;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }
}

