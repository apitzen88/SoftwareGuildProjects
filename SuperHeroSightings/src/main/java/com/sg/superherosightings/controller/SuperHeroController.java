/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.dao.HeroSuperPowerDao;
import com.sg.superherosightings.dao.OrganizationDao;
import com.sg.superherosightings.model.Hero;
import com.sg.superherosightings.model.SuperPower;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author apitz_000
 */
@Controller
public class SuperHeroController {

    OrganizationDao orgDao;
    HeroSuperPowerDao hspDao;

    @Inject
    public SuperHeroController(OrganizationDao orgDao, HeroSuperPowerDao hspDao) {
        this.orgDao = orgDao;
        this.hspDao = hspDao;
    }

    @RequestMapping(value = "/displaySuperHeroesPage", method = RequestMethod.GET)
    public String displaySuperHeroesPage(Model model) {

        List<Hero> heroes = hspDao.getAllHeroes();
        
        List<SuperPower> powers = hspDao.getAllSuperPowers();

        model.addAttribute("heroes", heroes);
        model.addAttribute("powers", powers);

        return "superheroes";
    }
    
    @RequestMapping(value = "/displayEditHeroForm", method = RequestMethod.GET)
    public String displayEditHeroForm(HttpServletRequest request, Model model){
        
        int heroId = Integer.valueOf((request.getParameter("heroId")));
        
        Hero hero = hspDao.getHeroById(heroId);
        
        model.addAttribute("hero", hero);
        
        return "editHeroForm";
    }

    @RequestMapping(value = "/displayEditSuperPowers", method = RequestMethod.GET)
    public String displayEditSuperPowers(Model model) {

        List<SuperPower> powers = hspDao.getAllSuperPowers();

        model.addAttribute("powers", powers);

        return "editSuperPowers";
    }

    @RequestMapping(value = "/displayEditPowerForm", method = RequestMethod.GET)
    public String displayEditPowerForm(HttpServletRequest request, Model model) {

        int powId = Integer.valueOf(request.getParameter("powerId"));

        SuperPower power = hspDao.getSuperPowerById(powId);

        model.addAttribute("power", power);

        return "editPowerForm";
    }

    @RequestMapping(value = "/displayAddPowersHero", method = RequestMethod.GET)
    public String displayAddPowersHero(HttpServletRequest request, Model model) {

        int heroId = Integer.valueOf(request.getParameter("heroId"));
        Hero hero = hspDao.getHeroById(heroId);

        List<SuperPower> allPowers = hspDao.getAllSuperPowers();
        List<SuperPower> heroPowers = hspDao.getSuperPowersByHeroId(heroId);

        List<Integer> allIds = new ArrayList();
        List<Integer> heroIds = new ArrayList();

        for (SuperPower pow : allPowers) {
            int id = pow.getSuperPowerId();
            allIds.add(id);
        }
        for (SuperPower pow : heroPowers) {
            int id = pow.getSuperPowerId();
            heroIds.add(id);
        }

        List<Integer> sims = new ArrayList(allIds);
        List<Integer> diffs = new ArrayList();

        diffs.addAll(allIds);
        diffs.addAll(heroIds);

        sims.retainAll(heroIds);
        diffs.removeAll(sims);

        List<SuperPower> available = new ArrayList<>();

        for (int id : diffs) {
            SuperPower s = hspDao.getSuperPowerById(id);
            available.add(s);
        }

        model.addAttribute("hero", hero);
        model.addAttribute("available", available);
        model.addAttribute("heroPowers", heroPowers);

        return "addPowersHero";
    }

    @RequestMapping(value = "/addPower", method = RequestMethod.POST)
    public String addPowersHero(HttpServletRequest request) {

        int heroId = Integer.valueOf(request.getParameter("heroId"));
        Hero hero = hspDao.getHeroById(heroId);

        List<SuperPower> heroPowers = hspDao.getSuperPowersByHeroId(heroId);

        String[] selectedPowers = request.getParameterValues("selectedPowers");

        List<SuperPower> powersToAdd = new ArrayList<>();

        try {
            if (selectedPowers.length > 0) {
                for (int i = 0; i < selectedPowers.length; i++) {
                    int powerId = Integer.valueOf(selectedPowers[i]);
                    SuperPower power = hspDao.getSuperPowerById(powerId);
                    powersToAdd.add(power);
                }
            }
        } catch (NullPointerException x) {
            return "redirect:displaySuperHeroesPage";
        }

        heroPowers.addAll(powersToAdd);

        hero.setSuperPowers(heroPowers);
        hspDao.updateHero(hero);

        return "redirect:displaySuperHeroesPage";

    }

