package com.tc.webatm.dao.user.hibernate;

import com.tc.webatm.dao.user.UserDAO;
import com.tc.webatm.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Deprecated
public class HibernateUserDAODeprecated implements UserDAO {

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Autowired
    private SessionFactory sessionFactory;
    private HibernateTemplate hibernateTemplate;

    private HibernateTemplate template() {
        if (hibernateTemplate == null) {
            hibernateTemplate = new HibernateTemplate(sessionFactory);
        }
        return hibernateTemplate;
    }

    public void add(User user) {
        template().save(user);
    }

    @Override
    public void save(User user) {
        template().saveOrUpdate(user);
    }

    public void update(User user) {
        template().update(user);
    }

    @Override
    public Collection getAll() {
        return template().find("from User order by id");
    }

    @Override
    public Collection<User> getAll(String field, String value) {
        DetachedCriteria criteria = DetachedCriteria.forClass(User.class).add(Restrictions.eq(field, value));
        return template().findByCriteria(criteria);
    }

    @Override
    public void delete(int id) {
        template().delete(new User().setId(id));
    }

    public void deleteAll() throws ClassNotFoundException, SQLException {
        template().deleteAll(getAll());
    }

    @Override
    public User get(int id) {
        DetachedCriteria criteria = DetachedCriteria.forClass(User.class).add(Restrictions.idEq(id));
        return (User)template().findByCriteria(criteria).toArray()[0];
    }
}