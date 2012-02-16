package com.tc.webatm.model;

import com.tc.webatm.service.UserService;

import java.util.HashSet;
import java.util.Set;

//@Entity
//@Table(name="user")
public class User {
    /*@Id
    @Column(name="id")
    @GeneratedValue*/
    private int id;
    private String email;
    private String password;
    private Set<Account> accounts = new HashSet<Account>();

    public Set<Account> getAccounts() {
        return accounts;
    }

    public User setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public int getId() {
        return id;
    }

    public User setId(int id) {
        this.id = id;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public boolean getIsAdmin() {
        return email.equals(UserService.ADMIN_EMAIL);
    }

    public boolean isAdmin() {
        return getIsAdmin();
    }

    public String toString() {
        return "Id: " + getId() +  "; Email: " + getEmail();
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

        User user = (User)o;
        return (user.getIsAdmin() == getIsAdmin() &&
                    user.getEmail().equals(getEmail()) &&
                    user.getPassword().equals(getPassword()) &&
                    user.getAccounts().containsAll(getAccounts()));
    }
}