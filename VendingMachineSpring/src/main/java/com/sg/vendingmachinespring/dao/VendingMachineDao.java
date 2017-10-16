/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespring.dao;

import com.sg.vendingmachinespring.model.Item;
import java.util.List;

/**
 *
 * @author apitz_000
 */
public interface VendingMachineDao {

    public List<Item> listItems();

    public Item getItem(String id);

    public void changeInventory(Item item);
    
}
