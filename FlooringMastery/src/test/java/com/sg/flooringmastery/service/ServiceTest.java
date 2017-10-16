/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.DaoException;
import com.sg.flooringmastery.dao.OrdersDao;
import com.sg.flooringmastery.dao.OrdersDaoStubImpl;
import com.sg.flooringmastery.dao.PersistenceException;
import com.sg.flooringmastery.dao.ProductDao;
import com.sg.flooringmastery.dao.ProductDaoStubImpl;
import com.sg.flooringmastery.dao.TaxDao;
import com.sg.flooringmastery.dao.TaxDaoStubImpl;
import com.sg.flooringmastery.dto.Order;
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
public class ServiceTest {

    private Service service;

    public ServiceTest() {
//        OrdersDao ordersDao = new OrdersDaoStubImpl();
//        ProductDao productDao = new ProductDaoStubImpl();
//        TaxDao taxDao = new TaxDaoStubImpl();
//
//        service = new ServiceImpl(ordersDao, productDao, taxDao);

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("service", Service.class);
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
     * Test of listOrdersByDate method, of class Service.
     */
    @Test
    public void testListOrdersByDate() throws Exception {
        assertEquals(1, service.listOrdersByDate("2019-09-09").size());

        assertEquals(0, service.listOrdersByDate("2022-02-02").size());
    }

    /**
     * Test of getOrderByOrderKey method, of class Service.
     */
    @Test
    public void testGetOrderByOrderKey() {

        String orderKeyPass = "2019-09-091";
        String orderKeyFail = "2019-09-0999";
        assertNotNull(service.getOrderByOrderKey(orderKeyPass));
        assertNull(service.getOrderByOrderKey(orderKeyFail));

    }

    /**
     * Test of createOrder method, of class Service.
     */
    @Test
    public void testCreateOrder() throws Exception {
        Order testOrder = new Order();
        testOrder.setDate("2019-09-09");
        testOrder.setCustomerName("Gary");
        testOrder.setState("NM");
        testOrder.setMaterial("Bearskin");
        testOrder.setArea("100");

        service.createOrder(testOrder);

        Order testOrderFail = new Order();
        testOrderFail.setDate("2019-09-09");
        testOrderFail.setCustomerName("Gary Gary");
        testOrderFail.setState("NM");
        testOrderFail.setMaterial("");
        testOrderFail.setArea("");

        try {
            service.createOrder(testOrderFail);
            fail("OrderValidationException was expected but not thrown");
        } catch (OrderValidationException e) {
            return;
        }
    }

    /**
     * Test of addNewOrder method, of class Service.
     */
    @Test
    public void testAddNewOrderRemoveOrder() throws Exception {
        Order onlyOrder = new Order();
        onlyOrder.setDate("2019-09-09");
        onlyOrder.setOrderNumber("99");
        onlyOrder.setCustomerName("Bobby Roberts");
        onlyOrder.setState("NM");
        onlyOrder.setTaxRate("6.50");
        onlyOrder.setMaterial("Bearskin");
        onlyOrder.setArea("100");
        onlyOrder.setCostPerSqFt("1.50");
        onlyOrder.setLaborCost("3.00");
        onlyOrder.setMaterialCost("150");
        onlyOrder.setLaborCost("300");
        onlyOrder.setTotalTax("29.25");
        onlyOrder.setGrandTotal("479.25");

        String orderKey = onlyOrder.getDate() + onlyOrder.getOrderNumber();

        service.addNewOrder(onlyOrder);

        assertEquals(2, service.listOrdersByDate(onlyOrder.getDate()).size());

        service.removeOrder(orderKey);

        assertEquals(1, service.listOrdersByDate(onlyOrder.getDate()).size());

    }

    /**
     * Test of replaceExistingOrder method, of class Service.
     */
    @Test
    public void testReplaceExistingOrder() {

        Order replaceOrder = new Order();
        replaceOrder.setDate("2019-09-09");
        replaceOrder.setOrderNumber("999");
        replaceOrder.setCustomerName("Bobert Robbins");
        replaceOrder.setState("NM");
        replaceOrder.setTaxRate("6.50");
        replaceOrder.setMaterial("Bearskin");
        replaceOrder.setArea("100");
        replaceOrder.setCostPerSqFt("1.50");
        replaceOrder.setLaborCost("3.00");
        replaceOrder.setMaterialCost("150");
        replaceOrder.setLaborCost("300");
        replaceOrder.setTotalTax("29.25");
        replaceOrder.setGrandTotal("479.25");

        String orderKey = replaceOrder.getDate() + replaceOrder.getOrderNumber();

        service.replaceExistingOrder(orderKey, replaceOrder);

        Order checkOrder = service.getOrderByOrderKey(orderKey);

        assertEquals("Bobert Robbins", checkOrder.getCustomerName());
    }

    /**
     * Test of editOrder method, of class Service.
     */
    @Test
    public void testEditOrder() throws Exception {
        Order testEdit = new Order();
        testEdit.setCustomerName("");
        testEdit.setState("");
        testEdit.setArea("");
        testEdit.setMaterial("");

        String failOrderKey = "2029-02-02999";
        try {
            service.editOrder(failOrderKey, testEdit);
        } catch (PersistenceException e) {
            return;
        }

        String passOrderKey = "2019-09-091";
        service.editOrder(passOrderKey, testEdit);

    }

}
