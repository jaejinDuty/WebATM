package com.tc.webatm.service;

import com.tc.webatm.dao.account.AccountDAO;
import com.tc.webatm.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Collection;

@Service
public class AccountService {
    @Autowired
    private AccountDAO accountDAO;

    public Account get(int id) {
        return accountDAO.get(id);
    }

    public Collection<Account> getAll() {
        return accountDAO.getAll();
    }

    public void delete(int id) throws ClassNotFoundException, SQLException {
        accountDAO.delete(id);
    }

    public void save(Account account) throws ClassNotFoundException, SQLException {
        accountDAO.save(account);
    }
}
