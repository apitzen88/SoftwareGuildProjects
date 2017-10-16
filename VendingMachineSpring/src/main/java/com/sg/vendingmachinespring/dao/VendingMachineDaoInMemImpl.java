/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespring.dao;

import com.sg.vendingmachinespring.model.Item;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apitz_000
 */
public class VendingMachineDaoInMemImpl implements VendingMachineDao {

    private Map<String, Item> items = new HashMap<>();

    public VendingMachineDaoInMemImpl() {

        Item potato = new Item("1", "Potato", "1.25", 3);
        Item broccoli = new Item("2", "Broccoli", "1.50", 2);
        Item shoe = new Item("3", "Shoe", "2.50", 4);
        Item hat = new Item("4", "Hat", "3.00", 2);
        Item cheese = new Item("5", "Cheese", "1.15", 2);
        Item bread = new Item("6", "Bread", "2.15", 7);
        Item turkey = new Item("7", "Turkey", "1.49", 2);
        Item seaMonkey = new Item("8", "Sea Monkey", "0.99", 1);
        Item oldSock = new Item("9", "Old Sock", "0.27", 2);

        items.put(potato.getId(), potato);
        items.put(broccoli.getId(), broccoli);
        items.put(shoe.getId(), shoe);
        items.put(hat.getId(), hat);
        items.put(cheese.getId(), cheese);
        items.put(bread.getId(), bread);
        items.put(turkey.getId(), turkey);
        items.put(seaMonkey.getId(), seaMonkey);
        items.put(oldSock.getId(), oldSock);

    }

    @Override
    public List<Item> listItems() {
        return new ArrayList<Item>(items.values());
    }

    @Override
    public Item getItem(String id) {
        return items.get(id);
    }

    @Override
    public void changeInventory(Item item) {
        getItem(item.getId()).setInventory(item.getInventory() - 1);
    }

}
