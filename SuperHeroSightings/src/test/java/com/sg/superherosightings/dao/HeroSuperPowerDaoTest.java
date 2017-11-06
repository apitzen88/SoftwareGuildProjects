/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Hero;
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
public class HeroSuperPowerDaoTest {

    HeroSuperPowerDao dao;

    public HeroSuperPowerDaoTest() {
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

        dao = ctx.getBean("HeroSuperPowerDao", HeroSuperPowerDao.class);

        List<Hero> heroes = dao.getAllHeroes();
        for (Hero currentHero : heroes) {
            dao.deleteHero(currentHero.getHeroId());

        }
        List<SuperPower> powers = dao.getAllSuperPowers();
        for (SuperPower currentPower : powers) {
            dao.deleteSuperPower(currentPower.getSuperPowerId());
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addHero, getHeroById, deleteHero method, of class
     * HeroSuperPowerDao.
     */
    @Test
    public void testAddHeroGetHeroByIdDeleteHero() {
        //setUp();
        System.out.println("--------------------------------");
        System.out.println("testAddHeroGetHeroByIdDeleteHero");
        System.out.println("--------------------------------");

        List<SuperPower> powers = new ArrayList<>();

        SuperPower power = new SuperPower();
        power.setSuperPowerId(1);
        power.setDescription("Speed");
        powers.add(power);
        dao.addSuperPower(power);

        Hero hero = new Hero();
        hero.setHeroName("Bill the Great");
        hero.setRealName("Bill the Regular");
        hero.setDescription("A great Bill");
        hero.setSuperPowers(powers);
        dao.addHero(hero);
        System.out.println("add: " + hero.getHeroName());

        List<SuperPower> heroPowers = hero.getSuperPowers();
        for (SuperPower currentPower : heroPowers) {
            System.out.println(currentPower.getDescription());
        }

        Hero testHero = dao.getHeroById(hero.getHeroId());
        
        System.out.println("get: " + testHero.getHeroName());

        List<SuperPower> testPowers = testHero.getSuperPowers();
        for (SuperPower currentPower : testPowers) {
            System.out.println(currentPower.getDescription());
        }

        assertEquals(testHero, hero);

        dao.deleteHero(hero.getHeroId());

        assertNull(dao.getHeroById(hero.getHeroId()));

        System.out.println("delete: " + dao.getHeroById(hero.getHeroId()));

        System.out.println("--------------------------------");

    }

    /**
     * Test of updateHero method, of class HeroSuperPowerDao.
     */
    @Test
    public void testUpdateHero() {
        System.out.println("--------------");
        System.out.println("testUpdateHero");
        System.out.println("--------------");

        List<SuperPower> powers = new ArrayList<>();

        SuperPower power = new SuperPower();
        power.setSuperPowerId(1);
        power.setDescription("Speed");
        powers.add(power);
        dao.addSuperPower(power);

        Hero hero = new Hero();
        hero.setHeroName("Bill the Great");
        hero.setRealName("Bill the Regular");
        hero.setDescription("A great Bill");
        hero.setSuperPowers(powers);

        dao.addHero(hero);
        System.out.println("original: " + hero.getDescription());

        hero.setDescription("A fantastic Bill");

        dao.updateHero(hero);

        Hero testHero = dao.getHeroById(hero.getHeroId());
        System.out.println("update: " + testHero.getDescription());

        assertEquals(hero, testHero);

        System.out.println("--------------");

    }

    /**
     * Test of getAllHeroes method, of class HeroSuperPowerDao.
     */
    @Test
    public void testGetAllHeroes() {
        System.out.println("----------------");
        System.out.println("testGetAllHeroes");
        System.out.println("----------------");

        List<SuperPower> powers = new ArrayList<>();

        SuperPower power = new SuperPower();
        power.setSuperPowerId(1);
        power.setDescription("Speed");
        powers.add(power);
        dao.addSuperPower(power);

        Hero hero1 = new Hero();
        hero1.setHeroName("Bill the Great");
        hero1.setRealName("Bill the Regular");
        hero1.setDescription("A great Bill");
        hero1.setSuperPowers(powers);

        dao.addHero(hero1);

        Hero hero2 = new Hero();
        hero2.setHeroName("Bob the Good");
        hero2.setRealName("Bob the Regular");
        hero2.setDescription("A good Bob");
        hero2.setSuperPowers(powers);

        dao.addHero(hero2);

        List<Hero> testHeroes = dao.getAllHeroes();
        for (Hero currentHero : testHeroes) {
            System.out.println(currentHero.getHeroName());
        }

        assertEquals(2, dao.getAllHeroes().size());

        System.out.println("----------------");

    }

    /**
     * Test of getHeroesBySightingId method, of class HeroSuperPowerDao.
     */
    @Test
    public void testGetHeroesBySightingId() {

    }

    /**
     * Test of getHeroesByLocationId method, of class HeroSuperPowerDao.
     */
    @Test
    public void testGetHeroesByLocationId() {
    }

    /**
     * Test of getHeroesByOrganizationId method, of class HeroSuperPowerDao.
     */
    @Test
    public void testGetHeroesByOrganizationId() {
    }

    /**
     * Test of getSuperPowerById method, addSuperPower, deleteSuperPower, of
     * class HeroSuperPowerDao.
     */
    @Test
    public void testAddSuperPowerGetSuperPowerByIdDeleteSuperPower() {
        System.out.println("--------------------------------------------------");
        System.out.println("testAddSuperPowerGetSuperPowerByIdDeleteSuperPower");
        System.out.println("--------------------------------------------------");

        SuperPower power = new SuperPower();
        power.setSuperPowerId(1);
        power.setDescription("Speed");
        dao.addSuperPower(power);
        System.out.println("add: " + power.getDescription());

        SuperPower testPower = dao.getSuperPowerById(power.getSuperPowerId());
        System.out.println("get: " + testPower.getDescription());

        assertEquals(testPower, power);

        dao.deleteSuperPower(testPower.getSuperPowerId());
        System.out.println("delete: " + dao.getSuperPowerById(testPower.getSuperPowerId()));

        assertNull(dao.getSuperPowerById(testPower.getSuperPowerId()));

        System.out.println("--------------------------------------------------");

    }

    /**
     * Test of updateSuperPower method, of class HeroSuperPowerDao.
     */
    @Test
    public void testUpdateSuperPower() {
        System.out.println("--------------------");
        System.out.println("testUpdateSuperPower");
        System.out.println("--------------------");

        SuperPower power = new SuperPower();
        power.setSuperPowerId(1);
        power.setDescription("Flinging Boogers");
        dao.addSuperPower(power);

        System.out.println("original: " + power.getDescription());

        power.setDescription("Running Backwards");
        dao.updateSuperPower(power);

        SuperPower testPower = dao.getSuperPowerById(power.getSuperPowerId());

        System.out.println("update: " + testPower.getDescription());

        assertEquals(power, testPower);

        System.out.println("--------------------");

    }

    /**
     * Test of getAllSuperPowers method, of class HeroSuperPowerDao.
     */
    @Test
    public void testGetAllSuperPowers() {
        System.out.println("---------------------");
        System.out.println("testGetAllSuperPowers");
        System.out.println("---------------------");

        SuperPower power1 = new SuperPower();
        power1.setSuperPowerId(1);
        power1.setDescription("Flinging Boogers");
        dao.addSuperPower(power1);

        SuperPower power2 = new SuperPower();
        power2.setSuperPowerId(2);
        power2.setDescription("Running Backwards");
        dao.addSuperPower(power2);

        List<SuperPower> powers = dao.getAllSuperPowers();
        for (SuperPower currentPower : powers) {
            System.out.println(currentPower.getDescription());
        }

        assertEquals(2, dao.getAllSuperPowers().size());

        System.out.println("--------------------");

    }

    /**
     * Test of getSuperPowersByHeroId method, of class HeroSuperPowerDao.
     */
    @Test
    public void testGetSuperPowersByHeroId() {
        System.out.println("--------------------------");
        System.out.println("testGetSuperPowersByHeroId");
        System.out.println("--------------------------");

        List<SuperPower> powers = new ArrayList<>();

        SuperPower power1 = new SuperPower();
        power1.setSuperPowerId(1);
        power1.setDescription("Crossing Eyes");
        powers.add(power1);
        dao.addSuperPower(power1);
        

        SuperPower power2 = new SuperPower();
        power2.setSuperPowerId(2);
        power2.setDescription("Rolling Tongue");
        powers.add(power2);
        dao.addSuperPower(power2);
        

        Hero hero = new Hero();
        hero.setHeroName("Cool Joe");
        hero.setRealName("Regular Joe");
        hero.setDescription("Pretty cool guy");
        hero.setSuperPowers(powers);
        dao.addHero(hero);

        Hero testHero = dao.getHeroById(hero.getHeroId());
        System.out.println(testHero.getHeroId());
        System.out.println(testHero.getHeroName());

        List<SuperPower> testPowers = dao.getSuperPowersByHeroId(testHero.getHeroId());
        for(SuperPower currentPower : testPowers) {
            System.out.println(currentPower.getDescription());
        }

        assertEquals(2, testPowers.size());
        System.out.println("--------------------------");

    }

}
