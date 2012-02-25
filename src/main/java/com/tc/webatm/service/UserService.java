package com.tc.webatm.service;

import com.tc.webatm.dao.user.UserDAO;
import com.tc.webatm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    private User loggedUser;
    
    public static final String ADMIN_EMAIL = "admin@webatm.com";
    public static final String ADMIN_PASSWORD = "admin";

    public void setLoggedUser(User u) {
        loggedUser = u;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public boolean isUserLogged() {
        return (loggedUser != null);
    }

    public User getHydratedFromMap(Map map) {
        //some kind of builder but without UserBuilder class. js (jquery) way. will it fit here?...
        return new User().setId((Integer) map.get("id"))
                        .setEmail((String) map.get("email"))
                        .setPassword((String) map.get("password"));
    }

    public Collection<User> fetchAll() {
        return userDAO.getAll();
    }

    public User get(int id) {
        return userDAO.get(id);
    }

    public void delete(int id) {
        userDAO.delete(id);
    }
    
    public void save(User user) {
        userDAO.save(user);
    }

    public Collection<User> findByEmail(String email) {
        return findByField("email", email);
    }
    
    private Collection<User> findByField(String field, String value) {
        if (value.isEmpty()) {
            return Collections.emptyList();
        }
        return userDAO.getAll(field, value);
    }
}
