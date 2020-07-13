package com.nicolrom.controllers.backoffice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String getLoginPage(){
        return "login";
    }

    @RequestMapping(value = "/login-error", method = RequestMethod.GET)
    public String loginError(Model model){
        model.addAttribute("error", "true");
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(){
        return "logout";
    }
}
