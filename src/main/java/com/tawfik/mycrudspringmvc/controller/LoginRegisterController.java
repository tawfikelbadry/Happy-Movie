/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tawfik.mycrudspringmvc.controller;

import com.tawfik.mycrudspringmvc.Service.UserService;
import com.tawfik.mycrudspringmvc.model.User;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author tawfik
 */
@Controller
public class LoginRegisterController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin(Model model, HttpSession session) {
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegister(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String postLogin(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {

        System.out.println("user name " + user.getUsername());
        System.out.println("password " + user.getPassword());
        if (user.getUsername().equals("") || user.getUsername().length() < 5 || user.getPassword().equals("")) {
            System.out.println("errors : " + result.getErrorCount());
            return "login";

        } else {
            boolean isUser = userService.isUser(user);
            if (isUser) {
              User loggedUser=  userService.getUser(user.getUsername());
                System.out.println(loggedUser);
                session.setAttribute("user", loggedUser);
                return "redirect:popular";
            } else {
                return "redirect:login?success=false";

            }
        }

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String postRegister(@Valid @ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        } else {
            userService.save(user);
            return "redirect:login?success=ok";
        }

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:login";
    }
}
