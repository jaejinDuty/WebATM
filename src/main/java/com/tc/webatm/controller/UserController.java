package com.tc.webatm.controller;

import com.tc.webatm.URIs;
import com.tc.webatm.model.User;
import com.tc.webatm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

/*
* use
* http://levelup.lishman.com/spring/form-processing/quick-start.php
*
* */

@Controller
@SessionAttributes("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(URIs.User.HOME)
    public ModelAndView index() {
        ModelAndView model = new ModelAndView("user/index");
        try {
            //request.setAttribute("users", getUserDAO().getAll());
            Collection users = userService.fetchAll();
            if (users.isEmpty()) {
                model.addObject("msg", "No users found");
            } else {
                model.addObject("users", users);
            }
        } catch (Exception e) {
            model.addObject("msg", e.getMessage());
        }

        return model;
	}

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value=URIs.User.EDIT, method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id") Integer id) {
        ModelAndView model = new ModelAndView("user/edit");
        if (id != null) {
            try {
                model.addObject("user", userService.get(id));
            } catch (Exception e) {
                model.addObject("msg", e.getMessage());
            }
        }
        return model;
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value=URIs.User.DELETE, method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable("id") Integer id) {
        String resultMessage = "User was successfully removed";
        try {
            userService.delete(id);
        } catch (Exception e) {
            resultMessage = e.getMessage();
        }
        ModelAndView model = index();
        model.addObject("msg", resultMessage);
        return model;
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value=URIs.User.ADD, method = RequestMethod.GET)
    public ModelAndView add() {
        ModelAndView model = new ModelAndView("user/edit");
        model.addObject("user", new User());
        return model;
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value=URIs.User.SAVE, method = RequestMethod.POST)
    public ModelAndView save(User user) {
        String resultMessage = "User was successfully ";
        try {
            if (user.getId() == 0) {
                userService.add(user);
                resultMessage += "added";
            } else {
                userService.save(user);
                resultMessage += "updated";
            }
        } catch (Exception e) {
            resultMessage = e.getMessage();
        }
        ModelAndView model = index();
        model.addObject("msg", resultMessage);
        return model;
    }
}