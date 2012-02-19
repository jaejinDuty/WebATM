package com.tc.webatm.dao.user.hibernate;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import com.tc.webatm.db.ExecutionMarkers;
import com.tc.webatm.db.hibernate.SessionProvider;
import com.tc.webatm.dao.user.UserDAO;
import com.tc.webatm.model.User;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;


public class HibernateUserDAO implements UserDAO {
    @Autowired
    private SessionFactory sessionFactory;
    private HibernateTemplate hibernateTemplate;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private HibernateTemplate template() {
        if (hibernateTemplate == null) {
            hibernateTemplate = new HibernateTemplate(sessionFactory);
        }
        return hibernateTemplate;
    }

    @Override
    @ExecutionMarkers.FetchMethod
    public Collection<User> getAll() {
        //!!opened and closed in HibernateProxyAspect
        //Session session = sessionFactory.openSession();
        List users = SessionProvider.SELF.getSession().createCriteria(User.class).list();
        //session.close();
        return users;
    }

    @Override
    @ExecutionMarkers.FetchMethod
    public User get(int id) throws ClassNotFoundException, SQLException {
        User user = (User)SessionProvider.SELF.getSession().get(User.class, id);
        Hibernate.initialize(user);
        return user;
    }

    @Override
    @ExecutionMarkers.AmendMethod
    public void add(User user) throws ClassNotFoundException, SQLException {
        SessionProvider.SELF.getSession().save(user);
    }

    @Override
    @ExecutionMarkers.AmendMethod
    public void save(User user) throws ClassNotFoundException, SQLException {
        SessionProvider.SELF.getSession().saveOrUpdate(user);
    }

    @Override
    @ExecutionMarkers.AmendMethod
    public void update(User user) throws ClassNotFoundException, SQLException {
        SessionProvider.SELF.getSession().update(user);
    }

    @Override
    @ExecutionMarkers.AmendMethod
    public void delete(User user) throws ClassNotFoundException, SQLException {
        SessionProvider.SELF.getSession().delete(user);
    }

    @Override
    @ExecutionMarkers.AmendMethod
    public void delete(int id) throws ClassNotFoundException, SQLException {
        SessionProvider.SELF.getSession().delete(new User().setId(id));
    }

    @Override
    @ExecutionMarkers.AmendMethod
    public void deleteAll() throws ClassNotFoundException, SQLException {
        for (User user : getAll()) {
            SessionProvider.SELF.getSession().delete(user);
        }
    }
}