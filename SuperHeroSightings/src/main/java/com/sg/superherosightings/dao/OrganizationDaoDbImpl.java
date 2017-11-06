/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Hero;
import com.sg.superherosightings.model.Organization;
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
public class OrganizationDaoDbImpl implements OrganizationDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_SELECT_ORG
            = "select * from Organization where OrganizationID = ?";

    private static final String SQL_INSERT_ORG
            = "insert into Organization (OrganizationName, Description, "
            + "Contact, BaseLocation) "
            + "values(?, ?, ?, ?)";

    private static final String SQL_DELETE_ORG
            = "delete from Organization where OrganizationID = ?";

    private static final String SQL_DELETE_ORG_HERO
            = "delete from HeroOrganization where OrganizationID = ?";

    private static final String SQL_UPDATE_ORG
            = "update Organization set OrganizationName = ?, "
            + "Description = ?, Contact = ?, BaseLocation = ? "
            + "where OrganizationID = ?";

    private static final String SQL_SELECT_ALL_ORGS
            = "select * from Organization";

    private static final String SQL_SELECT_ORG_BY_HERO_ID
            = "select o.OrganizationID, o.OrganizationName, o.Description, o.Contact, o. BaseLocation "
            + "from Organization o "
            + "join HeroOrganization ho on o.OrganizationID = ho.OrganizationID "
            + "where HeroID = ?";

    private static final String SQL_INSERT_HERO_ORG
            = "insert into HeroOrganization "
            + "(HeroID, OrganizationID)  "
            + "values(?, ?)";

    private static final String SQL_SELECT_HEROES_BY_ORG_ID
            = "select h.HeroID, h. HeroName, h.RealName, h.Description "
            + "from Hero h "
            + "join HeroOrganization ho on h.HeroID = ho.HeroID "
            + "where OrganizationID = ?";
////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public Organization getOrganizationById(int organizationId) {
        try {
            Organization org = jdbcTemplate.queryForObject(SQL_SELECT_ORG,
                    new OrgMapper(), organizationId);
            org.setHeroes(findHeroesForOrg(org));

            return org;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addOrganization(Organization organization) {
        jdbcTemplate.update(SQL_INSERT_ORG,
                organization.getOrgName(),
                organization.getDescription(),
                organization.getContact(),
                organization.getBaseLocation());
                List<Hero> heroes = organization.getHeroes();

        organization.setOrgId(jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class));
        
        if (heroes != null && !heroes.isEmpty()) {
            insertHeroOrg(organization);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteOrganization(int organizationId) {
        jdbcTemplate.update(SQL_DELETE_ORG_HERO, organizationId);
        jdbcTemplate.update(SQL_DELETE_ORG, organizationId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateOrganization(Organization organization) {
        jdbcTemplate.update(SQL_UPDATE_ORG,
                organization.getOrgName(),
                organization.getDescription(),
                organization.getContact(),
                organization.getBaseLocation(),
                organization.getOrgId());
        jdbcTemplate.update(SQL_DELETE_ORG_HERO, organization.getOrgId());
        
        if(organization.getHeroes() !=null){
        insertHeroOrg(organization);
        }
    }

    @Override
    public List<Organization> getAllOrganizations() {
        List<Organization> orgs = jdbcTemplate.query(SQL_SELECT_ALL_ORGS,
                new OrgMapper());
        return associateHeroesWithOrg(orgs);
    }

    @Override
    public List<Organization> getOrganizationByHeroId(int heroId) {
        List<Organization> orgs = jdbcTemplate.query(SQL_SELECT_ORG_BY_HERO_ID,
                new OrgMapper(), heroId);
        return associateHeroesWithOrg(orgs);
    }
//////////////////////////////////////////////////////////////////////////////////////

    private void insertHeroOrg(Organization org) {
        final int orgId = org.getOrgId();
        final List<Hero> heroes = org.getHeroes();
        
            for (Hero currentHero : heroes) {
                jdbcTemplate.update(SQL_INSERT_HERO_ORG,
                        currentHero.getHeroId(), orgId);
            }
    }

    private List<Hero> findHeroesForOrg(Organization org) {
        return jdbcTemplate.query(SQL_SELECT_HEROES_BY_ORG_ID,
                new HeroMapper(), org.getOrgId());

    }

    private List<Organization> associateHeroesWithOrg(List<Organization> orgs) {
        for (Organization currentOrg : orgs) {
            currentOrg.setHeroes(findHeroesForOrg(currentOrg));
        }
        return orgs;
    }
///////////////////////////////////////////////////////////////////////////////////////

    private static final class OrgMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {
            Organization o = new Organization();
            o.setOrgId(rs.getInt("OrganizationID"));
            o.setOrgName(rs.getString("OrganizationName"));
            o.setDescription(rs.getString("Description"));
            o.setContact(rs.getString("Contact"));
            o.setBaseLocation(rs.getString("BaseLocation"));

            return o;
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
