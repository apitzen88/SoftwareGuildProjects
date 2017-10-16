/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.DaoException;
import com.sg.flooringmastery.dao.OrdersDao;
import com.sg.flooringmastery.dao.PersistenceException;
import com.sg.flooringmastery.dao.ProductDao;
import com.sg.flooringmastery.dao.TaxDao;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apitz_000
 */
public class ServiceImpl implements Service {

    private OrdersDao ordersDao;
    private ProductDao productDao;
    private TaxDao taxDao;

    public ServiceImpl(OrdersDao ordersDao, ProductDao productDao, TaxDao taxDao) {
        this.ordersDao = ordersDao;
        this.productDao = productDao;
        this.taxDao = taxDao;
    }

    @Override
    public List<Order> listOrdersByDate(String date) throws PersistenceException {
        return ordersDao.listOrdersByDate(date);
    }

    @Override
    public void removeOrder(String orderKey) {
        ordersDao.removeOrder(orderKey);
    }

    @Override
    public void saveWork() throws PersistenceException {
        ordersDao.saveWork();
    }

    @Override
    public Order getOrderByOrderKey(String orderKey) {
        Order order = ordersDao.getOrderByKey(orderKey);
        return order;
    }

    @Override
    public Order editOrder(String orderKey, Order order) throws DaoException, PersistenceException, OrderValidationException {
        Order toEditOrder = ordersDao.getOrderByKey(orderKey);
        if (toEditOrder == null) {
            throw new PersistenceException("Order Not Found");
        }

        String newName = order.getCustomerName();
        String newState = order.getState();
        String newMaterial = order.getMaterial();
        String newArea = order.getArea();

        if (newName != null && newName.trim().length() != 0) {
            toEditOrder.setCustomerName(newName);
        }
        if (newState != null && newState.trim().length() != 0) {
            toEditOrder.setState(newState);
        }
        if (newMaterial != null && newMaterial.trim().length() != 0) {
            toEditOrder.setMaterial(newMaterial);
        }
        if (newArea != null && newArea.trim().length() != 0) {
            toEditOrder.setArea(newArea);
        }

        toEditOrder = this.createOrder(toEditOrder);
        return toEditOrder;

    }
/////////////////////////////////////////////////////////////////////////////////////////////////

    private void validateNewOrder(Order order) throws OrderValidationException {
        // NAME, STATE, MATERIAL, AREA 
        if (order.getCustomerName().equals(null) || order.getCustomerName().trim().length() == 0
                || order.getState().equals(null) || order.getState().trim().length() == 0
                || order.getMaterial().equals(null) || order.getMaterial().trim().length() == 0
                || order.getArea().equals(null) || order.getArea().trim().length() == 0) {
            throw new OrderValidationException("Field entries could not be validated");
        }
    }

    private void validateTaxInfo(Order order) throws OrderValidationException {
        try {
            String state = order.getState();
            getStateTaxRate(state);
        } catch (NullPointerException | DaoException e) {
            throw new OrderValidationException("Invalid State Entry");
        }
    }

