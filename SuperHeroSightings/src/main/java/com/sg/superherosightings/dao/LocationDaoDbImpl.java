/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Hero;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Sighting;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apitz_000
 */
public class LocationDaoDbImpl implements LocationDao {

    HeroSuperPowerDao hspDao;

    public LocationDaoDbImpl(HeroSuperPowerDao hspDao) {
        this.hspDao = hspDao;
    }

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_SELECT_LOCATION
            = "select * from Location where LocationID = ?";

    private static final String SQL_INSERT_LOCATION
            = "insert into Location (LocationName, Description, "
            + "Address, City, Latitude, Longitude) "
            + "values(?, ?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_LOCATION
            = "delete from Location where LocationID = ?";

    private static final String SQL_UPDATE_LOCATION
            = "update Location set LocationName = ?, Description = ?, "
            + "Address = ?, City = ?, Latitude = ?, Longitude = ? "
            + "where LocationID = ?";

    private static final String SQL_SELECT_ALL_LOCATIONS
            = "select * from Location";

    private static final String SQL_SELECT_SIGHTING_ID_BY_LOCATION_ID
            = "select * from Sightings where LocationID = ?";

    private static final String SQL_DELETE_LOCATION_SIGHTING
            = "delete from Sightings where LocationID = ?";

    private static final String SQL_DELETE_HERO_SIGHTING
            = "delete from HeroSightings where SightingID = ?";

    @Override
    public Location getLocationById(int locationId) {
        try {
            Location location = jdbcTemplate.queryForObject(SQL_SELECT_LOCATION,
                    new LocationMapper(), locationId);
            return location;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addLocation(Location location) {
        jdbcTemplate.update(SQL_INSERT_LOCATION,
                location.getLocationName(),
                location.getDescription(),
                location.getAddress(),
                location.getCity(),
                location.getLatitude(),
                location.getLongitude());
        location.setLocationId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteLocation(int locationId) {
        List<Sighting> s = jdbcTemplate.query(SQL_SELECT_SIGHTING_ID_BY_LOCATION_ID,
                new SightingMapper(), locationId);
        
        for (Sighting currentSighting : s) {
            jdbcTemplate.update(SQL_DELETE_HERO_SIGHTING, currentSighting.getSightingId());
            jdbcTemplate.update(SQL_DELETE_LOCATION_SIGHTING, locationId);
        }
        
        jdbcTemplate.update(SQL_DELETE_LOCATION, locationId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateLocation(Location location) {
        jdbcTemplate.update(SQL_UPDATE_LOCATION,
                location.getLocationName(),
                location.getDescription(),
                location.getAddress(),
                location.getCity(),
                location.getLatitude(),
                location.getLongitude(),
                location.getLocationId());

    }

    @Override
    public List<Location> getAllLocations() {
        List<Location> locations
                = jdbcTemplate.query(SQL_SELECT_ALL_LOCATIONS,
                        new LocationMapper());
        return locations;
    }

//    @Override
//    public List<Location> getLocationBySightingId(int sightingId) {
//        
//    }
    
    private static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Location l = new Location();
            l.setLocationId(rs.getInt("LocationID"));
            l.setLocationName(rs.getString("LocationName"));
            l.setDescription(rs.getString("Description"));
            l.setAddress(rs.getString("Address"));
            l.setCity(rs.getString("City"));
            l.setLatitude(rs.getDouble("Latitude"));
            l.setLongitude(rs.getDouble("Longitude"));

            return l;
        }
    }

    private static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Sighting s = new Sighting();
            s.setDate(rs.getDate("SightingDate").toLocalDate());
            s.setSightingId(rs.getInt("SightingID"));
            return s;
        }
    }
}
