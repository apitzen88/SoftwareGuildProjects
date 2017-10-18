/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespring.controller;

import com.sg.vendingmachinespring.model.Change;
import com.sg.vendingmachinespring.model.Item;
import com.sg.vendingmachinespring.service.InvalidEntryException;
import com.sg.vendingmachinespring.service.VendingMachineService;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author apitz_000
 */
@Controller
public class VendingMachineController {

    VendingMachineService service;

    @Inject
    public VendingMachineController(VendingMachineService service) {
        this.service = service;
    }

    String itemId;
    
    @RequestMapping(value = "/displayItems", method = RequestMethod.GET)
    public String displayItems(Model model) {

        List<Item> items = service.listItems();
        BigDecimal balance = service.currentBalance();
        String message = service.currentMessage();
        Change change = service.getChange();
       
        
        model.addAttribute("items", items);
        model.addAttribute("currentBalance", balance);
        model.addAttribute("currentMessage", message);
        model.addAttribute("currentChange", change);
        model.addAttribute("selectedItem", itemId);
        
        return "vend";
    }

    @RequestMapping(value = "/addDollar", method = RequestMethod.POST)
    public String addDollar() {

        BigDecimal dollar = new BigDecimal("1.00");

        service.addMoney(dollar);

        return "redirect:displayItems";
    }

    @RequestMapping(value = "/addQuarter", method = RequestMethod.POST)
    public String addQuarter() {

        BigDecimal quarter = new BigDecimal("0.25");

        service.addMoney(quarter);

        return "redirect:displayItems";
    }

    @RequestMapping(value = "/addDime", method = RequestMethod.POST)
    public String addDime() {

        BigDecimal dime = new BigDecimal("0.10");

        service.addMoney(dime);

        return "redirect:displayItems";
    }

    @RequestMapping(value = "/addNickel", method = RequestMethod.POST)
    public String addNickel() {

        BigDecimal nickel = new BigDecimal("0.05");

        service.addMoney(nickel);

        return "redirect:displayItems";
    }

    @RequestMapping(value = "/purchaseItem", method = RequestMethod.POST)
    public String purchaseItem(HttpServletRequest request, Model model) {
        try{
        String id = request.getParameter("itemId");
        
        service.purchaseItem(id);
        
        } catch(InvalidEntryException ex){
            
        }
        return "redirect:displayItems";
    }
    
    @RequestMapping(value = "/makeChange", method = RequestMethod.POST)
    public String makeChange(Model model) {
        
        Change change = service.makeChange();
        
        model.addAttribute("currentChange", change);
        
        return "redirect:displayItems";
    }
    
    @RequestMapping(value = "/selectItem", method = RequestMethod.POST)
    public String selectItem(HttpServletRequest request, Model model) {
        
        itemId = request.getParameter("itemButton");
        
        return "redirect:displayItems";
        
    }
    
}
