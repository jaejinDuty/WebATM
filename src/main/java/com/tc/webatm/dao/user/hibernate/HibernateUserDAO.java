package com.tc.webatm.dao.user.hibernate;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import com.tc.webatm.db.ExecutionMarkers;
import com.tc.webatm.db.hibernate.SessionProvider;
import com.tc.webatm.dao.user.UserDAO;
import com.tc.webatm.model.User;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;


public class HibernateUserDAO implements UserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @ExecutionMarkers.FetchMethod
    public Collection<User> getAll() {
        //!!opened and closed in HibernateProxyAspect
        //Session session = sessionFactory.openSession();

        return SessionProvider.SELF.getSession().createCriteria(User.class).list();
        //return template().loadAll(User.class);
    }

    @Override
    @ExecutionMarkers.FetchMethod
    public Collection<User> getAll(String field, String value) {
        Criteria criteria = SessionProvider.SELF.getSession().createCriteria(User.class);
        criteria.add(Expression.eq(field, value));

        return criteria.list();
    }

    @Override
    @ExecutionMarkers.FetchMethod
    public User get(int id) {
        User user = (User)SessionProvider.SELF.getSession().get(User.class, id);
        if (user != null) {
            Hibernate.initialize(user);
        }
        return user;
    }

    @Override
    @ExecutionMarkers.AmendMethod
    public void save(User user) {
        if (user.getId() != 0) {
            SessionProvider.SELF.getSession().saveOrUpdate(user);
        } else {
            SessionProvider.SELF.getSession().save(user);
        }
    }

    @Override
    @ExecutionMarkers.AmendMethod
    public void delete(int id) {
        SessionProvider.SELF.getSession().delete(new User().setId(id));
    }
}