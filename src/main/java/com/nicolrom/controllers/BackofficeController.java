package com.nicolrom.controllers;

import com.nicolrom.entities.Hole;
import com.nicolrom.enums.EmployeePositionEnum;
import com.nicolrom.enums.PhaseEnum;
import com.nicolrom.services.EmployeeService;
import com.nicolrom.services.HoleService;
import com.nicolrom.services.MaterialService;
import com.nicolrom.services.impl.HoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/backoffice/holes")
public class BackofficeController {

    @Autowired
    private HoleService holeService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MaterialService materialService;

    @RequestMapping(method = RequestMethod.GET)
    public String getHoles(Model model){
        model.addAttribute("allHoles", holeService.getAllHoles());
        return "viewHoles";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addHole(Model model){
        model.addAttribute("hole", new Hole());
        model.addAttribute("allPhases", PhaseEnum.values());
        model.addAttribute("allEmployeesSofer", employeeService.getEmployeeByPosition(EmployeePositionEnum.SOFER));
        model.addAttribute("allEmployeesMecanic", employeeService.getEmployeeByPosition(EmployeePositionEnum.MECANIC));
        model.addAttribute("allEmployeesNecalificat", employeeService.getEmployeeByPosition(EmployeePositionEnum.NECALIFICAT));
        model.addAttribute("allMaterials", materialService.getAllMaterials());

        return "addHole";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addHole(Model model, @ModelAttribute("hole") Hole hole, @RequestParam(value = "phaseId", required = true) int phaseId){


        return null;
    }
}
