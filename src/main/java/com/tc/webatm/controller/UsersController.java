package com.tc.webatm.controller;

import com.tc.webatm.URIs;
import com.tc.webatm.model.User;
import com.tc.webatm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@SessionAttributes("user")
public class UsersController {

    @Autowired
    private UserService userService;

    @RequestMapping(URIs.User.LIST)
    public ModelAndView index() {
        ModelAndView model = new ModelAndView("user/index");
        try {
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

    @RequestMapping(value=URIs.User.EDIT)
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

    @RequestMapping(value=URIs.User.DELETE)
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

    @RequestMapping(value=URIs.User.ADD, method = RequestMethod.GET)
    public ModelAndView add() {
        ModelAndView model = new ModelAndView("user/edit");
        model.addObject("user", new User());
        return model;
    }

    @RequestMapping(value=URIs.User.SAVE, method = RequestMethod.POST)
    public ModelAndView save(User user) {
        ModelAndView model = index();
        String saveResult = "User was successfully ";
        List<String> errors = new ArrayList<String>();
        try {
            if (user.getEmail().trim().isEmpty() || user.getPassword().isEmpty()) {
                errors.add("Email and password are required fields");
            }

            List<User> users = (List<User>)userService.findByEmail(user.getEmail());
            if (users.size() > 0) {
                errors.add("Specified user already exists");
            }

            if (errors.isEmpty()) {
                userService.save(user);
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