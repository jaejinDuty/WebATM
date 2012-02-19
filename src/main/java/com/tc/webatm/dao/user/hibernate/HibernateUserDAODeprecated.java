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

    @Override
    public void add(User user) throws ClassNotFoundException, SQLException {
        template().save(user);
    }

    @Override
    public void save(User user) throws ClassNotFoundException, SQLException {
        template().saveOrUpdate(user);
    }

    @Override
    public void update(User user) throws ClassNotFoundException, SQLException {
        template().update(user);
    }

    @Override
    public Collection getAll() throws ClassNotFoundException, SQLException {
        return template().find("from User order by id");
    }

    @Override
    public void delete(User user) throws ClassNotFoundException, SQLException {
        template().delete(user);
    }

    @Override
    public void delete(int id) throws ClassNotFoundException, SQLException {
        template().delete(new User().setId(id));
    }

    @Override
    public void deleteAll() throws ClassNotFoundException, SQLException {
        template().deleteAll(getAll());
    }

    @Override
    public User get(int id) throws ClassNotFoundException, SQLException {
        DetachedCriteria criteria = DetachedCriteria.forClass(User.class).add(Restrictions.idEq(id));
        return (User)template().findByCriteria(criteria).toArray()[0];
    }
}