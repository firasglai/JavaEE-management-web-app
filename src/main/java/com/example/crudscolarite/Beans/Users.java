package com.example.crudscolarite.Beans;

public class Users {
    private int id;
    private String uname, password, email, phone;

    public Users() {
    }

    public Users(int id, String uname, String password, String email, String phone) {
        this.id = id;
        this.uname = uname;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public Users(String uname, String password, String email, String phone) {
        this.uname = uname;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", uname='" + uname + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
