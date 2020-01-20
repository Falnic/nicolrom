package com.nicolrom.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/backoffice/holes")
public class BackofficeController {


    @RequestMapping(method = RequestMethod.GET)
    public String getHoles(Model model){

        return "viewHoles";
    }

}