    @RequestMapping(value = "/removePower", method = RequestMethod.POST)
    public String removePowersHero(HttpServletRequest request) {

        int heroId = Integer.valueOf(request.getParameter("heroId"));
        Hero hero = hspDao.getHeroById(heroId);

        List<SuperPower> heroPowers = hspDao.getSuperPowersByHeroId(heroId);

        String[] selectedPowers = request.getParameterValues("selectedRemovePowers");

        List<SuperPower> powersToRemove = new ArrayList<>();

        try {
            if (selectedPowers.length > 0) {
                for (int i = 0; i < selectedPowers.length; i++) {
                    int powId = Integer.valueOf(selectedPowers[i]);
                    SuperPower power = hspDao.getSuperPowerById(powId);
                    powersToRemove.add(power);
                }
            }
        } catch (NullPointerException x) {
            return "redirect:displaySuperHeroesPage";
        }

        heroPowers.removeAll(powersToRemove);

        hero.setSuperPowers(heroPowers);
        hspDao.updateHero(hero);

        return "redirect:displaySuperHeroesPage";

    }

    @RequestMapping(value = "/createPower", method = RequestMethod.POST)
    public String createPower(HttpServletRequest request) {

        SuperPower power = new SuperPower();
        power.setDescription(request.getParameter("description"));

        hspDao.addSuperPower(power);

        return "redirect:displaySuperHeroesPage";

    }

    @RequestMapping(value = "/deletePower", method = RequestMethod.GET)
    public String deletePower(HttpServletRequest request) {

        int powerId = Integer.valueOf(request.getParameter("powerId"));

        hspDao.deleteSuperPower(powerId);

        return "redirect:displayEditSuperPowers";

    }

    @RequestMapping(value = "/updatePower", method = RequestMethod.POST)
    public String updatePower(@Valid @ModelAttribute("power") SuperPower power, BindingResult result) {

        if (result.hasErrors()) {
            return "editPowerForm";
        }
        
        hspDao.updateSuperPower(power);

        return "redirect:displayEditSuperPowers";

    }

    @RequestMapping(value = "/createHero", method = RequestMethod.POST)
    public String createHero(HttpServletRequest request) {

        Hero hero = new Hero();
        hero.setHeroName(request.getParameter("heroName"));
        hero.setRealName(request.getParameter("realName"));
        hero.setDescription(request.getParameter("description"));

        String[] selectedPowers = request.getParameterValues("selectedPowers");

        List<SuperPower> powersToAdd = new ArrayList<>();

        try {
            if (selectedPowers.length > 0) {
                for (int i = 0; i < selectedPowers.length; i++) {
                    int powerId = Integer.valueOf(selectedPowers[i]);
                    SuperPower power = hspDao.getSuperPowerById(powerId);
                    powersToAdd.add(power);
                }
            }
        } catch (NullPointerException x) {
            return "redirect:displaySuperHeroesPage";
        }

        hero.setSuperPowers(powersToAdd);

        hspDao.addHero(hero);

        return "redirect:displaySuperHeroesPage";

    }

    @RequestMapping(value = "/deleteHero", method = RequestMethod.GET)
    public String deleteHero(HttpServletRequest request) {

        int heroId = Integer.valueOf(request.getParameter("heroId"));

        hspDao.deleteHero(heroId);

        return "redirect:displaySuperHeroesPage";

    }
    
    @RequestMapping(value = "/updateHero", method = RequestMethod.POST)
    public String updateHero(@Valid @ModelAttribute("hero") Hero hero, BindingResult result){
        
        if (result.hasErrors()) {
            return "editHeroForm";
        }
        
        int heroId = hero.getHeroId();
        
        List<SuperPower> powers = hspDao.getSuperPowersByHeroId(heroId);
        
        hero.setSuperPowers(powers);
        
        hspDao.updateHero(hero);
        
        return "redirect:displaySuperHeroesPage";
    }
    

}
