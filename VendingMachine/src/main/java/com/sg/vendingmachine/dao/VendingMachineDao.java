/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import java.util.List;

/**
 *
 * @author apitz_000
 */
public interface VendingMachineDao {
    
    public List<Item> listItems() throws VendingMachineDaoException;
    
    public Item getItem(String id) throws VendingMachineDaoException;
    
    public void changeInventory(Item item) throws VendingMachineDaoException;
    
    public Item removeItem(String id) throws VendingMachineDaoException;
    
    public Item addItem(String id, Item item) throws VendingMachineDaoException;
}
