/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apitz_000
 */
public class VendingMachineDaoImpl implements VendingMachineDao {

    public static final String ITEM_FILE = "items.txt";
    public static final String DELIMITER = "::";

    private Map<String, Item> items = new HashMap<>();

    private void loadItems() throws VendingMachineDaoException {
        Scanner sc;

        try {
            sc = new Scanner(
                    new BufferedReader(
                            new FileReader(ITEM_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachineDaoException(
                    "Could not load items data into memory.", e);
        }
        String currentLine;
        String[] currentTokens;

        while (sc.hasNext()) {
            currentLine = sc.next();
            currentTokens = currentLine.split(DELIMITER);

            Item currentItem = new Item(currentTokens[0]);
            currentItem.setName(currentTokens[1]);
            currentItem.setPrice(currentTokens[2]);
            currentItem.setInventory(Integer.parseInt(currentTokens[3]));

            items.put(currentItem.getId(), currentItem);
        }
        sc.close();
    }

    private void writeItems() throws VendingMachineDaoException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ITEM_FILE));
        } catch (IOException e) {
            throw new VendingMachineDaoException(
                    "Could not save item data", e);
        }
        List<Item> itemList = this.listItems();
        for (Item currentItem : itemList) {
            out.println(currentItem.getId() + DELIMITER
                    + currentItem.getName() + DELIMITER
                    + currentItem.getPrice() + DELIMITER
                    + currentItem.getInventory());
            out.flush();
        }
        out.close();
    }

    @Override
    public List<Item> listItems() throws VendingMachineDaoException {
        loadItems();
        return new ArrayList<Item>(items.values());

    }

    @Override
    public Item getItem(String id) throws VendingMachineDaoException {
        loadItems();
        return items.get(id);
    }

    @Override
    public void changeInventory(Item item) throws VendingMachineDaoException {
        getItem(item.getId()).setInventory(item.getInventory() - 1);
        writeItems();

    }

    //Maintenece/Stocking options?
    @Override
    public Item removeItem(String id) throws VendingMachineDaoException {
        Item removeItem = items.remove(id);
        writeItems();
        return removeItem;
    }

    @Override
    public Item addItem(String id, Item item) throws VendingMachineDaoException {
        Item newItem = items.put(id, item);
        writeItems();
        return newItem;
    }
}
