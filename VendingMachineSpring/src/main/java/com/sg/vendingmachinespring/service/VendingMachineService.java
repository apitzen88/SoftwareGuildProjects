/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespring.service;

import com.sg.vendingmachinespring.model.Change;
import com.sg.vendingmachinespring.model.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author apitz_000
 */
public interface VendingMachineService {
    
    public List<Item> listItems();
    
    public Item getItem(String id);
    
    public void changeInventory(Item item);
    
    public void addMoney(BigDecimal money);
    
    public String currentMessage();
    
    public void purchaseItem(String id) throws InvalidEntryException;
    
    public BigDecimal currentBalance();
    
    public Change makeChange();
    
     public Change getChange();
    
}
