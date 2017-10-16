/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import java.util.List;
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
public class OrdersDaoTest {

    OrdersDao ordersDao = new OrdersDaoStubImpl();

    public OrdersDaoTest() {
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
     * Test of listOrdersByDate method, of class OrdersDao.
     */
    @Test
    public void testListOrdersByDate() throws Exception {
        String date = "2019-09-09";
        assertEquals(1, ordersDao.listOrdersByDate(date).size());

    }

    /**
     * Test of addOrder method, of class OrdersDao.
     */
    @Test
    public void testAddOrderRemoveOrder() throws Exception {
        Order testOrder = new Order();
        testOrder.setDate("2019-09-09");
        testOrder.setOrderNumber("2");
        testOrder.setCustomerName("Vinny");
        testOrder.setState("NM");
        testOrder.setTaxRate("6.50");
        testOrder.setMaterial("Bearskin");
        testOrder.setArea("100");
        testOrder.setCostPerSqFt("1.50");
        testOrder.setLaborCost("3.00");
        testOrder.setMaterialCost("150");
        testOrder.setLaborCost("300");
        testOrder.setTotalTax("29.25");
        testOrder.setGrandTotal("479.25");

        String orderKey = testOrder.getDate() + testOrder.getOrderNumber();

        ordersDao.addOrder(testOrder);
        assertEquals(2, ordersDao.listOrdersByDate(testOrder.getDate()).size());

        ordersDao.removeOrder(orderKey);
        assertEquals(1, ordersDao.listOrdersByDate(testOrder.getDate()).size());
        assertNull(ordersDao.getOrderByKey(orderKey));
    }

    /**
     * Test of addEditedOrder method, of class OrdersDao.
     */
    @Test
    public void testAddEditedOrderGetOrderByKey() throws Exception {
        Order testOrder = new Order();
        testOrder.setDate("2019-09-09");
        testOrder.setOrderNumber("3");
        testOrder.setCustomerName("Vance");
        testOrder.setState("NM");
        testOrder.setTaxRate("6.50");
        testOrder.setMaterial("Bearskin");
        testOrder.setArea("100");
        testOrder.setCostPerSqFt("1.50");
        testOrder.setLaborCost("3.00");
        testOrder.setMaterialCost("150");
        testOrder.setLaborCost("300");
        testOrder.setTotalTax("29.25");
        testOrder.setGrandTotal("479.25");

        String orderKey = testOrder.getDate() + testOrder.getOrderNumber();

        ordersDao.addEditedOrder(orderKey, testOrder);
        assertEquals(2, ordersDao.listOrdersByDate(testOrder.getDate()).size());
        
        Order testGetOrder = ordersDao.getOrderByKey(orderKey);
        assertEquals("Vance", testGetOrder.getCustomerName());
        
        ordersDao.removeOrder(orderKey);
        assertNull(ordersDao.getOrderByKey(orderKey));
        
    }


}
