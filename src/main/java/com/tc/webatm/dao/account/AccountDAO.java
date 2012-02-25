package com.tc.webatm.dao.account;
import com.tc.webatm.model.Account;

import java.util.Collection;

public interface AccountDAO {
    public Account get(int id);
    public void save(Account account);
    public void delete(int id);
    public Collection<Account> getAll();
}
