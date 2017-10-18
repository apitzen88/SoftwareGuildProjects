/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespring.service;

import com.sg.vendingmachinespring.dao.VendingMachineDao;
import com.sg.vendingmachinespring.model.Change;
import com.sg.vendingmachinespring.model.Item;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author apitz_000
 */
public class VendingMachineServiceImpl implements VendingMachineService {

    BigDecimal balance = new BigDecimal("0.00");
    Change change;
    String message = "Hello!";

    VendingMachineDao dao;

    @Inject
    public VendingMachineServiceImpl(VendingMachineDao dao) {
        
        this.dao = dao;
    }

    @Override
    public List<Item> listItems() {
        
        return dao.listItems();

    }

    @Override
    public Item getItem(String id) {
        
        return dao.getItem(id);

    }

    @Override
    public void changeInventory(Item item) {
        
        dao.changeInventory(item);

    }

    @Override
    public void addMoney(BigDecimal money) {
        
        balance = balance.add(money);
        
    }

    @Override
    public void purchaseItem(String id) throws InvalidEntryException {
        try{
        Item item = getItem(id);
        BigDecimal price = new BigDecimal(item.getPrice());
        int stock = item.getInventory();
        BigDecimal difference = price.subtract(balance);
        if (stock <= 0) {
            
            message = item.getName()+" is out of stock...";
            
        } else if (price.compareTo(balance) > 0) {
            
            message = "Please add $"+difference;
            
        } else {
            
            balance = balance.subtract(price);
            makeChange();
            changeInventory(item);
        }
        } catch (NullPointerException ex){
            message = "Try a different Selection...";
            throw new InvalidEntryException("Invalid Entry");
        }
    }

    private Change calcChange(BigDecimal balance) {
        
        Change customerChange = new Change();

        BigDecimal allPennies = new BigDecimal("0");
        allPennies = balance.multiply(new BigDecimal(100));
        int pennies = allPennies.intValueExact();

        int quarterRemain = pennies % 25;
        int quarterChange = pennies - quarterRemain;
        int quarters = quarterChange / 25;

        int dimeRemain = quarterRemain % 10;
        int dimeChange = quarterRemain - dimeRemain;
        int dimes = dimeChange / 10;

        int nickelRemain = dimeRemain % 5;
        int nickelChange = dimeRemain - nickelRemain;
        int nickels = nickelChange / 5;

        pennies = nickelRemain;

        customerChange.setQuarters(quarters);
        customerChange.setDimes(dimes);
        customerChange.setNickels(nickels);
        customerChange.setPennies(pennies);

        this.change = customerChange;
        this.balance = new BigDecimal("0.00");
        this.message = "Thanks!";
        
        return customerChange;

    }
    
    @Override
    public Change getChange() {
        
        return change;
    }

    @Override
    public Change makeChange() {
        
       Change change = calcChange(balance);
       
       return change;
    }

    @Override
    public String currentMessage() {
        
        String currentMessage = message;
        
        return currentMessage;
    }

    @Override
    public BigDecimal currentBalance() {
        
        BigDecimal currentBalance = balance;
        
        return currentBalance;
    }

}
