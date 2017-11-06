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
import java.time.LocalDate;
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
public class SightingDaoDbimpl implements SightingDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_SELECT_SIGHTING
            = "select * from Sightings where SightingID = ?";

    private static final String SQL_SELECT_HEROES_BY_SIGHTING_ID
            = "select h.HeroID, h.HeroName, h.RealName, h.Description "
            + "from Hero h "
            + "join HeroSightings hs on h.HeroID = hs.HeroID "
            + "where hs.SightingID = ?";

    private static final String SQL_INSERT_SIGHTING
            = "insert into Sightings (LocationID, SightingDate) "
            + "values (?, ?)";

    private static final String SQL_DELETE_HERO_SIGHTING
            = "delete from HeroSightings where SightingID = ?";

    private static final String SQL_DELETE_SIGHTING
            = "delete from Sightings where SightingID = ?";

    private static final String SQL_SELECT_ALL_SIGHTINGS
            = "select * from Sightings";

    private static final String SQL_INSERT_HERO_SIGHTING
            = "insert into HeroSightings (HeroID, SightingID) "
            + "values(?, ?)";

    private static final String SQL_SELECT_LOCATION_BY_SIGHTING_ID
            = "select * from Location l "
            + "join Sightings s on l.LocationID = s.LocationID "
            + "where s.SightingID = ?";
    
    private static final String SQL_UPDATE_SIGHTING
            ="update Sightings set LocationID = ?, SightingDate = ? "
            + "where SightingID = ?";
    
    private static final String SQL_SELECT_SIGHITNGS_BY_HERO_ID
            ="select * from Sightings s "
            + "join HeroSightings hs on s.SightingID = hs.SightingID "
            + "where hs.HeroID = ?";
    
    private static final String SQL_SELECT_SIGHTINGS_BY_LOC_ID
            ="select * from Sightings "
            + "where LocationID = ?";
    
    private static final String SQL_SELECT_SIGHTINGS_BY_DATE
            ="select * from Sightings "
            + "where SightingDate = ?";
    
    private static final String SQL_SELECT_LAST_TEN_SIGHTINGS
            ="select * from Sightings "
            + "order by SightingDate desc limit 10";

    @Override
    public Sighting geSightingById(int sightingId) {
        try {
            Sighting sighting = jdbcTemplate.queryForObject(SQL_SELECT_SIGHTING,
                    new SightingMapper(), sightingId);
            sighting.setHeroes(findHeroesForSighting(sighting));
            sighting.setLocation(findLocationForSighting(sightingId));
            return sighting;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSighting(Sighting sighting) {
        int locationId = sighting.getLocation().getLocationId();
        LocalDate lDate = sighting.getDate();
        String sightingDate = lDate.toString();

        jdbcTemplate.update(SQL_INSERT_SIGHTING,
                locationId, sightingDate);
        sighting.setSightingId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class));
        insertHeroSightings(sighting);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteSighting(int SightingId) {
        jdbcTemplate.update(SQL_DELETE_HERO_SIGHTING, SightingId);
        jdbcTemplate.update(SQL_DELETE_SIGHTING, SightingId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateSighting(Sighting sighting) {
        LocalDate lDate = sighting.getDate();
        String sightingDate = lDate.toString();
        int locId = sighting.getLocation().getLocationId();
        jdbcTemplate.update(SQL_UPDATE_SIGHTING,
                locId,
                sightingDate,
                sighting.getSightingId());
        jdbcTemplate.update(SQL_DELETE_HERO_SIGHTING, sighting.getSightingId());
        insertHeroSightings(sighting);
        
    }

    @Override
    public List<Sighting> getAllSightings() {
        List<Sighting> sightings = jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS,
                new SightingMapper());
        return associateHeroesWithSighting(sightings);
    }

    @Override
    public List<Sighting> getSightingByHeroId(int heroId) {
        List<Sighting> sightings = jdbcTemplate.query(SQL_SELECT_SIGHITNGS_BY_HERO_ID,
                new SightingMapper(), heroId);
        return associateHeroesWithSighting(sightings);
    }

    @Override
    public List<Sighting> getSightingByLocationId(int locationId) {
        List<Sighting> sightings = jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_LOC_ID,
                new SightingMapper(), locationId);
        return associateHeroesWithSighting(sightings);
    }

    @Override
    public List<Sighting> getSightingByDate(LocalDate date) {
        String sightingDate = date.toString();
        List<Sighting> sightings = jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_DATE,
                new SightingMapper(), sightingDate);
        return associateHeroesWithSighting(sightings);
    }
    
    @Override
    public List<Sighting> getRecentSightingsByDate() {
        List<Sighting> sightings = jdbcTemplate.query(SQL_SELECT_LAST_TEN_SIGHTINGS,
                new SightingMapper());
        return associateHeroesWithSighting(sightings);
    }

    private void insertHeroSightings(Sighting sighting) {
        final int sightingId = sighting.getSightingId();
        final List<Hero> heroes = sighting.getHeroes();

        for (Hero currentHero : heroes) {
            jdbcTemplate.update(SQL_INSERT_HERO_SIGHTING,
                    currentHero.getHeroId(), sightingId);
        }
    }

    private List<Hero> findHeroesForSighting(Sighting sighting) {
        return jdbcTemplate.query(SQL_SELECT_HEROES_BY_SIGHTING_ID,
                new HeroMapper(), sighting.getSightingId());
    }

    private List<Sighting> associateHeroesWithSighting(List<Sighting> sightings) {
        for (Sighting currentSighting : sightings) {
            currentSighting.setHeroes(findHeroesForSighting(currentSighting));
            currentSighting.setLocation(findLocationForSighting(currentSighting.getSightingId()));
        }
        return sightings;

    }

    private Location findLocationForSighting(int sightingId) {
        return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_BY_SIGHTING_ID,
                new LocationMapper(), sightingId);
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

    private static final class HeroMapper implements RowMapper<Hero> {

        @Override
        public Hero mapRow(ResultSet rs, int i) throws SQLException {
            Hero h = new Hero();
            h.setHeroId(rs.getInt("HeroID"));
            h.setHeroName(rs.getString("HeroName"));
            h.setRealName(rs.getString("RealName"));
            h.setDescription(rs.getString("Description"));

            return h;
        }
    }

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
}
