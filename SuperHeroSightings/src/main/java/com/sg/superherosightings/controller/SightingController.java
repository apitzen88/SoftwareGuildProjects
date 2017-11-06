/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.dao.HeroSuperPowerDao;
import com.sg.superherosightings.dao.LocationDao;
import com.sg.superherosightings.dao.SightingDao;
import com.sg.superherosightings.model.Hero;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Sighting;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author apitz_000
 */
@Controller
public class SightingController {

    SightingDao sightDao;
    HeroSuperPowerDao hspDao;
    LocationDao locDao;

    @Inject
    public SightingController(SightingDao sightDao, HeroSuperPowerDao hspDao,
            LocationDao locDao) {
        this.sightDao = sightDao;
        this.hspDao = hspDao;
        this.locDao = locDao;
    }

    @RequestMapping(value = "/displaySightingsPage", method = RequestMethod.GET)
    public String displaySightingsPage(Model model) {

        List<Sighting> sightings = sightDao.getAllSightings();
        List<Hero> heores = hspDao.getAllHeroes();
        List<Location> locations = locDao.getAllLocations();

        model.addAttribute("sightings", sightings);
        model.addAttribute("heroes", heores);
        model.addAttribute("locations", locations);

        return "sightings";
    }

    @RequestMapping(value = "/displayEditSightingForm", method = RequestMethod.GET)
    public String displayEditSightingForm(HttpServletRequest request, Model model) {

        int sightingId = Integer.valueOf((request.getParameter("sightingId")));

        Sighting sighting = sightDao.geSightingById(sightingId);

        List<Location> locations = locDao.getAllLocations();

        model.addAttribute("sighting", sighting);
        model.addAttribute("locations", locations);
        model.addAttribute("selectedLoc", sighting.getLocation());

        return "editSightingForm";
    }

    @RequestMapping(value = "/displayAddHeroesSighting", method = RequestMethod.GET)
    public String displayAddHeroesSighting(HttpServletRequest request, Model model) {

        int sightingId = Integer.valueOf(request.getParameter("sightingId"));
        Sighting sighting = sightDao.geSightingById(sightingId);

        List<Hero> allHeroes = hspDao.getAllHeroes();
        List<Hero> sightingHeroes = hspDao.getHeroesBySightingId(sightingId);

        List<Integer> allIds = new ArrayList();
        List<Integer> sightingIds = new ArrayList();

        for (Hero hero : allHeroes) {
            int id = hero.getHeroId();
            allIds.add(id);
        }
        for (Hero hero : sightingHeroes) {
            int id = hero.getHeroId();
            sightingIds.add(id);
        }

        List<Integer> sims = new ArrayList(allIds);
        List<Integer> diffs = new ArrayList();

        diffs.addAll(allIds);
        diffs.addAll(sightingIds);

        sims.retainAll(sightingIds);
        diffs.removeAll(sims);

        List<Hero> available = new ArrayList<>();

        for (int id : diffs) {
            Hero h = hspDao.getHeroById(id);
            available.add(h);
        }

        model.addAttribute("sighting", sighting);
        model.addAttribute("available", available);
        model.addAttribute("sightingHeroes", sightingHeroes);

        return "addHeroesSighting";
    }

    @RequestMapping(value = "/createSighting", method = RequestMethod.POST)
    public String createSighting(HttpServletRequest request) {

        Sighting sighting = new Sighting();

        sighting.setDate(LocalDate.parse(request.getParameter("date")));

        int locId = Integer.valueOf(request.getParameter("location"));

        sighting.setLocation(locDao.getLocationById(locId));

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
            return "redirect:displaySightingsPage";
        }

        sighting.setHeroes(heroesToAdd);

        sightDao.addSighting(sighting);

        return "redirect:displaySightingsPage";

    }

    @RequestMapping(value = "/deleteSighting", method = RequestMethod.GET)
    public String deleteSighting(HttpServletRequest request) {

        int sightingId = Integer.valueOf(request.getParameter("sightingId"));

        sightDao.deleteSighting(sightingId);

        return "redirect:displaySightingsPage";

    }

    @RequestMapping(value = "/updateSighting", method = RequestMethod.POST)
    public String updateSighting(@Valid HttpServletRequest request) {
        
        int sightingId = Integer.valueOf(request.getParameter("sightingId"));
        Sighting sighting = sightDao.geSightingById(sightingId);

        sighting.setDate(LocalDate.parse(request.getParameter("date")));

        int locId = Integer.valueOf(request.getParameter("location"));

        Location loc = locDao.getLocationById(locId);

        sighting.setLocation(loc);

        List<Hero> heroes = hspDao.getHeroesBySightingId(sightingId);

        sighting.setHeroes(heroes);

        sightDao.updateSighting(sighting);

        return "redirect:displaySightingsPage";

    }

    @RequestMapping(value = "/addHeroSighting", method = RequestMethod.POST)
    public String addHeroesSighting(HttpServletRequest request) {

        int sightingId = Integer.valueOf(request.getParameter("sightingId"));
        Sighting sighting = sightDao.geSightingById(sightingId);

        List<Hero> sightingHeroes = hspDao.getHeroesBySightingId(sightingId);

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
            return "redirect:displaySightingsPage";
        }

        sightingHeroes.addAll(heroesToAdd);

        sighting.setHeroes(sightingHeroes);
        sightDao.updateSighting(sighting);

        return "redirect:displaySightingsPage";

    }

    @RequestMapping(value = "/removeHeroSighting", method = RequestMethod.POST)
    public String removeHeroesSighting(HttpServletRequest request) {

        int sightingId = Integer.valueOf(request.getParameter("sightingId"));
        Sighting sighting = sightDao.geSightingById(sightingId);

        List<Hero> sightingHeroes = hspDao.getHeroesBySightingId(sightingId);

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
            return "redirect:displaySightingsPage";
        }

        sightingHeroes.removeAll(heroesToRemove);

        sighting.setHeroes(sightingHeroes);
        sightDao.updateSighting(sighting);

        return "redirect:displaySightingsPage";

    }

    @RequestMapping(value = "/displayIndexPage", method = RequestMethod.GET)
    public String displayIndexPage(Model model) {

        List<Sighting> sightings = sightDao.getRecentSightingsByDate();
        
        model.addAttribute("sightings", sightings);

        return "recentSightings";
        
    }

}
