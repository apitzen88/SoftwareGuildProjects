/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apitz_000
 */
public class TaxDaoTest {
    
    TaxDao taxDao = new TaxDaoStubImpl();
    
    public TaxDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getStateTax method, of class TaxDao.
     */
    @Test
    public void testGetStateTax() throws Exception {
        
        String stateName="NM";
        Tax testTax = taxDao.getStateTax(stateName);
        assertEquals(new BigDecimal("6.50"), testTax.getTaxRate());
        
    }
    
}
