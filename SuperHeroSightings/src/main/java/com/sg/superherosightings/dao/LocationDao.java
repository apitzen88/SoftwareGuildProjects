/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Location;
import java.util.List;

/**
 *
 * @author apitz_000
 */
public interface LocationDao {
    
    public Location getLocationById(int locationId);
    
    public void addLocation(Location location);
    
    public void deleteLocation(int locationId);
    
    public void updateLocation(Location location);
    
    public List<Location> getAllLocations();
    
    //public List<Location> getLocationBySightingId(int sightingId);
    
}
