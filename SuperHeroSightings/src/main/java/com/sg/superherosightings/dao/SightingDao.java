/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Sighting;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author apitz_000
 */
public interface SightingDao {

    public Sighting geSightingById(int sightingId);

    public void addSighting(Sighting sighting);

    public void deleteSighting(int SightingId);

    public void updateSighting(Sighting sighting);

    public List<Sighting> getAllSightings();
    
    public List<Sighting> getSightingByHeroId(int heroId);
    
    public List<Sighting> getSightingByLocationId(int locationId);
    
    public List<Sighting> getSightingByDate(LocalDate date);
    
    public List<Sighting> getRecentSightingsByDate();
    

}
