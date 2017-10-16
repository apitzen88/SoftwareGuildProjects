/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.DaoException;
import com.sg.flooringmastery.dao.PersistenceException;
import com.sg.flooringmastery.dto.Order;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author apitz_000
 */
public interface Service {
    
   public List<Order> listOrdersByDate(String date) throws PersistenceException;
   
   public Order getOrderByOrderKey(String orderKey);
   
    public Order createOrder(Order order) throws OrderValidationException;
    
    public void addNewOrder(Order order);
    
    public void replaceExistingOrder(String orderKey, Order order);

    public Order editOrder (String orderKey, Order order) throws DaoException, PersistenceException, OrderValidationException;
    
    public void removeOrder(String orderKey);

    public void saveWork() throws PersistenceException;

}
