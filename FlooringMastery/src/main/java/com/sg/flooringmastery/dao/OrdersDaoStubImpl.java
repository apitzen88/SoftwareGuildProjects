/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apitz_000
 */
public class OrdersDaoStubImpl implements OrdersDao {

    Order onlyOrder;
    private Map<String, Order> orders = new HashMap<>();

    public OrdersDaoStubImpl() {
        onlyOrder = new Order();
        onlyOrder.setDate("2019-09-09");
        onlyOrder.setOrderNumber("1");
        onlyOrder.setCustomerName("Goober");
        onlyOrder.setState("NM");
        onlyOrder.setTaxRate("6.50");
        onlyOrder.setMaterial("Bearskin");
        onlyOrder.setArea("100");
        onlyOrder.setCostPerSqFt("1.50");
        onlyOrder.setLaborCost("3.00");
        onlyOrder.setMaterialCost("150");
        onlyOrder.setLaborCost("300");
        onlyOrder.setTotalTax("29.25");
        onlyOrder.setGrandTotal("479.25");

        String orderKey = onlyOrder.getDate() + onlyOrder.getOrderNumber();

        orders.put(orderKey, onlyOrder);

    }

    @Override
    public List<Order> listOrdersByDate(String date) throws PersistenceException {
        List<Order> allOrders = new ArrayList<>(orders.values());
        List<Order> byDateOrders = new ArrayList();
        for (Order order : allOrders) {
            if (order.getDate().equals(date)) {
                byDateOrders.add(order);
            }
        }
        return byDateOrders;
    }

    @Override
    public void addOrder(Order order) {
        String orderKey = order.getDate()+order.getOrderNumber();
        orders.put(orderKey, order);
        
    }

    @Override
    public void removeOrder(String orderKey) {
        orders.remove(orderKey);
    }

    @Override
    public void addEditedOrder(String orderKey, Order order) {
        orders.put(orderKey, order);
    }

    @Override
    public void saveWork() throws PersistenceException {
    }

    @Override
    public Order getOrderByKey(String orderKey) {
        return orders.get(orderKey);
    }

}
