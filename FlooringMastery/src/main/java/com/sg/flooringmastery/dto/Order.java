/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dto;

import java.math.BigDecimal;

/**
 *
 * @author apitz_000
 */
public class Order {

    private String date;
    private String orderNumber;
    private String customerName; //input
    private String state;  //input
    private String taxRate;
    private String material; //input
    private String area; //input
    private String costPerSqFt;
    private String laborCostPerSqFt;
    private String materialCost;
    private String laborCost;
    private String totalTax;
    private String grandTotal;

    public Order(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Order() {

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCostPerSqFt() {
        return costPerSqFt;
    }

    public void setCostPerSqFt(String costPerSqFt) {
        this.costPerSqFt = costPerSqFt;
    }

    public String getLaborCostPerSqFt() {
        return laborCostPerSqFt;
    }

    public void setLaborCostPerSqFt(String laborCostPerSqFt) {
        this.laborCostPerSqFt = laborCostPerSqFt;
    }

    public String getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(String materialCost) {
        this.materialCost = materialCost;
    }

    public String getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(String laborCost) {
        this.laborCost = laborCost;
    }

    public String getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(String totalTax) {
        this.totalTax = totalTax;
    }

    public String getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(String grandTotal) {
        this.grandTotal = grandTotal;
    }

}
