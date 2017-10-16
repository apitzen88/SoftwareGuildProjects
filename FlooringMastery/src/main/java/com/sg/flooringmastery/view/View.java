/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.view;

import com.sg.flooringmastery.dto.Order;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apitz_000
 */
public class View {

    private UserIO io;

    public View(UserIO io) {
        this.io = io;
    }

    private DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("MMddyyyy");
    //SimpleDateFormat formatDate = new SimpleDateFormat("ddmmyyyy");

    public int MainMenuSelector() {
        io.print("\nFlooring Program");
        io.print("****Main Menu****");
        io.print("=================");
        io.print("1. List Orders by Date");
        io.print("2. Create a New Order");
        io.print("3. Edit an Existing Order");
        io.print("4. Remove an Order");
        io.print("5. Save Current Work");
        io.print("6. Quit Program");
        io.print(" ");

        return io.readInt("Select a menu option: ");

    }

    public void displayOrderList(List<Order> orderList) {
        for (Order currentOrder : orderList) {
            io.print("Date: " + currentOrder.getDate() + " | "
                    + "Order #: " + currentOrder.getOrderNumber() + " | "
                    + "Customer: " + currentOrder.getCustomerName() + "\n"
                    + "State: " + currentOrder.getState() + " | "
                    + "Tax Rate: " + currentOrder.getTaxRate() + "%\n"
                    + "Material: " + currentOrder.getMaterial() + " | "
                    + "Area: " + currentOrder.getArea() + " sqft\n"
                    + "Cost/sqft: $" + currentOrder.getCostPerSqFt() + " | "
                    + "Labor Cost/sqft: $" + currentOrder.getLaborCostPerSqFt() + "\n"
                    + "Material Cost: $" + currentOrder.getMaterialCost() + " | "
                    + "Labor Cost: $" + currentOrder.getLaborCost() + "\n"
                    + "Total Tax: $" + currentOrder.getTotalTax() + " | "
                    + "Grand Total: $" + currentOrder.getGrandTotal() + "\n");
        }
        io.readString("Please hit enter to continue.");
    }

    public Order createNewOrder() {
        Order newOrder = new Order();
        newOrder.setDate(getDateInput());
        newOrder.setCustomerName(io.readString("Enter Customer Name: "));
        newOrder.setState(io.readString("Enter State: ").toUpperCase());
        newOrder.setMaterial(io.readString("Enter the Material: "));
        int intArea = (io.readInt("Enter the area (sqft): "));
        String stringArea = Integer.toString(intArea);
        newOrder.setArea(stringArea);
        return newOrder;
    }

    public Order editOrder(Order order) {
        Order toEditOrder = new Order();
        io.print("NOTE: If you do not want to change a field, leave it empty.");
        toEditOrder.setCustomerName(io.readString("Change Customer Name(" + order.getCustomerName() + "): "));
        toEditOrder.setState(io.readString("Change State(" + order.getState() + "): "));
        toEditOrder.setMaterial(io.readString("Change Material(" + order.getMaterial() + "): "));
        toEditOrder.setArea(io.readString("Change Area(" + order.getArea() + "): "));
        return toEditOrder;
    }

    public String getDateInput() {
        String date = io.readString("Enter date (mmddyyyy): ");
        try {
            LocalDate localDate = LocalDate.parse(date, formatDate);
            date = localDate.format(formatDate);
            date = localDate.toString();
        } catch (DateTimeParseException e) {
            io.print(date + " is not a valid date entry");
        }
        return date;

    }

    public String getOrderNumberInput() {
        String orderNumber = io.readString("Enter order number: ");
        return orderNumber;
    }

    public void exitMessage() {
        io.print("See ya...");
    }

    public void unknownCommand() {
        io.print("That's not a valid entry...");
    }

    public void invalidEntryMessage() {
        io.print("INVALID ENTRY - order could not be formed");
    }

    public void persistenceErrorMessage() {
        io.print("PERSISTENCE ERROR - could not persist data");
    }
}
