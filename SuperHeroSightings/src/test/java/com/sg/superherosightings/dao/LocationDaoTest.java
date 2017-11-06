/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Location;
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
public class LocationDaoTest {

    LocationDao locDao;

    public LocationDaoTest() {
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

        locDao = ctx.getBean("LocationDao", LocationDao.class);

        List<Location> locations = locDao.getAllLocations();
        for (Location currentLocation : locations) {
            locDao.deleteLocation(currentLocation.getLocationId());
        }

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getLocationById method, of class LocationDao.
     */
    @Test
    public void testAddLocationGetLocationByIdDeleteLocation() {
        System.out.println("-------------------");
        System.out.println("testGetLocationById");
        System.out.println("-------------------");

        Location loc1 = new Location();
        loc1.setLocationName("Mommas House");
        loc1.setDescription("Like it says");
        loc1.setAddress("123 nunya Ave");
        loc1.setCity("Hometown");
        loc1.setLatitude(123.456);
        loc1.setLongitude(123.456);

        locDao.addLocation(loc1);

        Location testLoc = locDao.getLocationById(loc1.getLocationId());

        assertEquals(loc1, testLoc);

        locDao.deleteLocation(loc1.getLocationId());

        assertNull(locDao.getLocationById(loc1.getLocationId()));

    }

    /**
     * Test of updateLocation method, of class LocationDao.
     */
    @Test
    public void testUpdateLocation() {

        Location loc1 = new Location();
        loc1.setLocationName("Mommas House");
        loc1.setDescription("Like it says");
        loc1.setAddress("123 nunya Ave");
        loc1.setCity("Hometown");
        loc1.setLatitude(123.456);
        loc1.setLongitude(123.456);

        locDao.addLocation(loc1);

        loc1.setLocationName("Poppas House");

        locDao.updateLocation(loc1);

        Location testLoc = locDao.getLocationById(loc1.getLocationId());

        assertEquals(loc1, testLoc);

    }

    /**
     * Test of getAllLocations method, of class LocationDao.
     */
    @Test
    public void testGetAllLocations() {

        Location loc1 = new Location();
        loc1.setLocationName("The Basement");
        loc1.setDescription("Underground");
        loc1.setAddress("123 nunya Ave");
        loc1.setCity("Cityville USA");
        loc1.setLatitude(123.456);
        loc1.setLongitude(123.456);
        locDao.addLocation(loc1);

        Location loc2 = new Location();
        loc2.setLocationName("The Attic");
        loc2.setDescription("Above ground");
        loc2.setAddress("123 nunya Ave");
        loc2.setCity("HamletTown USA");
        loc2.setLatitude(654.321);
        loc2.setLongitude(654.321);
        locDao.addLocation(loc2);
        
        List<Location> locations = locDao.getAllLocations();
        
        assertEquals(2, locations.size());
    }
}
