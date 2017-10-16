/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;
import java.math.BigDecimal;

/**
 *
 * @author apitz_000
 */
public interface ProductDao {

//    public BigDecimal getCostPerSqFt(String productType) throws DaoException;
//
//    public BigDecimal getLaborCostPerSqFt(String productType) throws DaoException;

    public Product getProductInfo(String productType) throws DaoException;
}
