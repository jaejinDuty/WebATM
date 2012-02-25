package com.tc.webatm.dao.user.jdbc;

import com.tc.webatm.dao.user.UserDAO;
import com.tc.webatm.dao.user.UserMapper;
import com.tc.webatm.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.validation.constraints.NotNull;
import java.sql.SQLException;
import java.util.*;

//@Repository
public class JDBCUserDAO implements UserDAO {
    private JdbcTemplate jdbcTemplate;

    @Override
    public User get(int id) {
        if (id < 1) {
            throw new IllegalArgumentException("User id must represent positive int");
        }

        User user = (User) this.jdbcTemplate.queryForObject("select * from user where id = ?",
                                                                new Object[] {id}, new UserMapper() );
        return user;
    }

    public void add(@NotNull User user) {
        if (user.getId() > 0) {
            jdbcTemplate.update("insert into user(id, email, password) values (?, ?, ?);", new Object[] {user.getId(), user.getEmail(), user.getPassword()});
        } else {
            jdbcTemplate.update("insert into user(email, password) values (?, ?);", new Object[] {user.getEmail(), user.getPassword()});
        }
    }

    @Override
    public void save(@NotNull User user) {
        if (user.getId() < 1) {
            throw new IllegalArgumentException("User id must represent positive int");
        }
        jdbcTemplate.update("update user set email = ?, password = ? where id = ?;", new Object[] {user.getEmail(), user.getPassword(), user.getId()});
    }

    public void update(@NotNull User user) {
        save(user);
    }

    @Override
    public List<User> getAll() {
        List<User> users = (List<User>)this.jdbcTemplate.query("select * from user", new UserMapper() );
        return users;
    }

    public void delete(@NotNull User user) {
        if (user.getId() < 1) {
            throw new IllegalArgumentException("User id must represent positive int");
        }
        jdbcTemplate.update("delete from user where id = ?;", new Object[] {user.getId()});
    }

    @Override
    public void delete(int id) {
        if (id < 1) {
            throw new IllegalArgumentException("User id must represent positive int");
        }

        jdbcTemplate.update("delete from user where id = ?;", new Object[] {id});
    }

    public void deleteAll() {
        jdbcTemplate.update("delete from user;");
    }

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Collection<User> getAll(String field, String value) {
        List<User> users = (List<User>)this.jdbcTemplate.query("select * from user where " + field + "=?", new Object[] {value}, new UserMapper() );
        return users;
    }
}
