/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery;

import com.sg.flooringmastery.controller.Controller;
import com.sg.flooringmastery.dao.DaoException;
import com.sg.flooringmastery.dao.OrdersDao;
import com.sg.flooringmastery.dao.OrdersDaoImpl;
import com.sg.flooringmastery.dao.PersistenceException;
import com.sg.flooringmastery.dao.ProductDao;
import com.sg.flooringmastery.dao.ProductDaoImpl;
import com.sg.flooringmastery.dao.TaxDao;
import com.sg.flooringmastery.dao.TaxDaoImpl;
import com.sg.flooringmastery.service.OrderValidationException;
import com.sg.flooringmastery.service.Service;
import com.sg.flooringmastery.service.ServiceImpl;
import com.sg.flooringmastery.view.InvalidEntryException;
import com.sg.flooringmastery.view.UserIO;
import com.sg.flooringmastery.view.UserIOConsoleImpl;
import com.sg.flooringmastery.view.View;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apitz_000
 */
public class App {

    public static void main(String[] args) {

//        UserIO io = new UserIOConsoleImpl();
//        View view = new View(io);
//        OrdersDao ordersDao = new OrdersDaoImpl();
//        ProductDao productDao = new ProductDaoImpl();
//        TaxDao taxDao = new TaxDaoImpl();
//        Service service = new ServiceImpl(ordersDao, productDao, taxDao);
//        Controller controller = new Controller(view, service);
//
//        controller.run();
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("applicationContext.xml");
        Controller controller
                = ctx.getBean("controller", Controller.class);
        controller.run();

    }
}
