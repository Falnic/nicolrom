package com.nicolrom.controllers.backoffice;

import com.nicolrom.entities.*;
import com.nicolrom.enums.EmployeePositionEnum;
import com.nicolrom.enums.PhaseEnum;
import com.nicolrom.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
                           @RequestParam(name = "pgSize", defaultValue = "13") Integer pgSize,
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

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        model.addAttribute("currentDate", dateFormat.format(date));

        return "hole/addHole";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addHole(Model model, @RequestParam(value = "holeDate") String holeDate,
                          @RequestParam(value = "street") String street,
                          @RequestParam(value = "streetNr") String streetNr,
                          @RequestParam(value = "locality") String locality,
                          @RequestParam(value = "district") String district,
                          @RequestParam(value = "holeLenght") Double holeLenght,
                          @RequestParam(value = "holeWidth") Double holeWidth,
                          @RequestParam(value = "holeDepth") Double holeDepth,
                          @RequestParam(value = "area") Integer areaId,
                          @RequestParam(value = "executor") String executor,
                          @RequestParam(value = "employees_SOFER") List<String> employeesSofer,
                          @RequestParam(value = "employees_MECANIC", required = false) List<String> employeesMecanic,
                          @RequestParam(value = "employees_NECALIFICAT", required = false) List<String> employeesNecalificat,
                          @RequestParam(value = "autoRouteDistance") Integer autoRouteDistance,
                          @RequestParam(value = "autoStationaryTime") Integer autoStationaryTime) {

        Hole hole = holeService.create(holeDate, street, streetNr, locality, district, areaId, holeLenght,
                                        holeWidth, holeDepth, executor, autoRouteDistance, autoStationaryTime);
        holeService.checkHole(hole);

        Phase phase = new Phase();
        phase.setHole(hole);
        phase.setPhaseDate(hole.getDate());

        hole.getPhases().add(phase);

        Team team = new Team();
        List<String> employeesStringArray = new ArrayList<>();

        if ("Nicol Rom".equals(executor)){
            employeesStringArray.addAll(employeesSofer);
            employeesStringArray.addAll(employeesMecanic);
            employeesStringArray.addAll(employeesNecalificat);
        } else {
            employeesStringArray.addAll(employeesSofer);
        }

        team.setEmployees(employeeService.getEmployeesById(parseEmployeesStringArray(employeesStringArray)));
        phase.setTeam(team);

        holeService.saveHole(hole);
        teamService.saveTeam(team);
        phaseService.savePhase(phase);

        return "redirect:/backoffice/holes/" + hole.getHoleId();
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateHoleGet(Model model,  @RequestParam(value = "id") String id){
        model.addAttribute("positionEmployeesMap_SOFER", employeeService.getEmployeesByPosition(EmployeePositionEnum.SOFER));
        model.addAttribute("positionEmployeesMap_MECANIC", employeeService.getEmployeesByPosition(EmployeePositionEnum.MECANIC));
        model.addAttribute("positionEmployeesMap_NECALIFICAT", employeeService.getEmployeesByPosition(EmployeePositionEnum.NECALIFICAT));
        model.addAttribute("areas", areaService.getAllAreas());

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        model.addAttribute("currentDate", dateFormat.format(date));

        Hole hole = holeService.getHoleById(Integer.parseInt(id));
        model.addAttribute("hole", hole);
        if ("Nicol Rom".equals(hole.getExecutor())){
            model.addAttribute("selectedEmployees_SOFER", prepareHoleEmployeesByPhaseString(employeeService.getHoleEmployeesByPhase(hole, PhaseEnum.SAPATURA, EmployeePositionEnum.SOFER)));
            model.addAttribute("selectedEmployees_MECANIC", prepareHoleEmployeesByPhaseString(employeeService.getHoleEmployeesByPhase(hole, PhaseEnum.SAPATURA, EmployeePositionEnum.MECANIC)));
            model.addAttribute("selectedEmployees_NECALIFICAT", prepareHoleEmployeesByPhaseString(employeeService.getHoleEmployeesByPhase(hole, PhaseEnum.SAPATURA, EmployeePositionEnum.NECALIFICAT)));
        } else {
            model.addAttribute("selectedEmployees_SOFER", prepareHoleEmployeesByPhaseString(employeeService.getHoleEmployeesByPhase(hole, PhaseEnum.SAPATURA, EmployeePositionEnum.SOFER)));
        }
        return "hole/updateHole";
    }
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateHolePut(Model model,  @RequestParam(value = "id") String id,
                                @RequestParam(value = "holeDate") String holeDate,
                                @RequestParam(value = "street") String street,
                                @RequestParam(value = "streetNr") String streetNr,
                                @RequestParam(value = "locality") String locality,
                                @RequestParam(value = "district") String district,
                                @RequestParam(value = "holeLenght") Double holeLenght,
                                @RequestParam(value = "holeWidth") Double holeWidth,
                                @RequestParam(value = "holeDepth") Double holeDepth,
                                @RequestParam(value = "area") Integer areaId,
                                @RequestParam(value = "executor") String executor,
                                @RequestParam(value = "employees_SOFER") List<String> employeesSofer,
                                @RequestParam(value = "employees_MECANIC", required = false) List<String> employeesMecanic,
                                @RequestParam(value = "employees_NECALIFICAT", required = false) List<String> employeesNecalificat,
                                @RequestParam(value = "autoRouteDistance") Integer autoRouteDistance,
                                @RequestParam(value = "autoStationaryTime") Integer autoStationaryTime) {

        Hole updatedHole = holeService.create(holeDate, street, streetNr, locality, district, areaId, holeLenght,
                holeWidth, holeDepth, executor, autoRouteDistance, autoStationaryTime);
        Hole hole = holeService.getHoleById(Integer.parseInt(id));
        updatedHole.setHoleId(hole.getHoleId());
        holeService.checkHole(hole, updatedHole);

        holeService.updateHole(updatedHole);

        updatedHole.setPhases(phaseService.createPhases(updatedHole, hole.getPhases()));

        Team updatedTeam = teamService.create(employeesSofer, employeesMecanic, employeesNecalificat, executor);
        updatedTeam.setIdTeam(hole.getPhases().get(0).getTeam().getIdTeam());

        Phase updatedPhase = updatedHole.getPhases().get(0);
        updatedPhase.setTeam(updatedTeam);
        updatedTeam.getPhases().add(updatedPhase);

        phaseService.updatePhase(updatedPhase);
        teamService.updateTeam(updatedTeam);

        return "redirect:/backoffice/holes/" + id;
    }

    private String prepareHoleEmployeesByPhaseString(List<Employee> employees){
        StringBuilder employeesString = new StringBuilder("[");
        for (Employee employee : employees){
            employeesString.append(employee.getIdEmployee()).append(',');
        }
        employeesString.deleteCharAt(employeesString.lastIndexOf(","));
        employeesString.append("]");
        return employeesString.toString();
    }

    private List<Integer> parseEmployeesStringArray(List<String> employeesStringArray){
        List<Integer> employees = new ArrayList<>();
        for (String employeeId : employeesStringArray) {
            employees.add(Integer.parseInt(employeeId));
        }
        return employees;
    }

}
