package com.nicolrom.controllers.backoffice;

import com.nicolrom.services.ArticleService;
import com.nicolrom.services.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PaymentController {

    @Autowired
    private ContractService contractService;

    @Autowired
    private ArticleService articleService;

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
        return "payment/addVolume";
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
}
