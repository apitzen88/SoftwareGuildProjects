/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apitz_000
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao {
//    Item onlyItem;
//    List<Item> items = new ArrayList<>();
//
//    public VendingMachineDaoStubImpl() {
//        onlyItem = new Item("999");
//        onlyItem.setName("Blorp");
//        onlyItem.setPrice("99.99");
//        onlyItem.setInventory(99);
//
//        items.add(onlyItem);
//    }
    
    Item onlyItem;
    private Map<String, Item> items = new HashMap<>();
    
    public VendingMachineDaoStubImpl() {
        onlyItem = new Item("999");
        onlyItem.setName("Blorp");
        onlyItem.setPrice("99.99");
        onlyItem.setInventory(99);
        
        items.put("999", onlyItem);
        
    }
    
    
    
        @Override
    public List<Item> listItems() throws VendingMachineDaoException {
        return new ArrayList<Item>(items.values());

    }

    @Override
    public Item getItem(String id) throws VendingMachineDaoException {
        return items.get(id);
    }

    @Override
    public void changeInventory(Item item) throws VendingMachineDaoException {
        getItem(item.getId()).setInventory(item.getInventory() - 1);
      

    }

    //Maintenece/Stocking options?
    @Override
    public Item removeItem(String id) throws VendingMachineDaoException {
        Item removeItem = items.remove(id);
        return removeItem;
    }

    @Override
    public Item addItem(String id, Item item) throws VendingMachineDaoException {
        Item newItem = items.put(id, item);
        return newItem;
    }

//    @Override
//    public List<Item> listItems() throws VendingMachineDaoException {
//        return items;
//    }
//
//    @Override
//    public Item getItem(String id) throws VendingMachineDaoException {
//        if (id.equals(onlyItem.getId())) {
//            return onlyItem;
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    public void changeInventory(Item item) throws VendingMachineDaoException {
//        getItem(item.getId()).setInventory(item.getInventory() - 1);
//
//    }
//
//    @Override
//    public Item removeItem(String id) throws VendingMachineDaoException {
//        if (id.equals(onlyItem.getId())) {
//            return onlyItem;
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    public Item addItem(String id, Item item) throws VendingMachineDaoException {
//        if (id.equals(onlyItem.getId())) {
//            return onlyItem;
//        } else {
//            return item;
//        }
//    }

}
