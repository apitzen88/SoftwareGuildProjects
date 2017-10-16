/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.servicelayer;

import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apitz_000
 */
public class VendingMachineServiceLayerTest {

    private VendingMachineServiceLayer service;

    public VendingMachineServiceLayerTest() {

//        VendingMachineDao dao = new VendingMachineDaoStubImpl();
//        VendingMachineAuditDao audit = new VendingMachineAuditDaoStubImpl();
//        
//        service= new VendingMachineServiceLayerImpl(dao, audit);

        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("applicationContext.xml");
        service
                = ctx.getBean("serviceLayer", VendingMachineServiceLayer.class);

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
     * Test of listItems method, of class VendingMachineServiceLayer.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testListItems() throws Exception {
        assertEquals(1, service.listItems().size());

    }

    /**
     * Test of getItem method, of class VendingMachineServiceLayer.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetItem() throws Exception {
        Item item = service.getItem("999");
        assertNotNull(item);
        item = service.getItem("888");
        assertNull(item);
    }

    /**
     * Test of changeInventory method, of class VendingMachineServiceLayer.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testChangeInventory() throws Exception {
        Item item = service.getItem("999");
        service.changeInventory(item);
        assertEquals(98, item.getInventory());

    }

    /**
     * Test of makeChange method, of class VendingMachineServiceLayer.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testMakeChange() throws Exception {

        BigDecimal balance = new BigDecimal("1.16");

        Change userChange = new Change();
        BigDecimal allPennies = new BigDecimal("0");
        allPennies = balance.multiply(new BigDecimal(100));
        int pennies = allPennies.intValueExact();
        int quarterRemain = pennies % 25;
        int quarterChange = pennies - quarterRemain;
        int quarters = quarterChange / 25;
        int dimeRemain = quarterRemain % 10;
        int dimeChange = quarterRemain - dimeRemain;
        int dimes = dimeChange / 10;
        int nickelRemain = dimeRemain % 5;
        int nickelChange = dimeRemain - nickelRemain;
        int nickels = nickelChange / 5;
        pennies = nickelRemain;

        userChange.setQuarters(quarters);

        userChange.setDimes(dimes);

        userChange.setNickels(nickels);

        userChange.setPennies(pennies);

        assertEquals(4, quarters);
        assertEquals(1, dimes);
        assertEquals(1, nickels);
        assertEquals(1, pennies);
    }

}
