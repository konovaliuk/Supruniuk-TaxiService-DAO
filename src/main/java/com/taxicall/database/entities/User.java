package com.taxicall.database.entities;

public class User {
    private long id;
    private String name;
    private String surname;
    private String email;
    private String password_hash;
    private String created_on;


    public User(int id, String name, String surname,
                String email, String password_hash, String created_on){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password_hash = password_hash;
        this.created_on = created_on;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getFullName() {
        return name + " " + surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return password_hash;
    }

    public String getCreatedOn() {
        return created_on;
    }
}