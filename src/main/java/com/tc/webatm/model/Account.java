package com.tc.webatm.model;

import com.tc.webatm.service.UserService;

public class Account {
    private int id;
    private int userId;
    private int currencyId;
    private int state;
    private int balance;
    private String title;

    private User user;

    public User getUser() {
        return user;
    }

    public Account setUser(User user) {
        this.user = user;
        return this;
    }

    public int getState() {
        return state;
    }

    public Account setState(int state) {
        this.state = state;
        return this;
    }

    public int getBalance() {
        return balance;
    }

    public Account setBalance(int balance) {
        this.balance = balance;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Account setTitle(String title) {
        this.title = title;
        return this;
    }

    public int getUserId() {
        return userId;
    }

    public Account setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public Account setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
        return this;
    }

    public int getId() {
        return id;
    }

    public Account setId(int id) {
        this.id = id;
        return this;
    }
   
    public String toString() {
        return "Id: " + Integer.toString(id) + "; title: " + title;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !getClass().equals(o.getClass()) || o.hashCode() != hashCode()) {
            return false;
        }

        Account obj = (Account)o;
        return (obj.getId() == getId() &&
                obj.getBalance() == getBalance() &&
                obj.getUserId() == getUserId() &&
                obj.getCurrencyId() == getCurrencyId() &&
                obj.getState() == getState() &&
                obj.getTitle() == getTitle());
    }
}