package com.tc.webatm;

public interface URIs {
    public static final String INDEX = "/index";
    public static final String LOGOUT = "/logout";
    interface User {
        public static final String LIST = "/users";
        public static final String EDIT = "/user/edit/{id}";
        public static final String DELETE = "/user/delete/{id}";
        public static final String ADD = "/user/add";
        public static final String SAVE = "/user/save";
    }
    interface Account {
        public static final String LIST = "/accounts/{userId}";
        public static final String EDIT = "/account/edit/{id}";
        public static final String DELETE = "/account/delete/{id}";
        public static final String ADD = "/account/add/{userId}";
        public static final String SAVE = "/account/save";
    }
}
