/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.model.Hero;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.SuperPower;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author apitz_000
 */
public interface SuperHeroService {

    public void addHero(Hero hero);

    public void deleteHero(int heroId);

    public void updateHero(Hero hero);

    public List<Hero> getAllHeroes();

    public List<Hero> getHeroesBySightingId(int SightingId);

    public List<Hero> getHeroesByLocationId(int LocationId);

    public List<Hero> getHeroesByOrganizationId(int OrgId);

    public SuperPower getSuperPowerById(int powerId);
    
/////////////////////////////////////////////////////////////////////////////////

    public void addSuperPower(SuperPower power);

    public void deleteSuperPower(int powerId);

    public void updateSuperPower(SuperPower power);

    public List<SuperPower> getAllSuperPowers();

    public List<SuperPower> getSuperPowersByHeroId(int heroId);
    
/////////////////////////////////////////////////////////////////////////////////

    public Location getLocationById(int locationId);

    public void addLocation(Location location);

    public void deleteLocation(int locationId);

    public void updateLocation(Location location);

    public List<Location> getAllLocations();
    
/////////////////////////////////////////////////////////////////////////////////

    public Organization getOrganizationById(int organizationId);

    public void addOrganization(Organization organization);

    public void deleteOrganization(int organizationId);

    public void updateOrganization(Organization organization);

    public List<Organization> getAllOrganizations();

    public List<Organization> getOrganizationByHeroId(int heroId);
    
//////////////////////////////////////////////////////////////////////////////////

    public Sighting geSightingById(int sightingId);

    public void addSighting(Sighting sighting);

    public void deleteSighting(int SightingId);

    public void updateSighting(Sighting sighting);

    public List<Sighting> getAllSightings();

    public List<Sighting> getSightingByHeroId(int heroId);

    public List<Sighting> getSightingByLocationId(int locationId);

    public List<Sighting> getSightingByDate(LocalDate date);

}
