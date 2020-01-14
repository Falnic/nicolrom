package com.nicolrom.controllers;

import com.nicolrom.dao.UserDao;
import com.nicolrom.entities.User;
import com.nicolrom.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class HomePageController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String getAllUsers(Model model){
        List<User> users = userService.getAllUsers();

        model.addAttribute("allUsers", users);

        return "viewUsers";
    }
}
