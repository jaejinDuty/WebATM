package com.tc.webatm;

public interface URIs {
    public static final String INDEX = "/index";
    public static final String LOGOUT = "/logout";
    public interface User {
        public static final String LIST = "/users";
        public static final String EDIT = "/user/edit/{id}";
        public static final String DELETE = "/user/delete/{id}";
        public static final String ADD = "/user/add";
        public static final String SAVE = "/user/save";
        public static final String ACCOUNTS = "/user/accounts/{id}";
    }
}
