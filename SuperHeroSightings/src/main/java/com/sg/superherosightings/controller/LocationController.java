/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.dao.LocationDao;
import com.sg.superherosightings.model.Location;
import static java.lang.Double.parseDouble;
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
public class LocationController {

    LocationDao locDao;

    @Inject
    public LocationController(LocationDao locDao) {
        this.locDao = locDao;
    }

    @RequestMapping(value = "/displayLocationsPage", method = RequestMethod.GET)
    public String displayLocationsPage(Model model) {

        List<Location> locations = locDao.getAllLocations();

        model.addAttribute("locations", locations);

        return "locations";
    }

    @RequestMapping(value = "/createLocation", method = RequestMethod.POST)
    public String createLocation(HttpServletRequest request) {

        Location location = new Location();
        location.setLocationName(request.getParameter("locationName"));
        location.setDescription(request.getParameter("description"));
        location.setAddress(request.getParameter("address"));
        location.setCity(request.getParameter("city"));
        location.setLatitude(Double.valueOf(request.getParameter("latitude")));
        location.setLongitude(Double.valueOf(request.getParameter("longitude")));
        
        locDao.addLocation(location);

        return "redirect:displayLocationsPage";
    }

    @RequestMapping(value = "/deleteLocation", method = RequestMethod.GET)
    public String deleteLocation(HttpServletRequest request) {

        int locationId = Integer.valueOf(request.getParameter("locationId"));

        locDao.deleteLocation(locationId);

        return "redirect:displayLocationsPage";
    }

    @RequestMapping(value = "/displayEditLocationForm", method = RequestMethod.GET)
    public String displayEditLocationForm(HttpServletRequest request, Model model) {

        int locationId = Integer.valueOf(request.getParameter("locationId"));

        Location location = locDao.getLocationById(locationId);

        model.addAttribute("location", location);

        return "editLocationForm";
    }

    @RequestMapping(value = "/updateLocation", method = RequestMethod.POST)
    public String updateLocation(@Valid @ModelAttribute("location") Location location, BindingResult result) {

        if (result.hasErrors()) {
            return "editLocationForm";
        }

        locDao.updateLocation(location);

        return "redirect:displayLocationsPage";
    }

}
