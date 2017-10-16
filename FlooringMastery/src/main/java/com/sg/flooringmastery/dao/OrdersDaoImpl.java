/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author apitz_000
 */
public class OrdersDaoImpl implements OrdersDao {

    private Map<String, Order> byDateOrderMap = new HashMap<>();
    private Map<String, Order> inMemOrderMap = new HashMap<>();
    private List<String> choppingBlock = new ArrayList<String>();

    private DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("MMddyyyy");

    //change to local filepath
    private String ordersFilePath = "Orders"+File.separatorChar;
    private String DELIMITER = ",";
    String mode;

    private void loadOrders(String date) throws PersistenceException {
        Scanner sc;

        try {

            File ordersByDate = new File(ordersFilePath);
            File[] ordersByDateList = ordersByDate.listFiles();

            for (int i = 0; i < ordersByDateList.length; i++) {
                if (ordersByDateList[i].isFile()) {
                    String ordersFile = ordersByDateList[i].getName();
                    if (ordersFile.startsWith("Orders_") && ordersFile.endsWith(".txt") && ordersFile.contains(date)) {

                        sc = new Scanner(
                                new BufferedReader(
                                        new FileReader(ordersByDateList[i])));

                        String currentLine;
                        String[] currentTokens;

                        while (sc.hasNext()) {
                            currentLine = sc.nextLine();
                            currentTokens = currentLine.split(DELIMITER);

                            Order currentOrder = new Order(currentTokens[0]);
                            currentOrder.setCustomerName(currentTokens[1]);
                            currentOrder.setState(currentTokens[2]);
                            currentOrder.setTaxRate(currentTokens[3]);
                            currentOrder.setMaterial(currentTokens[4]);
                            currentOrder.setArea(currentTokens[5]);
                            currentOrder.setCostPerSqFt(currentTokens[6]);
                            currentOrder.setLaborCostPerSqFt(currentTokens[7]);
                            currentOrder.setMaterialCost(currentTokens[8]);
                            currentOrder.setLaborCost(currentTokens[9]);
                            currentOrder.setTotalTax(currentTokens[10]);
                            currentOrder.setGrandTotal(currentTokens[11]);
                            currentOrder.setDate(date);

                            String orderKey = currentOrder.getDate() + currentOrder.getOrderNumber();
                            byDateOrderMap.put(orderKey, currentOrder);
                            for (String poorSoul : choppingBlock) {
                                byDateOrderMap.remove(poorSoul);
                            }
                        }
                        sc.close();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new PersistenceException("Could Not Load file", e);
        }
    }

    public void loadMode() {
        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader("mode.txt")));
            String currentLine;

            while (sc.hasNextLine()) {
                currentLine = sc.nextLine();
                mode = currentLine;
            }
            sc.close();

        } catch (FileNotFoundException e) {
            System.out.println("Could not find mode file");
        }

    }

    private void writeOrders(String date) throws PersistenceException {

        loadMode();
        if (mode.equals("prod")) {

            List<Order> orders = this.listOrdersByDate(date);

            PrintWriter out;
            String orderFile = "Orders_" + date + ".txt";
            String filePath = ordersFilePath + orderFile;

            try {

                out = new PrintWriter(new FileWriter(filePath));

            } catch (IOException e) {

                throw new PersistenceException("Could not save file", e);

            }

            for (Order currentOrder : orders) {

                out.println(
                        currentOrder.getOrderNumber() + DELIMITER
                        + currentOrder.getCustomerName() + DELIMITER
                        + currentOrder.getState() + DELIMITER
                        + currentOrder.getTaxRate() + DELIMITER
                        + currentOrder.getMaterial() + DELIMITER
                        + currentOrder.getArea() + DELIMITER
                        + currentOrder.getCostPerSqFt() + DELIMITER
                        + currentOrder.getLaborCostPerSqFt() + DELIMITER
                        + currentOrder.getMaterialCost() + DELIMITER
                        + currentOrder.getLaborCost() + DELIMITER
                        + currentOrder.getTotalTax() + DELIMITER
                        + currentOrder.getGrandTotal());

                out.flush();
            }
            out.close();
        } else {
            //DONT WRITE TO FILE

        }
    }


    private int assignOrderNumber(Order order) throws PersistenceException {
        String date = order.getDate();
        List<Order> orders = this.listOrdersByDate(date);

        int orderNumber = 0;

        for (Order currentOrder : orders) {
            if (currentOrder.getDate().equals(date)
                    && Integer.parseInt(currentOrder.getOrderNumber()) > orderNumber) {
                orderNumber = Integer.parseInt(currentOrder.getOrderNumber());
            }
        }
        return orderNumber + 1;
    }

    @Override
    public List<Order> listOrdersByDate(String date) throws PersistenceException {
        String thisDate = date.toString();
        loadOrders(thisDate);
        byDateOrderMap.putAll(inMemOrderMap);
        List<Order> allOrders = new ArrayList<>(byDateOrderMap.values());
        List<Order> byDateOrders = new ArrayList();
        for (Order order : allOrders) {
            if (order.getDate().equals(date)) {
                byDateOrders.add(order);
            }
        }
        return byDateOrders;
    }

    @Override
    public Order getOrderByKey(String orderKey) {
        Order order = byDateOrderMap.get(orderKey);
        return order;
    }

    @Override
    public void addOrder(Order order) {
        try {
            String date = order.getDate();
            loadOrders(date);

            int orderNumber = assignOrderNumber(order);
            String newNumber = Integer.toString(orderNumber);
            order.setOrderNumber(newNumber);

            String orderKey = order.getDate() + order.getOrderNumber();
            byDateOrderMap.put(orderKey, order);

        } catch (PersistenceException ex) {
            ex.getMessage();
        }
    }

    @Override
    public void removeOrder(String orderKey) {
        byDateOrderMap.remove(orderKey);
        inMemOrderMap.remove(orderKey);
        choppingBlock.add(orderKey);
    }

    @Override
    public void addEditedOrder(String orderKey, Order order) {
        try {
            String date = order.getDate();
            loadOrders(date);

            inMemOrderMap.put(orderKey, order);

        } catch (PersistenceException ex) {
            ex.getMessage();
        }

    }

    @Override
    public void saveWork() throws PersistenceException {
        byDateOrderMap.putAll(inMemOrderMap);
        List<Order> orders = new ArrayList<Order>(byDateOrderMap.values());
        for (Order order : orders) {
            writeOrders(order.getDate());
        }
    }

}
