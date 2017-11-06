/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Hero;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.SuperPower;
import java.time.LocalDate;
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
public class SightingDaoTest {

    HeroSuperPowerDao hspDao;
    OrganizationDao orgDao;
    LocationDao locDao;
    SightingDao dao;

    public SightingDaoTest() {
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
        hspDao = ctx.getBean("HeroSuperPowerDao", HeroSuperPowerDao.class);
        orgDao = ctx.getBean("OrganizationDao", OrganizationDao.class);
        locDao = ctx.getBean("LocationDao", LocationDao.class);
        dao = ctx.getBean("SightingDao", SightingDao.class);

        List<Organization> orgs = orgDao.getAllOrganizations();
        for (Organization currentOrg : orgs) {
            orgDao.deleteOrganization(currentOrg.getOrgId());

        }
        List<Hero> heroes = hspDao.getAllHeroes();
        for (Hero currentHero : heroes) {
            hspDao.deleteHero(currentHero.getHeroId());

        }
        List<SuperPower> powers = hspDao.getAllSuperPowers();
        for (SuperPower currentPower : powers) {
            hspDao.deleteSuperPower(currentPower.getSuperPowerId());
        }

        List<Location> locations = locDao.getAllLocations();
        for (Location currentLocation : locations) {
            locDao.deleteLocation(currentLocation.getLocationId());
        }

        List<Sighting> sightings = dao.getAllSightings();
        for (Sighting currentSighting : sightings) {
            dao.deleteSighting(currentSighting.getSightingId());
        }

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of geSightingById method, of class SightingDao.
     */
    @Test
    public void testAddSightingGeSightingByIdDeleteSighting() {

        List<SuperPower> powers = new ArrayList<>();
        List<Hero> heroes = new ArrayList<>();

        Location loc1 = new Location();
        loc1.setLocationName("Teefort");
        loc1.setDescription("A fort in a tree");
        loc1.setAddress("321 tree fort ave");
        loc1.setCity("Treetown");
        loc1.setLatitude(123.456);
        loc1.setLongitude(123.456);
        locDao.addLocation(loc1);

        SuperPower power = new SuperPower();
        power.setDescription("Climbing up trees");
        powers.add(power);
        hspDao.addSuperPower(power);

        SuperPower power2 = new SuperPower();
        power2.setDescription("Climbing down trees");
        powers.add(power);
        hspDao.addSuperPower(power);

        Hero hero = new Hero();
        hero.setHeroName("Bill the Treeclimber");
        hero.setRealName("Bill the Groundwalker");
        hero.setDescription("A great treeclimber");
        hero.setSuperPowers(powers);
        heroes.add(hero);
        hspDao.addHero(hero);

        Hero hero2 = new Hero();
        hero2.setHeroName("Bob the treefaller");
        hero2.setRealName("Bob in a wheelchair");
        hero2.setDescription("not good treeclimber");
        hero2.setSuperPowers(powers);
        heroes.add(hero2);
        hspDao.addHero(hero2);

        LocalDate date1 = LocalDate.of(2017, 10, 24);

        Sighting s1 = new Sighting();
        s1.setLocation(loc1);
        s1.setHeroes(heroes);
        s1.setDate(date1);
        dao.addSighting(s1);

        Sighting testSighting = dao.geSightingById(s1.getSightingId());
        Sighting testSighting2 = dao.geSightingById(testSighting.getSightingId());

        assertEquals(testSighting, testSighting2);

    }

    /**
     * Test of updateSighting method, of class SightingDao.
     */
    @Test
    public void testUpdateSighting() {

        List<SuperPower> powers = new ArrayList<>();
        List<Hero> heroes = new ArrayList<>();

        Location loc1 = new Location();
        loc1.setLocationName("Teefort");
        loc1.setDescription("A fort in a tree");
        loc1.setAddress("321 tree fort ave");
        loc1.setCity("Treetown");
        loc1.setLatitude(123.456);
        loc1.setLongitude(123.456);
        locDao.addLocation(loc1);

        SuperPower power = new SuperPower();
        power.setDescription("Climbing up trees");
        powers.add(power);
        hspDao.addSuperPower(power);

        SuperPower power2 = new SuperPower();
        power2.setDescription("Climbing down trees");
        powers.add(power);
        hspDao.addSuperPower(power);

        Hero hero = new Hero();
        hero.setHeroName("Bill the Treeclimber");
        hero.setRealName("Bill the Groundwalker");
        hero.setDescription("A great treeclimber");
        hero.setSuperPowers(powers);
        heroes.add(hero);
        hspDao.addHero(hero);

        Hero hero2 = new Hero();
        hero2.setHeroName("Bob the treefaller");
        hero2.setRealName("Bob in a wheelchair");
        hero2.setDescription("not good treeclimber");
        hero2.setSuperPowers(powers);
        heroes.add(hero2);
        hspDao.addHero(hero2);

        LocalDate date1 = LocalDate.of(2017, 10, 24);

        Sighting s1 = new Sighting();
        s1.setLocation(loc1);
        s1.setHeroes(heroes);
        s1.setDate(date1);
        dao.addSighting(s1);
        LocalDate date2 = LocalDate.of(2017, 10, 25);

        s1.setDate(date2);
        dao.updateSighting(s1);

        Sighting testSighting = dao.geSightingById(s1.getSightingId());

        List<Hero> testHeroes = testSighting.getHeroes();
        for (Hero h : testHeroes) {
            System.out.println(h.getHeroName());
        }

        assertEquals(s1.getDate(), testSighting.getDate());

    }

    /**
     * Test of getAllSightings method, of class SightingDao.
     */
    @Test
    public void testGetAllSightings() {
        List<SuperPower> powers = new ArrayList<>();
        List<Hero> heroes = new ArrayList<>();

        Location loc1 = new Location();
        loc1.setLocationName("Teefort");
        loc1.setDescription("A fort in a tree");
        loc1.setAddress("321 tree fort ave");
        loc1.setCity("Treetown");
        loc1.setLatitude(123.456);
        loc1.setLongitude(123.456);
        locDao.addLocation(loc1);

        SuperPower power = new SuperPower();
        power.setDescription("Climbing up trees");
        powers.add(power);
        hspDao.addSuperPower(power);

        SuperPower power2 = new SuperPower();
        power2.setDescription("Climbing down trees");
        powers.add(power);
        hspDao.addSuperPower(power);

        Hero hero = new Hero();
        hero.setHeroName("Bill the Treeclimber");
        hero.setRealName("Bill the Groundwalker");
        hero.setDescription("A great treeclimber");
        hero.setSuperPowers(powers);
        heroes.add(hero);
        hspDao.addHero(hero);

        Hero hero2 = new Hero();
        hero2.setHeroName("Bob the treefaller");
        hero2.setRealName("Bob in a wheelchair");
        hero2.setDescription("not good treeclimber");
        hero2.setSuperPowers(powers);
        heroes.add(hero2);
        hspDao.addHero(hero2);

        LocalDate date1 = LocalDate.of(2017, 10, 24);
        LocalDate date2 = LocalDate.of(2017, 10, 25);

        Sighting s1 = new Sighting();
        s1.setLocation(loc1);
        s1.setHeroes(heroes);
        s1.setDate(date1);
        dao.addSighting(s1);

        Sighting s2 = new Sighting();
        s2.setLocation(loc1);
        s2.setHeroes(heroes);
        s2.setDate(date2);
        dao.addSighting(s2);

        List<Sighting> sightings = dao.getAllSightings();

        assertEquals(2, sightings.size());
    }

    /**
     * Test of getSightingByHeroId method, of class SightingDao.
     */
    @Test
    public void testGetSightingByHeroId() {
        List<SuperPower> powers = new ArrayList<>();
        List<Hero> heroes = new ArrayList<>();

        Location loc1 = new Location();
        loc1.setLocationName("Teefort");
        loc1.setDescription("A fort in a tree");
        loc1.setAddress("321 tree fort ave");
        loc1.setCity("Treetown");
        loc1.setLatitude(123.456);
        loc1.setLongitude(123.456);
        locDao.addLocation(loc1);

        SuperPower power = new SuperPower();
        power.setDescription("Climbing up trees");
        powers.add(power);
        hspDao.addSuperPower(power);

        SuperPower power2 = new SuperPower();
        power2.setDescription("Climbing down trees");
        powers.add(power);
        hspDao.addSuperPower(power);

        Hero hero = new Hero();
        hero.setHeroName("Bill the Treeclimber");
        hero.setRealName("Bill the Groundwalker");
        hero.setDescription("A great treeclimber");
        hero.setSuperPowers(powers);
        heroes.add(hero);
        hspDao.addHero(hero);

        Hero hero2 = new Hero();
        hero2.setHeroName("Bob the treefaller");
        hero2.setRealName("Bob in a wheelchair");
        hero2.setDescription("not good treeclimber");
        hero2.setSuperPowers(powers);
        heroes.add(hero2);
        hspDao.addHero(hero2);

        LocalDate date1 = LocalDate.of(2017, 10, 24);
        LocalDate date2 = LocalDate.of(2017, 10, 25);

        Sighting s1 = new Sighting();
        s1.setLocation(loc1);
        s1.setHeroes(heroes);
        s1.setDate(date1);
        dao.addSighting(s1);

        Sighting s2 = new Sighting();
        s2.setLocation(loc1);
        s2.setHeroes(heroes);
        s2.setDate(date2);
        dao.addSighting(s2);

        List<Sighting> byHero = dao.getSightingByHeroId(hero2.getHeroId());
        for (Sighting s : byHero) {
            System.out.println(s.getSightingId());
            System.out.println(s.getDate());
        }

        assertEquals(2, byHero.size());

    }

    /**
     * Test of getSightingByLocationId method, of class SightingDao.
     */
    @Test
    public void testGetSightingByLocationId() {

        List<SuperPower> powers = new ArrayList<>();
        List<Hero> heroes = new ArrayList<>();

        Location loc1 = new Location();
        loc1.setLocationName("Teefort");
        loc1.setDescription("A fort in a tree");
        loc1.setAddress("321 tree fort ave");
        loc1.setCity("Treetown");
        loc1.setLatitude(123.456);
        loc1.setLongitude(123.456);
        locDao.addLocation(loc1);

        Location loc2 = new Location();
        loc2.setLocationName("Cave");
        loc2.setDescription("An undergound cave");
        loc2.setAddress("123 cave Street");
        loc2.setCity("Cavetown");
        loc2.setLatitude(234.567);
        loc2.setLongitude(789.456);
        locDao.addLocation(loc2);

        SuperPower power = new SuperPower();
        power.setDescription("Climbing up trees");
        powers.add(power);
        hspDao.addSuperPower(power);

        SuperPower power2 = new SuperPower();
        power2.setDescription("Climbing down trees");
        powers.add(power);
        hspDao.addSuperPower(power);

        Hero hero = new Hero();
        hero.setHeroName("Bill the Treeclimber");
        hero.setRealName("Bill the Groundwalker");
        hero.setDescription("A great treeclimber");
        hero.setSuperPowers(powers);
        heroes.add(hero);
        hspDao.addHero(hero);

        Hero hero2 = new Hero();
        hero2.setHeroName("Bob the treefaller");
        hero2.setRealName("Bob in a wheelchair");
        hero2.setDescription("not good treeclimber");
        hero2.setSuperPowers(powers);
        heroes.add(hero2);
        hspDao.addHero(hero2);

        LocalDate date1 = LocalDate.of(2017, 10, 24);
        LocalDate date2 = LocalDate.of(2017, 10, 25);

        Sighting s1 = new Sighting();
        s1.setLocation(loc1);
        s1.setHeroes(heroes);
        s1.setDate(date1);
        dao.addSighting(s1);

        Sighting s2 = new Sighting();
        s2.setLocation(loc2);
        s2.setHeroes(heroes);
        s2.setDate(date2);
        dao.addSighting(s2);

        Sighting s3 = new Sighting();
        s3.setLocation(loc2);
        s3.setHeroes(heroes);
        s3.setDate(date2);
        dao.addSighting(s3);

        Location testLoc1 = locDao.getLocationById(loc1.getLocationId());
        Location testLoc2 = locDao.getLocationById(loc2.getLocationId());

        List<Sighting> testSightings1 = dao.getSightingByLocationId(testLoc1.getLocationId());
        List<Sighting> testSightings2 = dao.getSightingByLocationId(testLoc2.getLocationId());

        assertEquals(1, testSightings1.size());
        assertEquals(2, testSightings2.size());

    }

    /**
     * Test of getSightingByDate method, of class SightingDao.
     */
    @Test
    public void testGetSightingByDate() {
        List<SuperPower> powers = new ArrayList<>();
        List<Hero> heroes = new ArrayList<>();

        Location loc1 = new Location();
        loc1.setLocationName("Teefort");
        loc1.setDescription("A fort in a tree");
        loc1.setAddress("321 tree fort ave");
        loc1.setCity("Treetown");
        loc1.setLatitude(123.456);
        loc1.setLongitude(123.456);
        locDao.addLocation(loc1);

        Location loc2 = new Location();
        loc2.setLocationName("Cave");
        loc2.setDescription("An undergound cave");
        loc2.setAddress("123 cave Street");
        loc2.setCity("Cavetown");
        loc2.setLatitude(234.567);
        loc2.setLongitude(789.456);
        locDao.addLocation(loc2);

        SuperPower power = new SuperPower();
        power.setDescription("Climbing up trees");
        powers.add(power);
        hspDao.addSuperPower(power);

        SuperPower power2 = new SuperPower();
        power2.setDescription("Climbing down trees");
        powers.add(power);
        hspDao.addSuperPower(power);

        Hero hero = new Hero();
        hero.setHeroName("Bill the Treeclimber");
        hero.setRealName("Bill the Groundwalker");
        hero.setDescription("A great treeclimber");
        hero.setSuperPowers(powers);
        heroes.add(hero);
        hspDao.addHero(hero);

        Hero hero2 = new Hero();
        hero2.setHeroName("Bob the treefaller");
        hero2.setRealName("Bob in a wheelchair");
        hero2.setDescription("not good treeclimber");
        hero2.setSuperPowers(powers);
        heroes.add(hero2);
        hspDao.addHero(hero2);

        LocalDate date1 = LocalDate.of(2017, 10, 24);
        LocalDate date2 = LocalDate.of(2017, 10, 25);

        Sighting s1 = new Sighting();
        s1.setLocation(loc1);
        s1.setHeroes(heroes);
        s1.setDate(date1);
        dao.addSighting(s1);

        Sighting s2 = new Sighting();
        s2.setLocation(loc2);
        s2.setHeroes(heroes);
        s2.setDate(date2);
        dao.addSighting(s2);

        Sighting s3 = new Sighting();
        s3.setLocation(loc2);
        s3.setHeroes(heroes);
        s3.setDate(date2);
        dao.addSighting(s3);

        List<Sighting> testSight1 = dao.getSightingByDate(date1);
        List<Sighting> testSight2 = dao.getSightingByDate(date2);

        assertEquals(1, testSight1.size());
        assertEquals(2, testSight2.size());
    }

}
