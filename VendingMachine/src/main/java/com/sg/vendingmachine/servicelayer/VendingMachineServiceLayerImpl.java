/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.servicelayer;

import com.sg.vendingmachine.controller.InsufficientFundsException;
import com.sg.vendingmachine.controller.ItemOutOfStockException;
import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineDao;
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
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    private VendingMachineDao dao;
    private VendingMachineAuditDao audit;

    public VendingMachineServiceLayerImpl(VendingMachineDao dao,VendingMachineAuditDao audit) {
        this.dao = dao;
        this.audit = audit;

    }

    @Override
    public List<Item> listItems() throws VendingMachineDaoException { 
            return dao.listItems();       
    }

    @Override
    public Item getItem(String id) throws VendingMachineDaoException {
        
        return dao.getItem(id);
    }

    @Override
    public void changeInventory(Item item) throws VendingMachineDaoException {
        dao.changeInventory(item);
    }

    @Override
    public Change makeChange(BigDecimal balance) throws VendingMachinePersistenceException {

        Change userChange = new Change();

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

        userChange.setQuarters(quarters);
        userChange.setDimes(dimes);
        userChange.setNickels(nickels);
        userChange.setPennies(pennies);

        return userChange;
    }
    
    // checkInventory and checkPrice jsut for audit logging for now - should do logic her instead of controller in future
    @Override
    public void checkInventory(String id) throws VendingMachineDaoException, ItemOutOfStockException, VendingMachinePersistenceException{
        Item item = dao.getItem(id);
        
        if (item == null){
            throw new ItemOutOfStockException("INVALID ITEM: DOES NOT EXIST");
        }
        if (item.getInventory() == 0){
            throw new ItemOutOfStockException("ITEM IS OUT OF STOCK");
            
        }
    }
    
    @Override
    public void checkPrice(BigDecimal balance, BigDecimal itemPrice) throws VendingMachineDaoException, InsufficientFundsException {
        
        if (balance.compareTo(itemPrice)==-1) {
            
            throw new InsufficientFundsException("INSUFFICIENT FUNDS");
            
        }
    }
    
    
    
    

}
