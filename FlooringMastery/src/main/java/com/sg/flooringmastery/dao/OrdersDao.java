/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author apitz_000
 */
public interface OrdersDao {

    public List<Order> listOrdersByDate(String date) throws PersistenceException;

    public void addOrder(Order order);

    public void removeOrder(String orderKey);

    public void addEditedOrder(String orderKey, Order order);

    public void saveWork() throws PersistenceException;

    public Order getOrderByKey(String orderKey);

}
