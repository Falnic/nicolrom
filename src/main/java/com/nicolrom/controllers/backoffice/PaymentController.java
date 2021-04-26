package com.nicolrom.controllers.backoffice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nicolrom.entities.Contract;
import com.nicolrom.entities.Hole;
import com.nicolrom.entities.Volume;
import com.nicolrom.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class PaymentController {

    @Autowired
    private ContractService contractService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private HoleService holeService;

    @Autowired
    private VolumeService volumeService;

    @RequestMapping(value = "/backoffice/contracts", method = RequestMethod.GET)
    public String getContracts(Model model){
        model.addAttribute("contracts", contractService.getAllContracts());
        return "payment/viewContracts";
    }

    @RequestMapping(value = "/backoffice/contracts/{id}", method = RequestMethod.GET)
    public String getContract(Model model, @PathVariable(value = "id") Integer id){
        model.addAttribute("contract", contractService.getContractById(id));
        return "payment/viewContract";
    }

    @RequestMapping(value = "/backoffice/contracts/{id}", method = RequestMethod.POST)
    public String modifyContractPrices(Model model, @PathVariable(value = "id") Integer id,
                                       @RequestParam(value = "articleId") List<String> articlesId,
                                       @RequestParam(value = "articlePrice") List<String> articlesPrice){

        articleService.updateArticles(articleService.getArticlesById(parseIntegerList(articlesId)), parseDoubleList(articlesPrice));
        model.addAttribute("contract", contractService.getContractById(id));
        model.addAttribute("succes", true);
        return "payment/viewContract";
    }

    @RequestMapping(value = "/backoffice/volumes", method = RequestMethod.GET)
    public String getVolumes(Model model){
        return "payment/viewVolumes";
    }

    @RequestMapping(value = "/backoffice/volumes/add", method = RequestMethod.GET)
    public String addVolume(Model model){
        model.addAttribute("contracts", contractService.getAllContracts());
        model.addAttribute("districts", addressService.getAllDistricts());
        model.addAttribute("currentDate", getCurrentDate());
        model.addAttribute("holes", holeService.getHolesWithoutVolume());
        return "payment/addVolume";
    }

    @RequestMapping(value = "/backoffice/volumes/add-getHolesByDistrict")
    @ResponseBody
    public String getHolesByDistrict(@RequestParam(name = "district") String district) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(holeService.getHolesWithoutVolume(district));
    }

    @RequestMapping(value = "/backoffice/volumes/add-getHolesByDatePeriod")
    @ResponseBody
    public String getHolesByDatePeriod(@RequestParam(name = "startDate") String startDate,
                                       @RequestParam(name = "endDate") String endDate) throws JsonProcessingException, ParseException {
        return new ObjectMapper().writeValueAsString(holeService.getHolesWithoutVolume(parseDate(startDate), parseDate(endDate)));
    }

    @RequestMapping(value = "/backoffice/volumes/add-getHolesByDatePeriodAndDistrict")
    @ResponseBody
    public String getHolesByDatePeriodAndDistrict(@RequestParam(name = "startDate") String startDate,
                                                  @RequestParam(name = "endDate") String endDate,
                                                  @RequestParam(name = "district") String district) throws JsonProcessingException, ParseException {
        return new ObjectMapper().writeValueAsString(holeService.getHolesWithoutVolume(parseDate(startDate), parseDate(endDate), district));
    }

    @RequestMapping(value = "/backoffice/volumes/add", method = RequestMethod.POST)
    public String addVolume(Model model, @RequestParam(name = "volumeNr") String volumeNr,
                            @RequestParam(name = "contract") String contractId,
                            @RequestParam(name = "regNr") String regNr,
                            @RequestParam(name = "district") String district,
                            @RequestParam(name = "startDate") String startDate,
                            @RequestParam(name = "endDate") String endDate,
                            @RequestParam(name = "holes") List<String> holesId) throws ParseException {
        Contract contract = contractService.getContractById(Integer.parseInt(contractId));
        List<Hole> holes = holeService.getHolesById(parseIntegerList(holesId));
        Volume volume = volumeService.createVolume(contract, regNr, district, parseDate(startDate), parseDate(endDate), holes);

        return "payment/viewVolume";
    }

    @RequestMapping(value = "/backoffice/volumes/1", method = RequestMethod.GET)
    public String getVolume(Model model){
        return "payment/viewVolume";
    }

    @RequestMapping(value = "/backoffice/volumes/1/update", method = RequestMethod.GET)
    public String updateVolume(Model model){
        return "payment/updateVolume";
    }

    private List<Double> parseDoubleList(List<String> stringList){
        List<Double> doubles = new ArrayList<>();
        for (String s : stringList){
            doubles.add(Double.parseDouble(s));
        }
        return doubles;
    }

    private List<Integer> parseIntegerList(List<String> stringList){
        List<Integer> integers = new ArrayList<>();
        for (String s : stringList){
            integers.add(Integer.parseInt(s));
        }
        return integers;
    }

    private String getCurrentDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

    private Date parseDate(String date) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(date);
    }
}
