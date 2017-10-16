/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;
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
public class ProductDaoTest {
    
    ProductDao productDao = new ProductDaoStubImpl();
    
    public ProductDaoTest() {
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
     * Test of getProductInfo method, of class ProductDao.
     */
    @Test
    public void testGetProductInfo() throws Exception {
        String productName = "Bearskin";
        Product product = productDao.getProductInfo(productName);
        
        assertEquals(new BigDecimal("1.50"), product.getCostPerSqFt());
        assertEquals(new BigDecimal("3.00"), product.getLaborCostPerSqFt());
    }
    
    
}
