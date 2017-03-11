package com.example.amr.headsofmohsensheet;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Contact {

    String name;
    String email;
    String phone;
    String address;
    String description;

    public Contact() {}

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public Contact(String name, String email, String phone, String address, String description) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.description = description;
    }

}
