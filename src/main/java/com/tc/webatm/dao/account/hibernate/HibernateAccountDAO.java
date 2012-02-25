package com.tc.webatm.dao.account.hibernate;

import com.tc.webatm.dao.account.AccountDAO;
import com.tc.webatm.db.ExecutionMarkers;
import com.tc.webatm.db.hibernate.SessionProvider;
import com.tc.webatm.model.Account;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

public class HibernateAccountDAO implements AccountDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @ExecutionMarkers.FetchMethod
    public Account get(int id) {
        Account account = (Account)SessionProvider.SELF.getSession().get(Account.class, id);
        if (account != null) {
            Hibernate.initialize(account);
        }
        return account;
    }

    @Override
    @ExecutionMarkers.AmendMethod
    public void save(Account account) {
        if (account.getId() != 0) {
            SessionProvider.SELF.getSession().saveOrUpdate(account);
        } else {
            SessionProvider.SELF.getSession().save(account);
        }
    }

    @Override
    @ExecutionMarkers.AmendMethod
    public void delete(int id) {
        SessionProvider.SELF.getSession().delete(new Account().setId(id));
    }

    @Override
    @ExecutionMarkers.FetchMethod
    public Collection<Account> getAll() {
        return SessionProvider.SELF.getSession().createCriteria(Account.class).list();
    }
}