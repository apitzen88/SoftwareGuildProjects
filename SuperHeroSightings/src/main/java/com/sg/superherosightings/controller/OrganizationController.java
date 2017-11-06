/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.dao.HeroSuperPowerDao;
import com.sg.superherosightings.dao.OrganizationDao;
import com.sg.superherosightings.model.Hero;
import com.sg.superherosightings.model.Organization;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
public class OrganizationController {

    OrganizationDao orgDao;
    HeroSuperPowerDao hspDao;

    @Inject
    public OrganizationController(OrganizationDao orgDao, HeroSuperPowerDao hspDao) {
        this.orgDao = orgDao;
        this.hspDao = hspDao;
    }

    @RequestMapping(value = "/displayOrganizationsPage", method = RequestMethod.GET)
    public String displayOrganizationsPage(Model model) {

        List<Organization> orgs = orgDao.getAllOrganizations();
        
        List<Hero> heroes = hspDao.getAllHeroes();

        model.addAttribute("orgs", orgs);
        model.addAttribute("heroes", heroes);

        return "organizations";
    }

    @RequestMapping(value = "/displayAddHeroesOrg", method = RequestMethod.GET)
    public String displayAddHeroesOrg(HttpServletRequest request, Model model) {

        int orgId = Integer.valueOf(request.getParameter("orgId"));
        Organization org = orgDao.getOrganizationById(orgId);

        List<Hero> allHeroes = hspDao.getAllHeroes();
        List<Hero> orgHeroes = hspDao.getHeroesByOrganizationId(orgId);

        List<Integer> allIds = new ArrayList();
        List<Integer> orgIds = new ArrayList();

        for (Hero hero : allHeroes) {
            int id = hero.getHeroId();
            allIds.add(id);
        }
        for (Hero hero : orgHeroes) {
            int id = hero.getHeroId();
            orgIds.add(id);
        }

        List<Integer> sims = new ArrayList(allIds);
        List<Integer> diffs = new ArrayList();

        diffs.addAll(allIds);
        diffs.addAll(orgIds);

        sims.retainAll(orgIds);
        diffs.removeAll(sims);

        List<Hero> available = new ArrayList<>();

        for (int id : diffs) {
            Hero h = hspDao.getHeroById(id);
            available.add(h);
        }

        model.addAttribute("org", org);
        model.addAttribute("available", available);
        model.addAttribute("orgHeroes", orgHeroes);

        return "addHeroesOrg";
    }

    @RequestMapping(value = "/displayEditOrgForm", method = RequestMethod.GET)
    public String displayEditOrgForm(HttpServletRequest request, Model model) {

        int orgId = Integer.valueOf(request.getParameter("orgId"));

        Organization org = orgDao.getOrganizationById(orgId);

        model.addAttribute("org", org);

        return "editOrgForm";
    }

    @RequestMapping(value = "/createOrg", method = RequestMethod.POST)
    public String createOrg(HttpServletRequest request) {

        Organization org = new Organization();
        org.setOrgName(request.getParameter("orgName"));
        org.setDescription(request.getParameter("description"));
        org.setContact(request.getParameter("contact"));
        org.setBaseLocation(request.getParameter("baseLocation"));
        
        String[] selectedHeroes = request.getParameterValues("selectedHeroes");

        List<Hero> heroesToAdd = new ArrayList<>();

        try {
            if (selectedHeroes.length > 0) {
                for (int i = 0; i < selectedHeroes.length; i++) {
                    int heroId = Integer.valueOf(selectedHeroes[i]);
                    Hero hero = hspDao.getHeroById(heroId);
                    heroesToAdd.add(hero);
                }
            }
        } catch (NullPointerException x) {
            return "redirect:displayOrganizationsPage";
        }

        org.setHeroes(heroesToAdd);

        orgDao.addOrganization(org);

        return "redirect:displayOrganizationsPage";
    }

    @RequestMapping(value = "/deleteOrg", method = RequestMethod.GET)
    public String deleteOrg(HttpServletRequest request) {

        int orgId = Integer.valueOf(request.getParameter("orgId"));

        orgDao.deleteOrganization(orgId);

        return "redirect:displayOrganizationsPage";
    }

    @RequestMapping(value = "/updateOrg", method = RequestMethod.POST)
    public String updateOrg(@Valid @ModelAttribute("org") Organization org, BindingResult result) {
        
        if (result.hasErrors()) {
            return "editOrgForm";
        }
        
        int orgId = org.getOrgId();
        
        List<Hero> orgHeroes = hspDao.getHeroesByOrganizationId(orgId);
        
        org.setHeroes(orgHeroes);
        
        orgDao.updateOrganization(org);
        
        return "redirect:displayOrganizationsPage";
    }

    @RequestMapping(value = "/addHero", method = RequestMethod.POST)
    public String addHeroesOrg(HttpServletRequest request) {

        int orgId = Integer.valueOf(request.getParameter("orgId"));
        Organization org = orgDao.getOrganizationById(orgId);

        List<Hero> orgHeroes = hspDao.getHeroesByOrganizationId(orgId);

        String[] selectedHeroes = request.getParameterValues("selectedHeroes");

        List<Hero> heroesToAdd = new ArrayList<>();

        try {
            if (selectedHeroes.length > 0) {
                for (int i = 0; i < selectedHeroes.length; i++) {
                    int heroId = Integer.valueOf(selectedHeroes[i]);
                    Hero hero = hspDao.getHeroById(heroId);
                    heroesToAdd.add(hero);
                }
            }
        } catch (NullPointerException x) {
            return "redirect:displayOrganizationsPage";
        }

        orgHeroes.addAll(heroesToAdd);

        org.setHeroes(orgHeroes);
        orgDao.updateOrganization(org);

        return "redirect:displayOrganizationsPage";

    }

    @RequestMapping(value = "/removeHero", method = RequestMethod.POST)
    public String removeHeroesOrg(HttpServletRequest request) {

        int orgId = Integer.valueOf(request.getParameter("orgId"));
        Organization org = orgDao.getOrganizationById(orgId);

        List<Hero> orgHeroes = hspDao.getHeroesByOrganizationId(orgId);

        String[] selectedHeroes = request.getParameterValues("selectedRemoveHeroes");

        List<Hero> heroesToRemove = new ArrayList<>();

        try {
            if (selectedHeroes.length > 0) {
                for (int i = 0; i < selectedHeroes.length; i++) {
                    int heroId = Integer.valueOf(selectedHeroes[i]);
                    Hero hero = hspDao.getHeroById(heroId);
                    heroesToRemove.add(hero);
                }
            }
        } catch (NullPointerException x) {
            return "redirect:displayOrganizationsPage";
        }

        orgHeroes.removeAll(heroesToRemove);

        org.setHeroes(orgHeroes);
        orgDao.updateOrganization(org);

        return "redirect:displayOrganizationsPage";

    }

}
