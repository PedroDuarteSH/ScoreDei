package com.example.data;


import javax.persistence.*;

@Entity
@Inheritance
public class Users {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique=true)
    private String username;

    private String name;

    @Column(unique=true)
    private String email;
    private String password;
    private String phoneNumber;
    private boolean isAdmin = false;

    public Users(String username, String name, String email, String password, String phoneNumber, boolean isAdmin) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.isAdmin = isAdmin;
    }

    public Users(){

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String isAdmin() {
        return isAdmin? "ADMIN" : "USER";
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
