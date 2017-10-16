/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.servicelayer;

import com.sg.vendingmachine.controller.InsufficientFundsException;
import com.sg.vendingmachine.controller.ItemOutOfStockException;
import com.sg.vendingmachine.dao.VendingMachineDaoException;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author apitz_000
 */
public interface VendingMachineServiceLayer {
    
    public List<Item> listItems() throws VendingMachineDaoException;
    
    public Item getItem(String id) throws VendingMachineDaoException;
    
    public void changeInventory(Item item) throws VendingMachineDaoException;
    
    public Change makeChange(BigDecimal balance) throws VendingMachinePersistenceException;
    
    public void checkInventory(String id) throws VendingMachineDaoException, ItemOutOfStockException, VendingMachinePersistenceException;
    
    public void checkPrice(BigDecimal itemPrice, BigDecimal balance) throws VendingMachineDaoException, InsufficientFundsException;
    
//    public BigDecimal insertCoins(int coin);
    
}
