package com.nicolrom.controllers.backoffice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nicolrom.entities.*;
import com.nicolrom.entities.dto.HoleDTO;
import com.nicolrom.enums.EmployeePositionEnum;
import com.nicolrom.enums.PhaseEnum;
import com.nicolrom.services.*;
import com.nicolrom.translators.HoleTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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
    private MaterialService materialService;

    @Autowired
    private PipeService pipeService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private HoleTranslator holeTranslator;

    @RequestMapping(method = RequestMethod.GET)
    public String getHoles(Model model, @RequestParam(name = "pgNr", defaultValue = "0") Integer pgNr,
                           @RequestParam(name = "pgSize", defaultValue = "13") Integer pgSize,
                           @RequestParam(name = "orderBy", defaultValue = "Implicit") String orderBy,
                           @RequestParam(value = "searchValue", required = false) String searchValue,
                           @RequestParam(value = "district", required = false) String[] districts){

        List<HoleDTO> holes;
        if (searchValue != null && !searchValue.isEmpty()) {
            holes = holeService.searchHolesByAddress(searchValue);
            holes = holeService.getHolesOrdered(holes, orderBy);
            if(districts != null){
                holes = holeService.filterHolesByDistricts(holes, Arrays.asList(districts));
                model.addAttribute("checkedDistricts", Arrays.asList(districts));
                model.addAttribute("lastPg", Math.ceil(holes.size() / pgSize));
            } else {
                model.addAttribute("lastPg", holeService.getLastPageNr(pgSize, searchValue));
            }
            model.addAttribute("searchValue", searchValue);
        } else {
            if (districts != null){
                holes = holeService.getHolesByDistricts(districts);
                model.addAttribute("checkedDistricts", Arrays.asList(districts));
                model.addAttribute("lastPg",holeService.getLastPageNr(pgSize, districts));
            } else {
                holes = holeService.getAllHoles(pgNr, pgSize, orderBy);
                model.addAttribute("lastPg",holeService.getLastPageNr(pgSize));
            }
        }

        model.addAttribute("allHoles", holes);
        model.addAttribute("pgNr", pgNr);
        model.addAttribute("orderBy", orderBy);
        model.addAttribute("orderByOptions", holeTranslator.translateOrderOptions());
        model.addAttribute("districts", holeService.getHoleDistricts());

        return "hole/viewHoles";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addHole(Model model){
        List<Employee> employees_SOFER = employeeService.getEmployeesByPosition(EmployeePositionEnum.SOFER);
        model.addAttribute("positionEmployeesMap_SOFER", employees_SOFER);
        model.addAttribute("positionEmployeesMap_MECANIC", employeeService.getEmployeesByPosition(EmployeePositionEnum.MECANIC));
        model.addAttribute("positionEmployeesMap_NECALIFICAT", employeeService.getEmployeesByPosition(EmployeePositionEnum.NECALIFICAT));
        model.addAttribute("areas", areaService.getAllAreas());
        model.addAttribute("localities", addressService.getAllLocalities());

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonStr = objectMapper.writeValueAsString(employees_SOFER);
            model.addAttribute("employeesJSON_SOFER", jsonStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        model.addAttribute("currentDate", dateFormat.format(date));

        return "hole/addHole";
    }

    @RequestMapping(value = "/add-getStreets", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getStreetsList(@RequestParam(name = "locality") String locality){
        return addressService.getStreetsByLocality(locality);
    }

    @RequestMapping(value = "/add-getDistrict", method = RequestMethod.GET)
    @ResponseBody
    public String getStreetDistrict(@RequestParam(name = "street") String street){
        return addressService.getDistrictByStreet(street);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addHole(Model model, @RequestParam(value = "holeDate") String holeDate,
                          @RequestParam(value = "street") String street,
                          @RequestParam(value = "streetNr") String streetNr,
                          @RequestParam(value = "locality") String locality,
                          @RequestParam(value = "county") String county,
                          @RequestParam(value = "district") String district,
                          @RequestParam(value = "area") Integer areaId,
                          @RequestParam(value = "holeLenght") Double holeLenght,
                          @RequestParam(value = "holeWidth") Double holeWidth,
                          @RequestParam(value = "holeDepth") Double holeDepth,
                          @RequestParam(value = "executor") String executor,
                          @RequestParam(value = "employees_SOFER", required = false) List<String> employeesSofer,
                          @RequestParam(value = "employees_MECANIC", required = false) List<String> employeesMecanic,
                          @RequestParam(value = "employees_NECALIFICAT", required = false) List<String> employeesNecalificat,
                          @RequestParam(value = "machineSelect_SOFER", required = false) List<String> machineriesSofer,
                          @RequestParam(value = "autoRouteDistance") Double autoRouteDistance,
                          @RequestParam(value = "autoStationaryTime") Integer autoStationaryTime) {

        Hole hole = holeService.create(holeDate, street, streetNr, locality, county, district, areaId, holeLenght,
                                        holeWidth, holeDepth, executor, autoRouteDistance, autoStationaryTime, null);
        // TODO: Check Same Holes
//        String messaje = holeService.checkHole(hole);
//        if (messaje != null){
//            model.addAttribute("error", messaje);
//            return addHole(model);
//        }

        holeService.saveHole(hole);

        Phase phase = new Phase();
        phase.setHole(hole);
        phase.setPhaseDate(hole.getDate());
        phaseService.savePhase(phase);

        Team team = teamService.create(employeesSofer, employeesMecanic, employeesNecalificat, machineriesSofer);
        team.setPhase(phase);
        teamService.saveTeam(team);

        return "redirect:/backoffice/holes/" + hole.getHoleId();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getHole(Model model, @PathVariable(value = "id") Integer id){
        Hole hole = holeService.getHoleById(id);
        model.addAttribute("hole", hole);
        List<PhaseEnum> phaseEnums = Arrays.asList(PhaseEnum.values());
        model.addAttribute("allPhasesEnum", phaseEnums);
        model.addAttribute("tabPhases", phaseService.getPhasesByPhaseTypeMap(phaseEnums, hole.getPhases()));
        PhaseEnum nextPhase = phaseService.getNextPhase(hole.getPhases());
        if (nextPhase.equals(PhaseEnum.UMPLERE)){
            model.addAttribute("allPipes", pipeService.getAllPipes());
        }
        model.addAttribute("nextPhase", nextPhase);
        List<Employee> employees_SOFER = employeeService.getEmployeesByPosition(EmployeePositionEnum.SOFER);
        model.addAttribute("positionEmployeesMap_SOFER", employees_SOFER);
        model.addAttribute("positionEmployeesMap_MECANIC", employeeService.getEmployeesByPosition(EmployeePositionEnum.MECANIC));
        model.addAttribute("positionEmployeesMap_NECALIFICAT", employeeService.getEmployeesByPosition(EmployeePositionEnum.NECALIFICAT));
        model.addAttribute("materials", materialService.getAllMaterials());

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        model.addAttribute("currentDate", dateFormat.format(date));

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonStr = objectMapper.writeValueAsString(employees_SOFER);
            model.addAttribute("employeesJSON_SOFER", jsonStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "hole/viewHole";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String addPhase(@PathVariable(value = "id") Integer id,
                           @RequestParam(value = "phaseDate") String phaseDate,
                           @RequestParam(value = "pipe") String pipeDiameter,
                           @RequestParam(value = "nextPhase") PhaseEnum nextPhase,
                           @RequestParam(value = "materialId", required = false) List<String> materialIds,
                           @RequestParam(value = "material", required = false) List<String> materialsValue,
                           @RequestParam(value = "employees_SOFER", required = false) List<String> employeesSofer,
                           @RequestParam(value = "employees_MECANIC", required = false) List<String> employeesMecanic,
                           @RequestParam(value = "employees_NECALIFICAT", required = false) List<String> employeesNecalificat,
                           @RequestParam(value = "machineSelect_SOFER", required = false) List<String> machineriesSofer){

        Hole hole = holeService.getHoleById(id);
        Phase phase = phaseService.createHolePhase(hole, phaseDate, nextPhase);

        hole.getPhases().add(phase);
        hole.setPipe(pipeService.getPipeByDiameter(pipeDiameter));
        holeService.updateHole(hole);
        phaseService.savePhase(phase);

        Team team = teamService.create(employeesSofer, employeesMecanic, employeesNecalificat, machineriesSofer);
        team.setPhase(phase);
        teamService.saveTeam(team);

        List<Integer> materialsIdsParsed = materialIds.stream().map(Integer::parseInt).collect(Collectors.toList());
        List<Double> materialsValuesParsed = materialsValue.stream().map(Double::parseDouble).collect(Collectors.toList());

        Set<MaterialNotice> materialNoticeSet = materialNoticeService.getMaterialNoticeSet(phase, materialsIdsParsed, materialsValuesParsed);

        materialNoticeService.saveMaterialNotice(materialNoticeSet);

        return "redirect:/backoffice/holes/{id}";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateHole(Model model, @RequestParam(value = "id") String id){

        Hole hole = holeService.getHoleById(Integer.parseInt(id));
        model.addAttribute("hole", hole);

        List<Employee> employees_SOFER = employeeService.getEmployeesByPosition(EmployeePositionEnum.SOFER);
        model.addAttribute("positionEmployeesMap_SOFER", employees_SOFER);
        model.addAttribute("positionEmployeesMap_MECANIC", employeeService.getEmployeesByPosition(EmployeePositionEnum.MECANIC));
        model.addAttribute("positionEmployeesMap_NECALIFICAT", employeeService.getEmployeesByPosition(EmployeePositionEnum.NECALIFICAT));
        model.addAttribute("areas", areaService.getAllAreas());
        model.addAttribute("allPipes", pipeService.getAllPipes());

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        model.addAttribute("currentDate", dateFormat.format(date));

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonStr = objectMapper.writeValueAsString(employees_SOFER);
            model.addAttribute("employeesJSON_SOFER", jsonStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        for (Phase phase : hole.getPhases()){
            switch (phase.getPhaseType()){
                    case SAPATURA:

                        List<Employee> employeeSofer = employeeService.getEmployeesByPositions(phase.getTeam(), EmployeePositionEnum.SOFER);
                        if (employeeSofer != null && !employeeSofer.isEmpty()){
                            model.addAttribute("selectedEmployees_SOFER", prepareHoleEmployeesByPhaseString(employeeSofer));
                        }

                        List<Employee> employeeMecanic = employeeService.getEmployeesByPositions(phase.getTeam(), EmployeePositionEnum.MECANIC);
                        if (employeeMecanic != null && !employeeMecanic.isEmpty()){
                            model.addAttribute("selectedEmployees_MECANIC", prepareHoleEmployeesByPhaseString(employeeMecanic));
                        }

                        List<Employee> employeeNecalificat = employeeService.getEmployeesByPositions(phase.getTeam(), EmployeePositionEnum.NECALIFICAT);
                        if (employeeNecalificat != null && !employeeNecalificat.isEmpty()){
                            model.addAttribute("selectedEmployees_NECALIFICAT", prepareHoleEmployeesByPhaseString(employeeNecalificat));
                        }

                        model.addAttribute("teamDeploys_SAPATURA", phase.getTeam().getTeamDeploys());
                        break;
                    case UMPLERE:
                        List<Material> exceptedMaterials = materialService.getMaterials(materialNoticeService.getMaterialNoticeSet(hole));
                        model.addAttribute("materials", materialService.getAllMaterialsExcept(exceptedMaterials));

                        List<Employee> employeeSofer_UMPLERE = employeeService.getEmployeesByPositions(phase.getTeam(), EmployeePositionEnum.SOFER);
                        if (employeeSofer_UMPLERE != null && !employeeSofer_UMPLERE.isEmpty()){
                            model.addAttribute("selectedEmployees_SOFER_UMPLERE", prepareHoleEmployeesByPhaseString(employeeSofer_UMPLERE));
                        }

                        List<Employee> employeeMecanic_UMPLERE = employeeService.getEmployeesByPositions(phase.getTeam(), EmployeePositionEnum.MECANIC);
                        if (employeeMecanic_UMPLERE != null && !employeeMecanic_UMPLERE.isEmpty()){
                            model.addAttribute("selectedEmployees_MECANIC_UMPLERE", prepareHoleEmployeesByPhaseString(employeeMecanic_UMPLERE));
                        }

                        List<Employee> employeeNecalificat_UMPLERE = employeeService.getEmployeesByPositions(phase.getTeam(), EmployeePositionEnum.NECALIFICAT);
                        if (employeeNecalificat_UMPLERE != null && !employeeNecalificat_UMPLERE.isEmpty()){
                            model.addAttribute("selectedEmployees_NECALIFICAT_UMPLERE", prepareHoleEmployeesByPhaseString(employeeNecalificat_UMPLERE));
                        }

                        model.addAttribute("teamDeploys_UMPLERE", phase.getTeam().getTeamDeploys());
                        break;
                }
        }
        return "hole/updateHole";
    }
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateHole(Model model, @RequestParam(value = "id") String id,
                             @RequestParam(value = "street") String street,
                             @RequestParam(value = "streetNr") String streetNr,
                             @RequestParam(value = "locality") String locality,
                             @RequestParam(value = "county") String county,
                             @RequestParam(value = "district") String district,
                             @RequestParam(value = "holeLenght") Double holeLenght,
                             @RequestParam(value = "holeWidth") Double holeWidth,
                             @RequestParam(value = "holeDepth") Double holeDepth,
                             @RequestParam(value = "area") Integer areaId,
                             @RequestParam(value = "SAPATURA_Date") String phaseDate_SAPATURA,
                             @RequestParam(value = "executor") String executor,
                             @RequestParam(value = "employees_SOFER", required = false) List<String> employeesSofer,
                             @RequestParam(value = "employees_MECANIC", required = false) List<String> employeesMecanic,
                             @RequestParam(value = "employees_NECALIFICAT", required = false) List<String> employeesNecalificat,
                             @RequestParam(value = "machineSelectSAPATURA_SOFER", required = false) List<String> machineriesSAPATURASofer,
                             @RequestParam(value = "autoRouteDistance") Double autoRouteDistance,
                             @RequestParam(value = "autoStationaryTime") Integer autoStationaryTime,
                             @RequestParam(value = "UMPLERE_Date", required = false) String phaseDate_UMPLERE,
                             @RequestParam(value = "pipe", required = false) String pipeDiameter,
                             @RequestParam(value = "employees_SOFER_UMPLERE", required = false) List<String> employeesSofer_UMPLERE,
                             @RequestParam(value = "employees_MECANIC_UMPLERE", required = false) List<String> employeesMecanic_UMPLERE,
                             @RequestParam(value = "employees_NECALIFICAT_UMPLERE", required = false) List<String> employeesNecalificat_UMPLERE,
                             @RequestParam(value = "machineSelectUMPLERE_SOFER", required = false) List<String> machineriesUMPLERESofer,
                             @RequestParam(value = "materialId", required = false) List<String> materialIds,
                             @RequestParam(value = "material", required = false) List<String> materialsValue,
                             @RequestParam(value = "phaseEnums") List<String> phases) {

        Hole updatedHole = holeService.create(phaseDate_SAPATURA, street, streetNr, locality, county, district, areaId,
                holeLenght, holeWidth, holeDepth, executor, autoRouteDistance, autoStationaryTime, pipeDiameter);

        Hole hole = holeService.getHoleById(Integer.parseInt(id));
        updatedHole.setHoleId(hole.getHoleId());
//        String messaje =  holeService.checkHole(hole, updatedHole);
//        if (messaje != null){
//            model.addAttribute("error", messaje);
//            return updateHole(model, id);
//        }

        holeService.updateHole(updatedHole);

        List<PhaseEnum> phaseEnums = parsePhaseEnums(phases);
        for (PhaseEnum phaseEnum : phaseEnums){
            Phase updatedPhase = new Phase();
            Team updatedTeam;
            switch (phaseEnum){
                case SAPATURA:
                    updatedPhase = phaseService.createHolePhase(updatedHole, phaseDate_SAPATURA, PhaseEnum.SAPATURA);
                    Phase holePhase_SAPATURA = phaseService.getHolePhaseByPhaseType(hole, PhaseEnum.SAPATURA);
                    updatedPhase.setPhaseId(holePhase_SAPATURA.getPhaseId());
                    updatedTeam = teamService.create(employeesSofer, employeesMecanic, employeesNecalificat, machineriesSAPATURASofer);
                    updatedTeam.setPhase(updatedPhase);
                    if (holePhase_SAPATURA.getTeam() != null){
                        updatedTeam.setIdTeam(holePhase_SAPATURA.getTeam().getIdTeam());
                    }
                    updatedPhase.setTeam(updatedTeam);
                    teamService.updateTeam(updatedTeam, holePhase_SAPATURA.getTeam());
                    break;

                case UMPLERE:
                    updatedPhase = phaseService.createHolePhase(updatedHole, phaseDate_UMPLERE, PhaseEnum.UMPLERE);
                    Phase holePhase_UMPLERE = phaseService.getHolePhaseByPhaseType(hole, PhaseEnum.UMPLERE);
                    updatedPhase.setPhaseId(holePhase_UMPLERE.getPhaseId());
                    updatedTeam = teamService.create(employeesSofer_UMPLERE, employeesMecanic_UMPLERE,
                            employeesNecalificat_UMPLERE, machineriesUMPLERESofer);
                    updatedTeam.setPhase(updatedPhase);
                    if (holePhase_UMPLERE.getTeam() != null){
                        updatedTeam.setIdTeam(holePhase_UMPLERE.getTeam().getIdTeam());
                    }
                    updatedPhase.setTeam(updatedTeam);
                    teamService.updateTeam(updatedTeam, holePhase_UMPLERE.getTeam());

                    List<Integer> materialsIdsParsed = materialIds.stream().map(Integer::parseInt).collect(Collectors.toList());
                    List<Double> materialsValuesParsed = materialsValue.stream().map(Double::parseDouble).collect(Collectors.toList());

                    Set<MaterialNotice> updatedMaterialNoticeSet = materialNoticeService.getMaterialNoticeSet(updatedPhase, materialsIdsParsed, materialsValuesParsed);

                    updatedPhase.setMaterialNoticeSet(updatedMaterialNoticeSet);
                    materialNoticeService.updateMaterialNotice(holePhase_UMPLERE.getMaterialNoticeSet(), updatedMaterialNoticeSet);
                    break;
            }
            updatedHole.getPhases().add(updatedPhase);
            phaseService.updatePhase(updatedPhase);
        }

        return "redirect:/backoffice/holes/" + id;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteHole(@RequestParam(value = "id") String id){
        Hole hole = holeService.getHoleById(Integer.parseInt(id));
        phaseService.deletePhase(hole.getPhases());
        holeService.deleteHole(hole);
        return "redirect:/backoffice/holes";
    }

    private String prepareHoleEmployeesByPhaseString(List<Employee> employees){
        if (employees.size() > 0){
            StringBuilder employeesString = new StringBuilder("[");
            for (Employee employee : employees){
                employeesString.append(employee.getIdEmployee()).append(',');
            }
            employeesString.deleteCharAt(employeesString.lastIndexOf(","));
            employeesString.append("]");
            return employeesString.toString();
        } else {
            return "";
        }
    }

    private List<Integer> parseStringArray(List<String> stringArray){
        List<Integer> intValues = new ArrayList<>();
        for (String intValue : stringArray) {
            intValues.add(Integer.parseInt(intValue));
        }
        return intValues;
    }

    private List<PhaseEnum> parsePhaseEnums(List<String> phaseEnums){
        List<PhaseEnum> result = new ArrayList<>();
        List<PhaseEnum> enums = Arrays.asList(PhaseEnum.values());
        for (String phaseEnumString : phaseEnums){
            if (enums.contains(PhaseEnum.valueOf(phaseEnumString))){
                result.add(PhaseEnum.valueOf(phaseEnumString));
            }
        }
        return result;
    }
}
