/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Tax;
import java.math.BigDecimal;

/**
 *
 * @author apitz_000
 */
public interface TaxDao {

    // public BigDecimal getTaxRate(String stateName) throws DaoException;
    public Tax getStateTax(String stateName) throws DaoException;

}
