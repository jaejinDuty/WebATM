package com.tc.webatm;

import com.tc.webatm.model.Account;
import com.tc.webatm.model.User;
import com.tc.webatm.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:test-context.xml"})
public class UserTest {
    @Autowired
    private UserService userService;

    private int rndNum;

    @Before
    public void setUp() {
        int min=5, max=10;
        rndNum = min + (int)(Math.random() * ((max - min) + 1));
    }

    @Test
    public void testAddRemove() throws ClassNotFoundException, SQLException {
        Collection users = userService.fetchAll();
        int oldSize = users.size();

        userService.save(new User().setEmail("test" + rndNum + "@mail.com").setPassword("123"));
        users = userService.fetchAll();

        assertNotSame(oldSize, users.size());

        int lastUId = 0;
        System.out.println("users:");
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
        userService.save(targetUser);

        targetUser = userService.get(targetUID);
        assertEquals(targetUser.getEmail(), changedMail);

        targetUser.setEmail(initialEmail);
        userService.save(targetUser);
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
        User targetUser = new User().setEmail("user1234545@test.com").setPassword("dsfsd");
        
        targetUser.getAccounts().add(new Account().setBalance(10) .
                                                    setCurrencyId(1) .
                                                    setState(1) .
                                                    setTitle("grn") .
                                                    setUser(targetUser));
        userService.save(targetUser);

        int uid = targetUser.getId();

        targetUser = userService.get(uid);
        assertSame(targetUser.getAccounts().size(), 1);

        System.out.println("user:");
        System.out.println(targetUser.toString());

        System.out.println("user accounts:");
        for (Account acc : targetUser.getAccounts()) {
            System.out.println(acc.toString());
        }
        
        Account targetAccount = (Account)targetUser.getAccounts().toArray()[0];
        String newAccTitle = "title" + rndNum;
        targetAccount.setTitle(newAccTitle);

        userService.save(targetUser);

        targetUser = userService.get(uid);
        targetAccount = ((Account)targetUser.getAccounts().toArray()[0]);
        assertTrue(targetAccount.getTitle().equals(newAccTitle));

        userService.delete(uid);
    }
}
