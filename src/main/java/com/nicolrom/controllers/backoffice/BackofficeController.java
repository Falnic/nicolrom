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

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addHole(Model model){
        model.addAttribute("hole", new Hole());
        return "hole/addHole";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addHole(Model model, @ModelAttribute("hole") Hole hole, HttpServletRequest httpServletRequest){

        HttpSession session = httpServletRequest.getSession(true);
        session.setAttribute("hole", hole);

        model.addAttribute("phase", new Phase());
        model.addAttribute("allPhaseTypes", PhaseEnum.values());
        return "hole/addPhase";
    }

    @RequestMapping(value = "/add-phase", method = RequestMethod.POST)
    public String addPhaseToHole(Model model, @ModelAttribute("phase") Phase phase, HttpServletRequest httpServletRequest){

        HttpSession session = httpServletRequest.getSession(true);
        Hole hole = (Hole) session.getAttribute("hole");

        Team team = new Team();

        phase.setTeam(team);
        phase.setHole(hole);

        Set<Phase> phaseSet = new HashSet<>();
        phaseSet.add(phase);

        hole.setPhases(phaseSet);
        team.setPhases(phaseSet);

        session.setAttribute("hole", hole);
        session.setAttribute("phaseType", phase.getPhaseType());

        model.addAttribute("team", team);

        Map<EmployeePositionEnum, List<Employee>> employeePositionMap = employeeService.getEmployeesByPositionAsMap(siteWorkersPositions);

        model.addAttribute("employeesMap", employeePositionMap);

        return "hole/addTeam";
    }

    @RequestMapping(value = "/add-team", method = RequestMethod.POST)
    public  String addTeamToHole(Model model, @RequestParam(value = "employees") List<Integer> employeeArray,
                                 HttpServletRequest httpServletRequest){

        HttpSession session = httpServletRequest.getSession(true);
        Hole hole = (Hole) session.getAttribute("hole");

        PhaseEnum phaseEnum = (PhaseEnum) session.getAttribute("phaseType");

        // TODO: Create a method in service for this aproach
        Phase phase = holeService.getHolePhaseByType(hole, phaseEnum);
        Team team = phase.getTeam();

        //todo: replace db call with spring form for each employee
        for (Integer integer : employeeArray) {
            team.getEmployees().add(employeeService.getEmployeeById(integer));
        }

        session.setAttribute("hole", hole);

        model.addAttribute("allMaterials", materialService.getAllMaterials());

        return "hole/addMaterials";
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
