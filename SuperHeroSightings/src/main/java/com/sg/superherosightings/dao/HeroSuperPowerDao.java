/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Hero;
import com.sg.superherosightings.model.SuperPower;
import java.util.List;

/**
 *
 * @author apitz_000
 */
public interface HeroSuperPowerDao {
    
    public Hero getHeroById(int heroId);
    
    public void addHero(Hero hero);
    
    public void deleteHero(int heroId);
    
    public void updateHero(Hero hero);
    
    public List<Hero> getAllHeroes();
    
    public List<Hero> getHeroesBySightingId(int SightingId);
    
    public List<Hero> getHeroesByLocationId(int LocationId);
    
    public List<Hero> getHeroesByOrganizationId(int OrgId);
    
    public SuperPower getSuperPowerById(int powerId);
    
    public void addSuperPower(SuperPower power);
    
    public void deleteSuperPower(int powerId);
    
    public void updateSuperPower(SuperPower power);
    
    public List<SuperPower> getAllSuperPowers();

    public List<SuperPower> getSuperPowersByHeroId(int heroId);

}
