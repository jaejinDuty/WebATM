package com.tc.webatm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tc.webatm.URIs;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    @RequestMapping(value={"", "/", URIs.INDEX, URIs.INDEX + "/*"}, method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ModelAndView model = new ModelAndView("index/index");
        model.addObject("msg", "index page");

        return model;
	}

    /* @RequestMapping(URIs.LOGOUT)
    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.getSession().invalidate();
        HttpSession s = request.getSession();

        return "redirect:/";
    } */
}