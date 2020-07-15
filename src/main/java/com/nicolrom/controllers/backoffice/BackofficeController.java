package com.nicolrom.controllers.backoffice;

import com.nicolrom.entities.*;
import com.nicolrom.enums.EmployeePositionEnum;
import com.nicolrom.enums.PhaseEnum;
import com.nicolrom.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping(value = "/backoffice/holes")
public class BackofficeController {

    @Autowired
    private HoleService holeService;

    @Autowired
    private PhaseService phaseService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private MaterialNoticeService materialNoticeService;

    @Autowired
    private PipeService pipeService;

    @Autowired
    private AreaService areaService;

    @RequestMapping(method = RequestMethod.GET)
    public String getHoles(Model model, @RequestParam(name = "pgNr", defaultValue = "0") Integer pgNr,
                           @RequestParam(name = "pgSize", defaultValue = "15") Integer pgSize,
                           @RequestParam(name = "sortBy", defaultValue = "holeId") String sortBy){

        model.addAttribute("allHoles", holeService.getAllHoles(pgNr, pgSize, sortBy));
        model.addAttribute("pgNr", pgNr);
        model.addAttribute("lastPg", (int) holeService.getLastPageNr(pgSize));
        return "hole/viewHoles";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getHole(Model model, @PathVariable(value = "id") Integer id){
        Hole hole = holeService.getHoleById(id);

        model.addAttribute("hole", hole);
        model.addAttribute("phasePositionsMap", employeeService.getEmployeePositionsByPhases(hole.getPhases()));
        if (hole.getPhases().size() < PhaseEnum.values().length){
            PhaseEnum nextPhase = PhaseEnum.values()[hole.getPhases().size()];
            model.addAttribute("nextPhase", nextPhase);
        } else {
            model.addAttribute("nextPhase",  null);
        }
        model.addAttribute("positionEmployeesMap_SOFER", employeeService.getEmployeesByPosition(EmployeePositionEnum.SOFER));
        model.addAttribute("positionEmployeesMap_MECANIC", employeeService.getEmployeesByPosition(EmployeePositionEnum.MECANIC));
        model.addAttribute("positionEmployeesMap_NECALIFICAT", employeeService.getEmployeesByPosition(EmployeePositionEnum.NECALIFICAT));
        model.addAttribute("allPipes", pipeService.getAllPipes());
        return "hole/viewHole";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String addPhase(@PathVariable(value = "id") Integer id,
                           @RequestParam(value = "phaseDate") Date phaseDate,
                           @RequestParam(value = "employees") List<Integer> employeeArray,
                           @RequestParam(value = "nextPhase") PhaseEnum nextPhase,
                           @RequestParam(value = "pipe") String pipeDiameter){
        Phase phase = new Phase();
        phase.setPhaseType(nextPhase);
        phase.setPhaseDate(phaseDate);

        Hole hole = holeService.getHoleById(id);
        hole.setPipe(pipeService.getPipeByDiameter(pipeDiameter));
        holeService.updateHole(hole);

        phase.setHole(hole);

        Team team = new Team();
        team.setEmployees(employeeService.getEmployeesById(employeeArray));
        phase.setTeam(team);

        phase.setMaterialNoticeSet(materialNoticeService.calculateMaterialsForPhase(hole, phase));

        teamService.saveTeam(team);
        phaseService.savePhase(phase);
        materialNoticeService.saveMaterialNotices(phase.getMaterialNoticeSet());

        return "redirect:/backoffice/holes/{id}";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addHole(Model model){
        model.addAttribute("positionEmployeesMap_SOFER", employeeService.getEmployeesByPosition(EmployeePositionEnum.SOFER));
        model.addAttribute("positionEmployeesMap_MECANIC", employeeService.getEmployeesByPosition(EmployeePositionEnum.MECANIC));
        model.addAttribute("positionEmployeesMap_NECALIFICAT", employeeService.getEmployeesByPosition(EmployeePositionEnum.NECALIFICAT));
        model.addAttribute("areas", areaService.getAllAreas());

        return "hole/addHole";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addHole(Model model, @RequestParam(value = "holeDate") Date holeDate,
                          @RequestParam(value = "street") String street,
                          @RequestParam(value = "streetNr") String streetNr,
                          @RequestParam(value = "locality") String locality,
                          @RequestParam(value = "district") String district,
                          @RequestParam(value = "holeLenght") Double holeLenght,
                          @RequestParam(value = "holeWidth") Double holeWidth,
                          @RequestParam(value = "holeDepth") Double holeDepth,
                          @RequestParam(value = "area") Integer areaId,
                          @RequestParam(value = "executor") String executor,
                          @RequestParam(value = "employees") List<Integer> employeeArray,
                          @RequestParam(value = "autoRouteDistance") Integer autoRouteDistance,
                          @RequestParam(value = "autoStationaryTime") Integer autoStationaryTime){

        Hole hole = holeService.create(holeDate, street, streetNr, locality, district, areaId, holeLenght,
                                        holeWidth, holeDepth, executor, autoRouteDistance, autoStationaryTime);
        HashMap<Boolean, String> error = holeService.checkHole(hole);
        if (error != null){
            model.addAttribute("error", error.get(true));
            model.addAttribute("positionEmployeesMap_SOFER", employeeService.getEmployeesByPosition(EmployeePositionEnum.SOFER));
            model.addAttribute("positionEmployeesMap_MECANIC", employeeService.getEmployeesByPosition(EmployeePositionEnum.MECANIC));
            model.addAttribute("positionEmployeesMap_NECALIFICAT", employeeService.getEmployeesByPosition(EmployeePositionEnum.NECALIFICAT));
            model.addAttribute("areas", areaService.getAllAreas());
            return "hole/addHole";
        }

        Phase phase = new Phase();
        phase.setHole(hole);
        phase.setPhaseDate(hole.getDate());

        hole.getPhases().add(phase);

        Team team = new Team();
        team.setEmployees(employeeService.getEmployeesById(employeeArray));
        phase.setTeam(team);

        holeService.saveHole(hole);
        teamService.saveTeam(team);
        phaseService.savePhase(phase);

        return "redirect:/backoffice/holes/" + hole.getHoleId();
    }

}
