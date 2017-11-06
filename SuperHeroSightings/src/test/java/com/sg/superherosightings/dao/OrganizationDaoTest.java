/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Hero;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.SuperPower;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apitz_000
 */
public class OrganizationDaoTest {

    OrganizationDao dao;

    HeroSuperPowerDao hspDao;

    public OrganizationDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        dao = ctx.getBean("OrganizationDao", OrganizationDao.class);
        hspDao = ctx.getBean("HeroSuperPowerDao", HeroSuperPowerDao.class);

        List<Organization> orgs = dao.getAllOrganizations();
        for (Organization currentOrg : orgs) {
            dao.deleteOrganization(currentOrg.getOrgId());

        }
        List<Hero> heroes = hspDao.getAllHeroes();
        for (Hero currentHero : heroes) {
            hspDao.deleteHero(currentHero.getHeroId());

        }
        List<SuperPower> powers = hspDao.getAllSuperPowers();
        for (SuperPower currentPower : powers) {
            hspDao.deleteSuperPower(currentPower.getSuperPowerId());
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getOrganizationById method, of class OrganizationDao.
     */
    @Test
    public void testGetOrganizationById() {

        List<SuperPower> powers = new ArrayList<>();
        List<Hero> heroes = new ArrayList<>();

        SuperPower power = new SuperPower();
        power.setDescription("Speed");
        hspDao.addSuperPower(power);
        powers.add(power);

        Hero hero = new Hero();
        hero.setHeroName("Bill the Great");
        hero.setRealName("Bill the Regular");
        hero.setDescription("A great Bill");
        hero.setSuperPowers(powers);
        hspDao.addHero(hero);
        heroes.add(hero);

        Organization org = new Organization();
        org.setOrgName("Happy Friends");
        org.setDescription("Some Happy Friends");
        org.setBaseLocation("in a boat");
        org.setContact("smoke signals");
        org.setHeroes(heroes);
        dao.addOrganization(org);

        System.out.println(org);

        Organization testOrg = dao.getOrganizationById(org.getOrgId());

        System.out.println(testOrg);

        assertEquals(testOrg.getOrgId(), org.getOrgId());

        dao.deleteOrganization(org.getOrgId());

        assertNull(dao.getOrganizationById(testOrg.getOrgId()));

    }

    /**
     * Test of updateOrganization method, of class OrganizationDao.
     */
    @Test
    public void testUpdateOrganization() {
        List<SuperPower> powers = new ArrayList<>();
        List<Hero> heroes = new ArrayList<>();

        SuperPower power = new SuperPower();
        power.setDescription("Speed");
        hspDao.addSuperPower(power);
        powers.add(power);

        Hero hero = new Hero();
        hero.setHeroName("Bill the Great");
        hero.setRealName("Bill the Regular");
        hero.setDescription("A great Bill");
        hero.setSuperPowers(powers);
        hspDao.addHero(hero);
        heroes.add(hero);

        Organization org = new Organization();
        org.setOrgName("Happy Friends");
        org.setDescription("Some Happy Friends");
        org.setBaseLocation("in a boat");
        org.setContact("smoke signals");
        org.setHeroes(heroes);
        dao.addOrganization(org);

        org.setBaseLocation("on a ship");
        dao.updateOrganization(org);

        Organization testOrg = dao.getOrganizationById(org.getOrgId());

        assertEquals(org.getBaseLocation(), testOrg.getBaseLocation());

    }

    /**
     * Test of getAllOrganizations method, of class OrganizationDao.
     */
    @Test
    public void testGetAllOrganizations() {
        List<SuperPower> powers = new ArrayList<>();
        List<Hero> heroes = new ArrayList<>();

        SuperPower power = new SuperPower();
        power.setDescription("Speed");
        hspDao.addSuperPower(power);
        powers.add(power);

        Hero hero = new Hero();
        hero.setHeroName("Bill the Great");
        hero.setRealName("Bill the Regular");
        hero.setDescription("A great Bill");
        hero.setSuperPowers(powers);
        hspDao.addHero(hero);
        heroes.add(hero);

        Organization org = new Organization();
        org.setOrgName("Happy Friends");
        org.setDescription("Some Happy Friends");
        org.setBaseLocation("in a boat");
        org.setContact("smoke signals");
        org.setHeroes(heroes);
        dao.addOrganization(org);

        Organization org2 = new Organization();
        org2.setOrgName("Unhappy Friends");
        org2.setDescription("Some Unhappy Friends");
        org2.setBaseLocation("on a plane");
        org2.setContact("morse code");
        org2.setHeroes(heroes);
        dao.addOrganization(org2);

        assertEquals(2, dao.getAllOrganizations().size());

    }

    /**
     * Test of getOrganizationByHeroId method, of class OrganizationDao.
     */
    @Test
    public void testGetOrganizationByHeroId() {

        List<SuperPower> powers = new ArrayList<>();
        List<Hero> heroes = new ArrayList<>();

        SuperPower power = new SuperPower();
        power.setDescription("Speed");
        hspDao.addSuperPower(power);
        powers.add(power);

        Hero hero = new Hero();
        hero.setHeroName("Bill the Great");
        hero.setRealName("Bill the Regular");
        hero.setDescription("A great Bill");
        hero.setSuperPowers(powers);
        hspDao.addHero(hero);
        heroes.add(hero);

        Organization org = new Organization();
        org.setOrgName("Happy Friends");
        org.setDescription("Some Happy Friends");
        org.setBaseLocation("in a boat");
        org.setContact("smoke signals");
        org.setHeroes(heroes);
        dao.addOrganization(org);

        Organization org2 = new Organization();
        org2.setOrgName("Unhappy Friends");
        org2.setDescription("Some Unhappy Friends");
        org2.setBaseLocation("on a plane");
        org2.setContact("morse code");
        org2.setHeroes(heroes);
        dao.addOrganization(org2);
        
        List<Organization> orgs = dao.getOrganizationByHeroId(hero.getHeroId());
        for(Organization currentOrg : orgs){
            System.out.println(currentOrg.getOrgId());
            System.out.println(currentOrg.getOrgName());
            System.out.println(currentOrg.getDescription());
            System.out.println(currentOrg.getBaseLocation());
            List<Hero> h =currentOrg.getHeroes();
            for(Hero currentH : h){
                System.out.println(currentH.getHeroName());
                List<SuperPower> sp = currentH.getSuperPowers();

            }
            System.out.println(currentOrg.getContact());
            
        }
        
        assertEquals(2, dao.getOrganizationByHeroId(hero.getHeroId()).size());

    }
}