    private void validateProductInfo(Order order) throws OrderValidationException {
        try {
            String product = order.getMaterial();
            getCostPerSqFt(product);
        } catch (NullPointerException | DaoException e) {
            throw new OrderValidationException("Invalid Product entry");
        }
    }
//////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void addNewOrder(Order order) {
        ordersDao.addOrder(order);
    }

    @Override
    public void replaceExistingOrder(String orderKey, Order order) {
        ordersDao.addEditedOrder(orderKey, order);
    }

    @Override
    public Order createOrder(Order order) throws OrderValidationException {

        try {
            validateNewOrder(order);
            validateProductInfo(order);
            validateTaxInfo(order);

            BigDecimal stateTax = getStateTaxRate(order.getState());
            stateTax = stateTax.setScale(2, RoundingMode.DOWN);
            String stringStateTax = stateTax.toString();

            BigDecimal taxRate = getTaxRate(stateTax);
            taxRate = taxRate.setScale(2, RoundingMode.DOWN);

            BigDecimal costSqFt = getCostPerSqFt(order.getMaterial());
            costSqFt = costSqFt.setScale(2, RoundingMode.DOWN);
            String stringCostSqFt = costSqFt.toString();

            BigDecimal laborSqFt = getLaborCostPerSqFt(order.getMaterial());
            laborSqFt = laborSqFt.setScale(2, RoundingMode.DOWN);
            String stringLaborSqFt = laborSqFt.toString();

            BigDecimal area = getBdArea(order.getArea());
            area = area.setScale(2, RoundingMode.DOWN);

            BigDecimal materialCost = getMaterialCost(costSqFt, area);
            materialCost = materialCost.setScale(2, RoundingMode.DOWN);
            String stringMaterialCost = materialCost.toString();

            BigDecimal laborCost = getLaborCost(laborSqFt, area);
            laborCost = laborCost.setScale(2, RoundingMode.DOWN);
            String stringLaborCost = laborCost.toString();

            BigDecimal subTotal = getSubTotal(materialCost, laborCost);
            subTotal = subTotal.setScale(2, RoundingMode.DOWN);

            BigDecimal tax = getTax(subTotal, taxRate);
            tax = tax.setScale(2, RoundingMode.DOWN);
            String stringTax = tax.toString();

            BigDecimal grandTotal = getGrandTotal(subTotal, tax);
            grandTotal = grandTotal.setScale(2, RoundingMode.DOWN);
            String stringGrandTotal = grandTotal.toString();

            order.setTaxRate(stringStateTax);
            order.setCostPerSqFt(stringCostSqFt);
            order.setLaborCostPerSqFt(stringLaborSqFt);
            order.setMaterialCost(stringMaterialCost);
            order.setLaborCost(stringLaborCost);
            order.setTotalTax(stringTax);
            order.setGrandTotal(stringGrandTotal);

        } catch (DaoException ex) {
            throw new OrderValidationException("Order could not be Validated");
        }
        return order;
    }

    private BigDecimal getStateTaxRate(String state) throws DaoException {
        Tax tax = taxDao.getStateTax(state);
        return tax.getTaxRate();
    }

    private BigDecimal getTaxRate(BigDecimal tax) {
        BigDecimal taxRate = tax.divide(new BigDecimal(100));
        return taxRate;
    }

    private BigDecimal getCostPerSqFt(String material) throws DaoException {
        Product product = productDao.getProductInfo(material);
        BigDecimal costSqFt = product.getCostPerSqFt();
        return costSqFt;
    }

    private BigDecimal getLaborCostPerSqFt(String material) throws DaoException {
        Product product = productDao.getProductInfo(material);
        BigDecimal laborSqFt = product.getLaborCostPerSqFt();
        return laborSqFt;
    }

    private BigDecimal getBdArea(String area) {
        BigDecimal bdArea = new BigDecimal(area);
        return bdArea;
    }

    private BigDecimal getMaterialCost(BigDecimal costSqFt, BigDecimal area) {
        BigDecimal cost = area.multiply(costSqFt);
        return cost;
    }

    private BigDecimal getLaborCost(BigDecimal laborSqFt, BigDecimal area) {
        BigDecimal cost = area.multiply(laborSqFt);
        return cost;
    }

    private BigDecimal getSubTotal(BigDecimal materialCost, BigDecimal laborCost) {
        BigDecimal subTotal = materialCost.add(laborCost);
        return subTotal;
    }

    private BigDecimal getTax(BigDecimal subTotal, BigDecimal taxRate) {
        BigDecimal tax = subTotal.multiply(taxRate);
        return tax;
    }

    private BigDecimal getGrandTotal(BigDecimal subTotal, BigDecimal tax) {
        BigDecimal grandTotal = subTotal.add(tax);
        return grandTotal;
    }

}
