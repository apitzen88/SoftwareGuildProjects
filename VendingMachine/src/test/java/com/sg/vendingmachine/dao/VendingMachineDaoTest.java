/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
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
public class VendingMachineDaoTest {

    private VendingMachineDao dao = new VendingMachineDaoStubImpl();

    public VendingMachineDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
        List<Item> itemList = dao.listItems();
        for (Item item : itemList) {
            dao.removeItem(item.getId());
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of listItems method, of class VendingMachineDao.
     */
    @Test
    public void testListItems() throws Exception {
        Item item1 = new Item("001");
        item1.setName("Slime");
        item1.setPrice("4.50");
        item1.setInventory(2);
        dao.addItem(item1.getId(), item1);

        Item item2 = new Item("002");
        item2.setName("SLudge");
        item2.setPrice("3.50");
        item2.setInventory(1);
        dao.addItem(item2.getId(), item2);

        assertEquals(2, dao.listItems().size());
    }

    /**
     * Test of getItem method, of class VendingMachineDao.
     */
    @Test
    public void testGetItem() throws Exception {
        Item item1 = new Item("001");
        item1.setName("testing");
        item1.setPrice("3.45");
        item1.setInventory(4);
        dao.addItem(item1.getId(), item1);

        assertEquals("testing", dao.getItem("001").getName());
    }

    /**
     * Test of changeInventory method, of class VendingMachineDao.
     */
    @Test
    public void testChangeInventory() throws Exception {
        Item item1 = new Item("001");
        item1.setName("Blorp");
        item1.setPrice("3.45");
        item1.setInventory(4);
        dao.addItem(item1.getId(), item1);

        dao.changeInventory(item1);

        assertEquals(3, dao.getItem("001").getInventory());
    }

}
