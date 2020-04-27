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

    private final static List<EmployeePositionEnum> siteWorkersPositions = new ArrayList<>
            (Arrays.asList(EmployeePositionEnum.MECANIC,
                    EmployeePositionEnum.NECALIFICAT,
                    EmployeePositionEnum.SOFER));

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
    private MaterialService materialService;

    @Autowired
    private PipeService pipeService;

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
    public String getHole(Model model, @PathVariable(value = "id") Integer id, HttpServletRequest httpServletRequest){
        Hole hole = holeService.getHoleById(id);

        model.addAttribute("hole", hole);
        model.addAttribute("phasePositionsMap", employeeService.getEmployeePositionsByPhases(hole.getPhases()));
        if (hole.getPhases().size() < PhaseEnum.values().length){
            PhaseEnum nextPhase = PhaseEnum.values()[hole.getPhases().size()];
            model.addAttribute("nextPhase", nextPhase);
        } else {
            model.addAttribute("nextPhase",  null);
        }
        model.addAttribute("employeesMap", employeeService.getEmployeesByPositionAsMap(siteWorkersPositions));
        model.addAttribute("allPipes", pipeService.getAllPipes());
        //        model.addAttribute("allMaterials", materialService.getAllMaterials());
        return "hole/viewHole";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String addPhase(Model model, @PathVariable(value = "id") Integer id,
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
        Map<EmployeePositionEnum, List<Employee>> employeePositionMap = employeeService.getEmployeesByPositionAsMap(siteWorkersPositions);

        model.addAttribute("hole", new Hole());
        model.addAttribute("employeesMap", employeePositionMap);

        return "hole/add/addHole";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addHole(@ModelAttribute("hole") Hole hole, @RequestParam(value = "employees") List<Integer> employeeArray,
                          HttpServletRequest httpServletRequest){

        Phase phase = new Phase();
        phase.setHole(hole);
        phase.setPhaseDate(hole.getDate());

        hole.getPhases().add(phase);

        Team team = new Team();

        for (Integer integer : employeeArray) {
            team.getEmployees().add(employeeService.getEmployeeById(integer));
        }
        phase.setTeam(team);

        holeService.saveHole(hole);
        teamService.saveTeam(team);
        phaseService.savePhase(phase);

        return "redirect:/backoffice/holes";
    }

}
