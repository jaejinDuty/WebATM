package com.tc.webatm;

import com.tc.webatm.model.Account;
import com.tc.webatm.model.User;
import com.tc.webatm.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:test-context.xml"})
public class UserTest {
    @Autowired
    private UserService userService;

    @Test
    public void testAddRemove() throws ClassNotFoundException, SQLException {
        Collection users = userService.fetchAll();
        int oldSize = users.size();

        userService.add(new User().setId(2).setEmail("aa@bb.cc").setPassword("123"));
        users = userService.fetchAll();

        assertNotSame(oldSize, users.size());

        int lastUId = 0;
        System.out.println("Users:");
        for (Object user : users) {
            System.out.println(user);
            lastUId = ((User) user).getId();
        }

        //delete
        userService.delete(lastUId);
        users = userService.fetchAll();
        assertEquals(users.size(), oldSize);
    }

    @Test
    public void testUpdate() throws ClassNotFoundException, SQLException {
        String initialEmail = "user@test.com";
        String changedMail = "user2@test.com";

        User targetUser = ((User)userService.fetchAll().toArray()[0]);
        int targetUID = targetUser.getId();

        targetUser.setEmail(changedMail);
        userService.update(targetUser);

        targetUser = userService.get(targetUID);
        assertEquals(targetUser.getEmail(), changedMail);

        targetUser.setEmail(initialEmail);
        userService.update(targetUser);
    }

    @Test
    public void testFetch() throws ClassNotFoundException, SQLException {
        Collection users = userService.fetchAll();
        assertNotSame(users.size(), 0);
        
        User user = userService.get(1);
        assertNotSame(user.getAccounts().size(), 0);
    }

    @Test
    public void testAccounts() throws ClassNotFoundException, SQLException {
        User targetUser = new User();
        targetUser.setEmail("user1234545@test.com").setPassword("dsfsd");
        
        Account account = new Account().setBalance(10) .
                                        setCurrencyId(1) .
                                        setState(1) .
                                        setTitle("grn") .
                                        setUser(targetUser);
        targetUser.getAccounts().add(account);
        userService.save(targetUser);

        int uid = targetUser.getId();

        User u = userService.get(uid);
        assertNotSame(u.getAccounts().size(), 0);
        
        for (Account acc : u.getAccounts()) {
            System.out.println(acc.toString());
        }

        userService.delete(uid);
    }
}
