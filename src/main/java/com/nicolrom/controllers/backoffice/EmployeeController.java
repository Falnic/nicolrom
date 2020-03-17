package com.nicolrom.controllers.backoffice;

import com.nicolrom.entities.Employee;
import com.nicolrom.enums.EmployeePositionEnum;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/backoffice/employees")
public class EmployeeController {

    @RequestMapping(method = RequestMethod.GET)
    public String getListedEmployees(){
        return "";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addNewEmployee_get(Model model){

        model.addAttribute("employee", new Employee());
        model.addAttribute("positions", EmployeePositionEnum.values());

        return "/employee/addEmployee";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addNewEmployee_post(@ModelAttribute("employee") Employee employee){
        return "";
    }
}
