/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.dao.DaoException;
import com.sg.flooringmastery.dao.PersistenceException;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.service.OrderValidationException;
import com.sg.flooringmastery.service.Service;
import com.sg.flooringmastery.view.InvalidEntryException;
import com.sg.flooringmastery.view.View;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author apitz_000
 */
public class Controller {

    private View view;
    private Service service;

    public Controller(View view, Service service) {
        this.view = view;
        this.service = service;
    }

    public void run() {

        boolean keepGoing = true;
        int menuSelection;

        while (keepGoing == true) {

            menuSelection = view.MainMenuSelector();

            switch (menuSelection) {
                case 1:
                    listOrdersByDate(); //WORKS FOR NOW
                    break;
                case 2:
                    createNewOrder(); // WORKS FOR NOW
                    break;
                case 3:
                    editOrder();  // WORKS Probably
                    break;
                case 4:
                    removeOrder(); //THIS WORKS I THINK
                    break;
                case 5:
                    saveWork(); //WORKS I THINK
                    break;
                case 6:
                    view.exitMessage();
                    keepGoing = false;
                    break;
                default:
                    view.unknownCommand();
            }

        }
    }

    public void listOrdersByDate() {
        try {
            String date = view.getDateInput();
            List<Order> orderList = service.listOrdersByDate(date);
            view.displayOrderList(orderList);
        } catch (PersistenceException e) {
            view.persistenceErrorMessage();
        }
    }

    public void createNewOrder() {
        try {
            Order newOrder = view.createNewOrder();
            Order newCompleteOrder = service.createOrder(newOrder);
            service.addNewOrder(newCompleteOrder);
        } catch (OrderValidationException e) {
            view.invalidEntryMessage();
        }
    }

    public void editOrder() {
        try {
            String date = view.getDateInput();
            List<Order> orders = service.listOrdersByDate(date);
            view.displayOrderList(orders);

            String orderNumber = view.getOrderNumberInput();
            String orderKey = date + orderNumber;

            Order toEditOrder = service.getOrderByOrderKey(orderKey);
            Order orderEdits = view.editOrder(toEditOrder);
            Order updatedOrder = service.editOrder(orderKey, orderEdits);
            service.replaceExistingOrder(orderKey, updatedOrder);

        } catch (PersistenceException | DaoException | OrderValidationException e) {
            view.invalidEntryMessage();
        } catch (NullPointerException x) {
            view.unknownCommand();
        }
    }

    public void removeOrder() {
        try {
            String date = view.getDateInput();
            List<Order> orderList = service.listOrdersByDate(date);
            view.displayOrderList(orderList);
            String orderNumber = view.getOrderNumberInput();
            String orderKey = date + orderNumber;

            service.removeOrder(orderKey);

        } catch (PersistenceException e) {
            view.persistenceErrorMessage();
        } catch (NullPointerException x) {
            view.unknownCommand();
        }
    }

    public void saveWork() {
        try {
            service.saveWork();
        } catch (PersistenceException e) {
            view.persistenceErrorMessage();
        }
    }
}
