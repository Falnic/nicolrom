package com.nicolrom.controllers;

import com.nicolrom.entities.*;
import com.nicolrom.enums.EmployeePositionEnum;
import com.nicolrom.enums.PhaseEnum;
import com.nicolrom.services.EmployeeService;
import com.nicolrom.services.HoleService;
import com.nicolrom.services.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

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

        return "addHole";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addHole(Model model, @ModelAttribute("hole") Hole hole, HttpServletResponse httpServletResponse,
                          HttpServletRequest httpServletRequest){

        HttpSession session = httpServletRequest.getSession(true);
        session.setAttribute("hole", hole);

        model.addAttribute("phase", new Phase());
        model.addAttribute("allPhaseTypes", PhaseEnum.values());
        return "addPhaseToHole";
    }

    @RequestMapping(value = "/addPhaseToHole", method = RequestMethod.POST)
    public String addPhaseToHole(Model model, @ModelAttribute("phase") Phase phase, HttpServletResponse httpServletResponse,
                          HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession(true);
        Hole hole = (Hole) session.getAttribute("hole");

        Team team = new Team();
        Team_Employee team_employee = new Team_Employee();
        team_employee.setTeam(team);

        phase.setTeam(team);
        phase.setHole(hole);

        Set<Phase> phaseSet = new HashSet<>();
        phaseSet.add(phase);

        hole.setPhases(phaseSet);

        session.setAttribute("hole", hole);
        session.setAttribute("phaseType", phase.getPhaseType());

        model.addAttribute("team_employee", new Team_Employee());

        List<EmployeePositionEnum> employeePositions = new ArrayList<>();
        for (EmployeePositionEnum position : EmployeePositionEnum.values()){
            if(position.ordinal() != EmployeePositionEnum.ALTA_POZITIE.ordinal()){
                employeePositions.add(position);
            }
        }

        //Only Sofer Mecanic and Necalificat employees are needed
        List<Employee> employees = new ArrayList<>();
        for (Employee employee : employeeService.getAllEmployees()){
            if (employee.getPosition().ordinal() != EmployeePositionEnum.ALTA_POZITIE.ordinal()){
                employees.add(employee);
            }
        }

        Map<EmployeePositionEnum, List<Employee>> employeesMap = new HashMap<>();
        for (EmployeePositionEnum position : employeePositions) {
            List<Employee> employeeListByPosition = new ArrayList<>();
            for(Employee employee : employees){
                if (employee.getPosition().ordinal() == position.ordinal()){
                    employeeListByPosition.add(employee);
                }
            }
            employeesMap.put(position, employeeListByPosition);
        }

        model.addAttribute("employeesMap", employeesMap);

        return "addTeamToHole";
    }



//    @RequestMapping(value = "/addEmployeeToTeam", method = RequestMethod.POST)
//    public String addEmployeeToTeam(Model model, @RequestParam(value = "employeeArr[]") List<Integer> employeeArr,
//                                    HttpServletRequest httpServletRequest){
//
//        HttpSession session = httpServletRequest.getSession(true);
//        Hole hole = (Hole) session.getAttribute("hole");
//        PhaseEnum phaseEnum = (PhaseEnum) session.getAttribute("phaseType");
//
//        Team team = holeService.getHolePhaseByType(hole, phaseEnum).getTeam();
//        Set<Team_Employee> team_employeeSet = team.getTeam_employees();
//
//        if(team_employeeSet == null) {
//            team_employeeSet = new HashSet<>();
//        }
//        //Get Employee from db with id value in employeeArr
//        for (Integer integer : employeeArr) {
//            Employee employee = employeeService.getEmployeeById(integer);
//
//            Team_Employee teamEmployee = new Team_Employee();
//            teamEmployee.setEmployee(employee);
//            teamEmployee.setTeam(team);
//
//            team_employeeSet.add(teamEmployee);
//        }
//        team.setTeam_employees(team_employeeSet);
//
//        return "addMaterialsToHole";
//    }

    @RequestMapping(value = "/addTeamToHole", method = RequestMethod.POST)
    public  String addTeamToHole(Model model, @RequestParam(value = "employee") List<Integer> employeeArray,
                                    HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest){

        HttpSession session = httpServletRequest.getSession(true);
        Hole hole = (Hole) session.getAttribute("hole");

        PhaseEnum phaseEnum = (PhaseEnum) session.getAttribute("phaseType");

        Team team = holeService.getHolePhaseByType(hole, phaseEnum).getTeam();;
        Set<Team_Employee> team_employeeSet = team.getTeam_employees();

        if(team_employeeSet == null) {
            team_employeeSet = new HashSet<>();
        }
        //Get Employee from db with id value in employeeArr
        for (Integer integer : employeeArray) {
            Employee employee = employeeService.getEmployeeById(integer);

            Team_Employee teamEmployee = new Team_Employee();
            teamEmployee.setEmployee(employee);
            teamEmployee.setTeam(team);

            team_employeeSet.add(teamEmployee);
        }
        team.setTeam_employees(team_employeeSet);

        session.setAttribute("hole", hole);


        //Get All Materials
        model.addAttribute("allMaterials", null);
        model.addAttribute("phase_material", new Phase_Material());

        return "addMaterialsToHole";
    }

    @RequestMapping(value = "/addMaterialsToHole", method = RequestMethod.POST)
    public String addMaterialsToHole(Model model, @ModelAttribute("phase_material") Phase_Material phase_material, HttpServletResponse httpServletResponse,
                                     HttpServletRequest httpServletRequest){
        return null;
    }

//    @RequestMapping(value = "/add", method = RequestMethod.POST)
//    public String addHole(Model model, @ModelAttribute("hole") Hole hole, @RequestParam(value = "materialId") int materialId,
//                          @RequestParam(value = "phaseOrdinal") int phaseOrdinal, @RequestParam(value = "phaseDate") Date phaseDate
//                          ){
//
//
//        return null;
//    }
}
