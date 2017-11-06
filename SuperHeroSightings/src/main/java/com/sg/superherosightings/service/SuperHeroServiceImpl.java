/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.dao.HeroSuperPowerDao;
import com.sg.superherosightings.dao.LocationDao;
import com.sg.superherosightings.dao.OrganizationDao;
import com.sg.superherosightings.dao.SightingDao;
import com.sg.superherosightings.model.Hero;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.SuperPower;
import java.time.LocalDate;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

/**
 *
 * @author apitz_000
 */

@Service
public class SuperHeroServiceImpl implements SuperHeroService {
    
    HeroSuperPowerDao hspDao;
    OrganizationDao orgDao;
    LocationDao locDao;
    SightingDao sightDao;
    
    @Inject
    public SuperHeroServiceImpl(HeroSuperPowerDao hspDao, OrganizationDao 
            orgDao, LocationDao locDao, SightingDao sightDao) {
        
        this.hspDao = hspDao;
        this.orgDao = orgDao;
        this.locDao = locDao;
        this.sightDao = sightDao;
        
    }
                    
    

    @Override
    public void addHero(Hero hero) {
        hspDao.addHero(hero);
    }

    @Override
    public void deleteHero(int heroId) {
        hspDao.deleteHero(heroId);
    }

    @Override
    public void updateHero(Hero hero) {
        hspDao.updateHero(hero);
    }

    @Override
    public List<Hero> getAllHeroes() {
        return hspDao.getAllHeroes();
    }

    @Override
    public List<Hero> getHeroesBySightingId(int SightingId) {
        return hspDao.getHeroesBySightingId(SightingId);
    }

    @Override
    public List<Hero> getHeroesByLocationId(int LocationId) {
        return hspDao.getHeroesByLocationId(LocationId);
    }

    @Override
    public List<Hero> getHeroesByOrganizationId(int OrgId) {
        return hspDao.getHeroesByOrganizationId(OrgId);
    }

    @Override
    public SuperPower getSuperPowerById(int powerId) {
        return hspDao.getSuperPowerById(powerId);
    }

    @Override
    public void addSuperPower(SuperPower power) {
        hspDao.addSuperPower(power);
    }

    @Override
    public void deleteSuperPower(int powerId) {
        hspDao.deleteSuperPower(powerId);
    }

    @Override
    public void updateSuperPower(SuperPower power) {
        hspDao.updateSuperPower(power);
    }

    @Override
    public List<SuperPower> getAllSuperPowers() {
        return hspDao.getAllSuperPowers();
    }

    @Override
    public List<SuperPower> getSuperPowersByHeroId(int heroId) {
        return hspDao.getSuperPowersByHeroId(heroId);
    }

    @Override
    public Location getLocationById(int locationId) {
        return locDao.getLocationById(locationId);
    }

    @Override
    public void addLocation(Location location) {
        locDao.addLocation(location);
    }

    @Override
    public void deleteLocation(int locationId) {
        locDao.deleteLocation(locationId);
    }

    @Override
    public void updateLocation(Location location) {
        locDao.updateLocation(location);
    }

    @Override
    public List<Location> getAllLocations() {
        return locDao.getAllLocations();
    }

    @Override
    public Organization getOrganizationById(int organizationId) {
        return orgDao.getOrganizationById(organizationId);
    }

    @Override
    public void addOrganization(Organization organization) {
        orgDao.addOrganization(organization);
    }

    @Override
    public void deleteOrganization(int organizationId) {
        orgDao.deleteOrganization(organizationId);
    }

    @Override
    public void updateOrganization(Organization organization) {
        orgDao.updateOrganization(organization);
    }
    

    @Override
    public List<Organization> getAllOrganizations() {
        return orgDao.getAllOrganizations();
    }

    @Override
    public List<Organization> getOrganizationByHeroId(int heroId) {
        return orgDao.getOrganizationByHeroId(heroId);
    }

    @Override
    public Sighting geSightingById(int sightingId) {
        return sightDao.geSightingById(sightingId);
    }
    

    @Override
    public void addSighting(Sighting sighting) {
        sightDao.addSighting(sighting);
    }

    @Override
    public void deleteSighting(int SightingId) {
        sightDao.deleteSighting(SightingId);
    }

    @Override
    public void updateSighting(Sighting sighting) {
        sightDao.updateSighting(sighting);
    }
    

    @Override
    public List<Sighting> getAllSightings() {
        return sightDao.getAllSightings();
    }

    @Override
    public List<Sighting> getSightingByHeroId(int heroId) {
        return sightDao.getSightingByHeroId(heroId);
    }

    @Override
    public List<Sighting> getSightingByLocationId(int locationId) {
        return sightDao.getSightingByLocationId(locationId);
    }

    @Override
    public List<Sighting> getSightingByDate(LocalDate date) {
        return sightDao.getSightingByDate(date);
    }
    
}
