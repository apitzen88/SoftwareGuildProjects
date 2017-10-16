/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author apitz_000
 */
public class VendingMachineView {

    UserIO io;

    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    
    public int InsertCoins() {
        io.print("**Insert Coins**");
        io.print("1. Quarter");
        io.print("2. Dime");
        io.print("3. Nickel");
        io.print("4. Penny");

        return io.readInt("Select a coin to insert, hit (5) when complete: ");
    }

    public void displayBalance(BigDecimal balance) {
        io.print("Balance: $" + balance);
    }

    public void displayChange(Change change) {
        io.print("Change: " + "Quarters: " + change.getQuarters() + " | "
                + "Dimes: " + change.getDimes() + " | "
                + "Nickels: " + change.getNickels() + " | "
                + "Pennies: " + change.getPennies());
    }

    
    
    public String getItemChoice() {
        return io.readString("Enter item ID: ");
    }

    public void displayItems(List<Item> itemList) {
        for (Item currentItem : itemList) {
            io.print(currentItem.getId() + ": "
                    + currentItem.getName()
                    + " | $"
                    + currentItem.getPrice()
                    + " | Stock: "
                    + currentItem.getInventory());
        }
    }
    
    
    public void MenuBanner(){
        io.print("**Menu**");
        io.print("========");
    }

    public void outOfStockNotice() {
        io.print("ITEM OUT OF STOCK");
    }

    public void addMoneyNotice() {
        io.print("PLEASE ADD COINS");
    }
    
    public void invalidItemIdNotice(){
        io.print("NO ITEM AT THAT ID");
    }
    
    public void invalidCoinTypeNotice(){
        io.print("INVALID COIN TYPE");
    }

}
