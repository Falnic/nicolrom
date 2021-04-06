package com.nicolrom.controllers.backoffice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PaymentController {

    @RequestMapping(value = "/backoffice/contracts", method = RequestMethod.GET)
    public String getContracts(Model model){

        return "payment/viewContracts";
    }

    @RequestMapping(value = "/backoffice/contracts/1", method = RequestMethod.GET)
    public String getContract(){
        return "payment/viewContract";
    }

    @RequestMapping(value = "/backoffice/volumes", method = RequestMethod.GET)
    public String getVolumes(Model model){
        return "payment/viewVolumes";
    }

    @RequestMapping(value = "/backoffice/volumes/add", method = RequestMethod.GET)
    public String addVolume(Model model){
        return "payment/addVolume";
    }

    @RequestMapping(value = "/backoffice/volumes/1", method = RequestMethod.GET)
    public String getVolume(Model model){
        return "payment/viewVolume";
    }

    @RequestMapping(value = "/backoffice/volumes/1/update", method = RequestMethod.GET)
    public String updateVolume(Model model){
        return "payment/updateVolume";
    }
}
