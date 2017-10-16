/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespring.model;

/**
 *
 * @author apitz_000
 */
public class Item {

    String id;
    String name;
    String price;
    int inventory;
    
    public Item(String id, String name, String price, int inventory){
        this.id=id;
        this.name=name;
        this.price=price;
        this.inventory=inventory;
    }

    public Item(String id) {
        this.id = id;
    }

    public Item(int inventory) {
        this.setInventory(inventory);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

}
