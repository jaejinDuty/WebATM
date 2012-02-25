package com.tc.webatm.dao.user;
import com.tc.webatm.model.User;

import java.util.Collection;

public interface UserDAO {
    public void save(User user);
    public void delete(int id);
    public User get(int id);
    public Collection<User> getAll();
    public Collection<User> getAll(String field, String value);
}
