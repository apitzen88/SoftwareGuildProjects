/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine;

import com.sg.vendingmachine.controller.VendingMachineController;
import com.sg.vendingmachine.dao.VendingMachineDaoException;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.controller.ItemOutOfStockException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apitz_000
 */
public class App {

    public static void main(String[] args) throws VendingMachineDaoException, VendingMachinePersistenceException, ItemOutOfStockException {

//        UserIO io = new UserIOConsoleImpl();
//        VendingMachineView view = new VendingMachineView(io);
//        VendingMachineDao dao = new VendingMachineDaoImpl();
//        VendingMachineAuditDao audit = new VendingMachineAuditDaoImpl();
//        VendingMachineServiceLayer service = new VendingMachineServiceLayerImpl(dao, audit);
//        VendingMachineController controller = new VendingMachineController(view, service);
       
//        controller.run();

        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("applicationContext.xml");
        VendingMachineController controller
                = ctx.getBean("controller", VendingMachineController.class);
        controller.run();
        
        //ok
        
    }

}
