package com.twu.biblioteca.entity;

public class Account {
    private int number;
    private String name;
    private String password;

    public Account(int number, String name, String password) {
        this.number = number;
        this.name = name;
        this.password = password;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
