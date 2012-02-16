package com.tc.webatm.service;

import com.tc.webatm.dao.user.UserDAO;
import com.tc.webatm.model.Account;
import com.tc.webatm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    private User loggedUser;
    
    public static final String ADMIN_EMAIL = "admin@webatm.com";
    public static final String ADMIN_PASSWORD = "admin";

    public UserService() {
        int i=0;
    }

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

    public Collection<User> fetchAll() throws ClassNotFoundException, SQLException {
        return userDAO.getAll();
    }

    public User get(int id) throws ClassNotFoundException, SQLException {
        return userDAO.get(id);
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        userDAO.add(user);
    }

    public void delete(User user) throws ClassNotFoundException, SQLException {
        userDAO.delete(user);
    }

    public void delete(int id) throws ClassNotFoundException, SQLException {
        userDAO.delete(id);
    }
    
    public void update(User user) throws ClassNotFoundException, SQLException {
        userDAO.update(user);
    }
}
