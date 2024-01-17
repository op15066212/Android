package com.example.myapplication.Bean;

import org.jetbrains.annotations.NotNull;

public class user_mysql {
    private String uid;
    private String uname;
    private String password;
    private String gender;
    private String phone;
    private String email;
    private String address;

    public user_mysql(String uid, String uname, String password, String gender, String phone, String email, String address) {
        this.uid = uid;
        this.uname = uname;
        this.password = password;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public user_mysql(String username, String password) {
        this.uname = username;
        this.password = password;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @NotNull
    @Override
    public String toString() {
        return "user{" +
                "uid='" + uid + '\'' +
                ", uname='" + uname + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
