/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Tax;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author apitz_000
 */
public class TaxDaoImpl implements TaxDao{

    public static final String TAX_FILE = "Taxes.txt";
    public static final String DELIMITER = ",";

    public Map<String, Tax> stateTaxes = new HashMap<>();

    private void loadTaxes() throws DaoException {
        Scanner sc;

        try {
            sc = new Scanner(
                    new BufferedReader(
                            new FileReader(TAX_FILE)));
        } catch (FileNotFoundException e) {
            throw new DaoException(
                    "Could not load tax data into memory.", e);
        }
        String currentLine;
        String[] currentTokens;

        while (sc.hasNext()) {
            currentLine = sc.next();
            currentTokens = currentLine.split(DELIMITER);

            Tax currentTax = new Tax(currentTokens[0]);
            currentTax.setTaxRate(new BigDecimal(currentTokens[1]));

            stateTaxes.put(currentTax.getStateName(), currentTax);
        }
        sc.close();
    }
    
//    @Override
//    public BigDecimal getTaxRate(String stateName) throws DaoException{
//        loadTaxes();
//        Tax state = stateTaxes.get(stateName);
//        return state.getTaxRate();
//        
//    }
    
    @Override
    public Tax getStateTax(String stateName) throws DaoException{
        loadTaxes();
        Tax state = stateTaxes.get(stateName);
        return state;
    }
    

}
