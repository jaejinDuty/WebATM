package com.tc.webatm;

/* public enum URIs {
    INDEX("/index");

    private String uri;

    URIs(String uri) {
        this.uri=uri;
    }
    
    @Override
    public String toString() {
        return uri;
    }
} */

public interface URIs {
    public static final String INDEX = "/index";
    public static final String USERS_HOME = "/users";
    public static final String USER_EDIT = "/user/edit/{id}";
    public static final String USER_DELETE = "/user/delete/{id}";
    public static final String USER_ADD = "/user/add";
    public static final String USER_SAVE = "/user/save";
    public static final String USER_ACCOUNTS = "/user/accounts/{id}";
}
