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
import javax.servlet.http.HttpSession;
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

    @RequestMapping(method = RequestMethod.GET)
    public String getHoles(Model model){
        model.addAttribute("allHoles", holeService.getAllHoles());
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
        model.addAttribute("allMaterials", materialService.getAllMaterials());
        return "hole/viewHole";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String addPhase(Model model, @PathVariable(value = "id") Integer id,
                           @RequestParam(value = "phaseDate") Date phaseDate,
                           @RequestParam(value = "employees") List<Integer> employeeArray,
                           @RequestParam(value = "materials") List<Integer> materialArray,
                           @RequestParam(value = "materialsQuantity") List<Integer> materialQuantityArray,
                           @RequestParam(value = "nextPhase") PhaseEnum nextPhase){
        Phase phase = new Phase();
        phase.setPhaseType(nextPhase);
        phase.setPhaseDate(phaseDate);

        Hole hole = holeService.getHoleById(id);
        phase.setHole(hole);

        Team team = new Team();
        team.setEmployees(employeeService.getEmployeesById(employeeArray));
        phase.setTeam(team);

        phase.setMaterialNoticeSet(materialNoticeService.getMaterialNoticeSet(phase, materialArray, materialQuantityArray));

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

        // todo: team needs to be send via model and not to create a new one
        Team team = new Team();

        //todo: replace db call with ajax call
        for (Integer integer : employeeArray) {
            team.getEmployees().add(employeeService.getEmployeeById(integer));
        }
        phase.setTeam(team);

        holeService.saveHole(hole);
        teamService.saveTeam(team);
        phaseService.savePhase(phase);

        return "redirect:/backoffice/holes";
    }

    @RequestMapping(value = "/add-team", method = RequestMethod.GET)
    public String addTeam(Model model){

        Map<EmployeePositionEnum, List<Employee>> employeePositionMap = employeeService.getEmployeesByPositionAsMap(siteWorkersPositions);

        model.addAttribute("team", new Team());
        model.addAttribute("employeesMap", employeePositionMap);

        return "hole/add/addTeam";
    }

    @RequestMapping(value = "/add-team", method = RequestMethod.POST)
    public  String addTeamToHole(Model model, @RequestParam(value = "employees") List<Integer> employeeArray,
                                 HttpServletRequest httpServletRequest){

        HttpSession session = httpServletRequest.getSession(true);
        Hole hole = (Hole) session.getAttribute("hole");
        // todo: team needs to be send via model and not to create a new one
        Team team = new Team();

        //todo: replace db call with ajax call
        for (Integer integer : employeeArray) {
            team.getEmployees().add(employeeService.getEmployeeById(integer));
        }


        holeService.saveHole(hole);
        teamService.saveTeam(team);

        Phase phase = hole.getPhases().iterator().next();
        phase.setTeam(team);

        phaseService.savePhase(phase);

        return "redirect:/backoffice/holes";
    }

    @RequestMapping(value = "/add-materials", method = RequestMethod.POST)
    public String addMaterialsToHole(Model model, @RequestParam(value = "materialId") List<Integer> materialIdArray,
                                     @RequestParam(value = "materialQuantity") List<Integer> materialQuantityArray,
                                     HttpServletRequest httpServletRequest){

        //get all Materials By Id
        Map<Material, Integer> materialIntegerMap = new HashMap<>();
        for (int i = 0; i < materialQuantityArray.size(); i++){
            int quantity = materialQuantityArray.get(i);
            if(quantity != 0){
                Material material = materialService.getMaterialById(materialIdArray.get(i));
                materialIntegerMap.put(material, quantity);
            }
        }

        HttpSession session = httpServletRequest.getSession(true);
        Hole hole = (Hole) session.getAttribute("hole");

        PhaseEnum phaseEnum = (PhaseEnum) session.getAttribute("phaseType");
        Phase phase = holeService.getHolePhaseByType(hole, phaseEnum);

        phase.setMaterialNoticeSet(prepareMaterialsNotice(materialIntegerMap, phase));

        holeService.saveHole(hole);
        teamService.saveTeam(phase.getTeam());
        phaseService.savePhase(phase);
        materialNoticeService.saveMaterialNotices(phase.getMaterialNoticeSet());

        return "redirect:/backoffice/holes";
    }

    private Set<MaterialNotice> prepareMaterialsNotice (Map<Material, Integer> materialIntegerMap, Phase phase){
        Set<MaterialNotice> materialNoticeList = new HashSet<>();
        for(Map.Entry<Material, Integer> entry : materialIntegerMap.entrySet()){
            MaterialNotice materialNotice = new MaterialNotice();
            materialNotice.setMaterial(entry.getKey());
            materialNotice.setQuantity(entry.getValue());
            materialNotice.setPhase(phase);

            materialNoticeList.add(materialNotice);
        }

        return materialNoticeList;
    }

}
