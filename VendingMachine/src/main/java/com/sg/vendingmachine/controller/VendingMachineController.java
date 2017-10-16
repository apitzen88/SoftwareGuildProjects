/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineDaoException;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.servicelayer.VendingMachineServiceLayer;
import com.sg.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apitz_000
 */
public class VendingMachineController {

    VendingMachineView view;
    VendingMachineServiceLayer service;

    public VendingMachineController(VendingMachineView view, VendingMachineServiceLayer service) {
        this.view = view;
        this.service = service;
    }

    public void run() {

        vend();
        //insert coin --> create balance
        //list items --> get item selection
        //check item stock
        //compare balance to item price
        //check balance>=price
        //calculate and return change
        //adjust inventory

    }

    public void vend() {

        BigDecimal balance;
        Item item;
        int stock;

        try {

            do {
                listItems();
                balance = insertCoins();
                listItems();
                item = getItemChoice();

                try {
                    stock = item.getInventory();

                    service.checkInventory(item.getId());

                    if (stock == 0) {

                        throw new ItemOutOfStockException("ITEM OUT OF STOCK");
                    }

                } catch (ItemOutOfStockException x) {
                    view.outOfStockNotice();
                    item = getItemChoice();
                    stock = item.getInventory();
                }

            } while (stock == 0);

            vendItem(item, balance);

        } catch (VendingMachinePersistenceException | VendingMachineDaoException e) {
            e.getMessage();
        }

    }

    public void vendItem(Item item, BigDecimal balance) throws VendingMachineDaoException {

        BigDecimal itemPrice = new BigDecimal(item.getPrice());
        balance = comparePrice(balance, itemPrice);
        makeChange(balance);
        service.changeInventory(item);

    }

    public void makeChange(BigDecimal balance) {
        try {
            Change change = service.makeChange(balance);
            view.displayChange(change);
        } catch (VendingMachinePersistenceException e) {
            e.getMessage();
        }
    }

    public BigDecimal comparePrice(BigDecimal balance, BigDecimal itemPrice) {
        do {
            try {
                
                service.checkPrice(balance, itemPrice);
                
                if (balance.compareTo(itemPrice) == -1) {

                    throw new InsufficientFundsException("ITEM OUT OF STOCK");

                } else {

                    balance = balance.subtract(itemPrice);

                }

            } catch (InsufficientFundsException x) {

                view.addMoneyNotice();
                BigDecimal addFunds = insertCoins();
                balance = balance.add(new BigDecimal(String.valueOf(addFunds)));

            } catch (VendingMachineDaoException ex) {
                ex.getMessage();
            }

        } while (balance.compareTo(itemPrice) == -1);

        balance = balance.subtract(itemPrice);

        return balance;
    }

    public Item getItemChoice() throws VendingMachineDaoException, VendingMachinePersistenceException {
        String id = view.getItemChoice();
        Item item = service.getItem(id);
        //service.checkInventory(item.getId());

        while (item == null) {
            view.invalidItemIdNotice();
            id = view.getItemChoice();
            item = service.getItem(id);
        }
        return item;
    }

    public void listItems() throws VendingMachineDaoException {
        List<Item> itemList = service.listItems();
        view.MenuBanner();
        view.displayItems(itemList);
    }

    public BigDecimal insertCoins() {

        BigDecimal quarter = new BigDecimal("0.25");
        BigDecimal dime = new BigDecimal("0.10");
        BigDecimal nickel = new BigDecimal("0.05");
        BigDecimal penny = new BigDecimal("0.01");

        boolean addCoins = true;
        BigDecimal bdTotal = new BigDecimal("0");

        while (addCoins == true) {

            int coin = view.InsertCoins();

            switch (coin) {
                case 1:
                    bdTotal = bdTotal.add(quarter);
                    view.displayBalance(bdTotal);
                    break;
                case 2:
                    bdTotal = bdTotal.add(dime);
                    view.displayBalance(bdTotal);
                    break;
                case 3:
                    bdTotal = bdTotal.add(nickel);
                    view.displayBalance(bdTotal);
                    break;
                case 4:
                    bdTotal = bdTotal.add(penny);
                    view.displayBalance(bdTotal);
                    break;
                case 5:
                    addCoins = false;
                    break;
                default:
                    view.invalidCoinTypeNotice();
            }
        }
        return bdTotal;
    }
}
