/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Hero;
import com.sg.superherosightings.model.SuperPower;
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
public class HeroSuperPowerDaoDbImpl implements HeroSuperPowerDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_SELECT_ALL_HEROES
            = "select * from Hero";

    private static final String SQL_SELECT_HERO
            = "select * from Hero where HeroID = ?";

    private static final String SQL_INSERT_HERO
            = "insert into Hero (HeroName, RealName, Description) "
            + "values (?, ?, ?)";

    private static final String SQL_DELETE_HERO
            = "delete from Hero where HeroID = ?";

    private static final String SQL_UPDATE_HERO
            = "update Hero set HeroName = ?, RealName = ?, Description = ? "
            + "where HeroID = ?";

    private static final String SQL_SELECT_ALL_SUPERPOWERS
            = "select * from SuperPower";

    private static final String SQL_SELECT_SUPERPOWER
            = "select * from SuperPower where SuperPowerID = ?";

    private static final String SQL_INSERT_SUPERPOWER
            = "insert into SuperPower (Description) "
            + "values (?)";

    private static final String SQL_DELETE_SUPERPOWER
            = "delete from SuperPower where SuperPowerID = ?";

    private static final String SQL_UPDATE_SUPERPOWER
            = "update SuperPower set Description = ? "
            + "where SuperPowerID = ?";

    private static final String SQL_INSERT_HERO_SUPERPOWER
            = "insert into HeroSuperPower "
            + "(HeroID, SuperPowerID) "
            + "values (?, ?)";

    private static final String SQL_DELETE_HERO_SUPERPOWER
            = "delete from HeroSuperPower where HeroID = ?";
    
    private static final String SQL_DELETE_SUPERPOWER_HERO
            ="delete from HeroSuperPower where SuperPowerID = ?";

    private static final String SQL_INSERT_HERO_SIGHTING
            = "insert into HeroSighting "
            + "(HeroID, SightingID) "
            + "values (?, ?)";

    private static final String SQL_DELETE_HERO_SIGHTING
            = "delete from HeroSightings where HeroID = ?";

    private static final String SQL_INSERT_HERO_ORG
            = "insert into HeroOrganization "
            + "(HeroID, OrganizationID) "
            + "values (?, ?)";

    private static final String SQL_DELETE_HERO_ORG
            = "delete from HeroOrganization where HeroID = ?";

    private static final String SQL_SELECT_HEROES_BY_SIGHTING_ID
            = "select h.HeroID, h.HeroName, h.RealName, h.Description "
            + "from Hero h "
            + "join HeroSightings hs on h.HeroID = hs.HeroID "
            + "where SightingID = ?";

    private static final String SQL_SELECT_HEROES_BY_ORG_ID
            = "select h.HeroID, h.HeroName, h.RealName, h.Description "
            + "from Hero h "
            + "join HeroOrganization ho on h.HeroID = ho.HeroID "
            + "where OrganizationID = ?";

    private static final String SQL_SELECT_SUPERPOWERS_BY_HERO_ID
            = " select sp.SuperPowerID, sp.Description "
            + "from SuperPower sp "
            + "join HeroSuperPower hsp on sp.SuperPowerID = hsp.SuperPowerID "
            + "where hsp.HeroID = ?";

    //////////////////////////////////////////////////////////////////////////////////////
    
    @Override
    public Hero getHeroById(int heroId) {
        try {
            Hero hero = jdbcTemplate.queryForObject(SQL_SELECT_HERO,
                    new HeroMapper(), heroId);
            hero.setSuperPowers(findPowersForHero(hero));

            return hero;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addHero(Hero hero) {
        jdbcTemplate.update(SQL_INSERT_HERO,
                hero.getHeroName(),
                hero.getRealName(),
                hero.getDescription());
        List<SuperPower> powers = hero.getSuperPowers();
        
        hero.setHeroId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));

        if (powers != null && !powers.isEmpty()) {
            insertHeroPowers(hero);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteHero(int heroId) {
        jdbcTemplate.update(SQL_DELETE_HERO_SIGHTING, heroId);
        jdbcTemplate.update(SQL_DELETE_HERO_ORG, heroId);
        jdbcTemplate.update(SQL_DELETE_HERO_SUPERPOWER, heroId);
        jdbcTemplate.update(SQL_DELETE_HERO, heroId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateHero(Hero hero) {
        jdbcTemplate.update(SQL_UPDATE_HERO,
                hero.getHeroName(),
                hero.getRealName(),
                hero.getDescription(),
                hero.getHeroId());
        jdbcTemplate.update(SQL_DELETE_HERO_SUPERPOWER, hero.getHeroId());
        insertHeroPowers(hero);
    }

    @Override
    public List<Hero> getAllHeroes() {
        List<Hero> heroes = jdbcTemplate.query(SQL_SELECT_ALL_HEROES,
                new HeroMapper());
        return associatePowersWithHero(heroes);
    }

    @Override
    public List<Hero> getHeroesBySightingId(int SightingId) {
        List<Hero> heroes = jdbcTemplate.query(SQL_SELECT_HEROES_BY_SIGHTING_ID,
                new HeroMapper(),
                SightingId);
        return associatePowersWithHero(heroes);

    }

    @Override
    public List<Hero> getHeroesByLocationId(int LocationId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Hero> getHeroesByOrganizationId(int OrgId) {
        List<Hero> heroes = jdbcTemplate.query(SQL_SELECT_HEROES_BY_ORG_ID,
                new HeroMapper(),
                OrgId);
        return associatePowersWithHero(heroes);
    }

    @Override
    public List<SuperPower> getAllSuperPowers() {
        List<SuperPower> powers = jdbcTemplate.query(SQL_SELECT_ALL_SUPERPOWERS,
                new SuperPowerMapper());
        return powers;
    }

    @Override
    public SuperPower getSuperPowerById(int powerId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_SUPERPOWER,
                    new SuperPowerMapper(), powerId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSuperPower(SuperPower power) {
        jdbcTemplate.update(SQL_INSERT_SUPERPOWER,
                power.getDescription());
        power.setSuperPowerId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteSuperPower(int powerId) {
        jdbcTemplate.update(SQL_DELETE_SUPERPOWER_HERO, powerId);
        jdbcTemplate.update(SQL_DELETE_SUPERPOWER, powerId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateSuperPower(SuperPower power) {
        jdbcTemplate.update(SQL_UPDATE_SUPERPOWER,
                power.getDescription(),
                power.getSuperPowerId());
        jdbcTemplate.update(SQL_DELETE_HERO_SUPERPOWER, power.getSuperPowerId());
    }

    @Override
    public List<SuperPower> getSuperPowersByHeroId(int heroId) {
        List<SuperPower> powers = jdbcTemplate.query(SQL_SELECT_SUPERPOWERS_BY_HERO_ID,
                new SuperPowerMapper(),
                heroId);

        return powers;
    }

//////////////////////////////////////////////////////////////////////////////////////////////
    private void insertHeroPowers(Hero hero) {
        final int heroId = hero.getHeroId();
        final List<SuperPower> powers = hero.getSuperPowers();

        for (SuperPower currentPower : powers) {
            jdbcTemplate.update(SQL_INSERT_HERO_SUPERPOWER,
                    heroId, currentPower.getSuperPowerId());
        }
    }

    private List<SuperPower> findPowersForHero(Hero hero) {
        return jdbcTemplate.query(SQL_SELECT_SUPERPOWERS_BY_HERO_ID,
                new SuperPowerMapper(),
                hero.getHeroId());
    }

    private List<Hero> associatePowersWithHero(List<Hero> heroes) {
        for (Hero currentHero : heroes) {
            currentHero.setSuperPowers(findPowersForHero(currentHero));
        }
        return heroes;

    }

/////////////////////////////////////////////////////////////////////////////////////////////
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

    public static final class SuperPowerMapper implements RowMapper<SuperPower> {

        @Override
        public SuperPower mapRow(ResultSet rs, int i) throws SQLException {
            SuperPower sp = new SuperPower();
            sp.setSuperPowerId(rs.getInt("SuperPowerID"));
            sp.setDescription(rs.getString("Description"));

            return sp;
        }
    }

}
