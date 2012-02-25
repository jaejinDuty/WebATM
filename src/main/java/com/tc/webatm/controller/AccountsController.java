package com.tc.webatm.controller;

import com.tc.webatm.URIs;
import com.tc.webatm.model.Account;
import com.tc.webatm.model.User;
import com.tc.webatm.service.AccountService;
import com.tc.webatm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("account")
public class AccountsController {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @RequestMapping(URIs.Account.LIST)
    public ModelAndView index(@PathVariable("userId") Integer userId) {
        ModelAndView model = new ModelAndView("account/index");
        model.addObject("userId", userId);

        try {
            User user = userService.get(userId);
            if (user == null) {
                model.addObject("msg", "User wasn't found");
            }
            model.addObject("accounts", user.getAccounts());
        } catch (Exception e) {
            model.addObject("msg", e.getMessage());
        }

        return model;
	}

    @RequestMapping(value=URIs.Account.EDIT)
    public ModelAndView edit(@PathVariable("id") Integer id) {
        ModelAndView model = new ModelAndView("account/edit");
        if (id != null) {
            try {
                model.addObject("account", accountService.get(id));
            } catch (Exception e) {
                model.addObject("msg", e.getMessage());
            }
        }
        return model;
    }

    @RequestMapping(value=URIs.Account.DELETE)
    public ModelAndView delete(@PathVariable("id") Integer id) {
        String resultMessage = "Account was successfully removed";
        Account account = accountService.get(id);
        User user = account.getUser();
        try {
            accountService.delete(id);
        } catch (Exception e) {
            resultMessage = e.getMessage();
        }
        ModelAndView model = index(user.getId());
        model.addObject("msg", resultMessage);
        return model;
    }

    @RequestMapping(value=URIs.Account.ADD)
    public ModelAndView add() {
        ModelAndView model = new ModelAndView("account/edit");
        model.addObject("account", new Account());
        return model;
    }

    @RequestMapping(value=URIs.Account.SAVE, method = RequestMethod.POST)
    public ModelAndView save(Account account) {
        User user = account.getUser();
        ModelAndView model = index(user.getId());
        String saveResult = "Account was successfully ";
        List<String> errors = new ArrayList<String>();
        try {
            if (account.getTitle().isEmpty()) {
                errors.add("Title required");
            }

            if (errors.isEmpty()) {
                accountService.save(account);
                saveResult += (user.getId() == 0 ? "added" : "updated");
                model.addObject("msg", saveResult);
            }
        } catch (Exception e) {
            errors.add(e.getMessage());
        }

        if (!errors.isEmpty()) {
            model.addObject("errors", errors);
        }
        return model;
    }
}