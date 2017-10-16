/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author apitz_000
 */
public class ProductDaoImpl implements ProductDao {

    public static final String PRODUCT_FILE = "Products.txt";
    public static final String DELIMITER = ",";

    public Map<String, Product> products = new HashMap<>();

    private void loadProducts() throws DaoException {
        Scanner sc;

        try {
            sc = new Scanner(
                    new BufferedReader(
                            new FileReader(PRODUCT_FILE)));
        } catch (FileNotFoundException e) {
            throw new DaoException(
                    "Could not load product data into memory.", e);
        }
        String currentLine;
        String[] currentTokens;

        while (sc.hasNext()) {
            currentLine = sc.next();
            currentTokens = currentLine.split(DELIMITER);

            Product currentProduct = new Product(currentTokens[0]);
            currentProduct.setCostPerSqFt(new BigDecimal(currentTokens[1]));
            currentProduct.setLaborCostPerSqFt(new BigDecimal(currentTokens[2]));

            products.put(currentProduct.getProductType(), currentProduct);
        }
        sc.close();
    }

    //think about getting whole product and pulling from that instead of pulling info from here
//    @Override
//    public BigDecimal getCostPerSqFt(String productType) throws DaoException {
//        loadProducts();
//        Product product = products.get(productType);
//        String strCost = product.getCostPerSqFt();
//        BigDecimal bdCost = new BigDecimal(strCost).setScale(2, RoundingMode.HALF_UP);
//
//        return bdCost;
//    }
//
//    @Override
//    public BigDecimal getLaborCostPerSqFt(String productType) throws DaoException {
//        loadProducts();
//        Product product = products.get(productType);
//        String strCost = product.getLaborCostPerSqFt();
//        BigDecimal bdCost = new BigDecimal(strCost).setScale(2, RoundingMode.HALF_UP);
//
//        return bdCost;
//    }
    
    @Override
    public Product getProductInfo(String productType) throws DaoException {
        loadProducts();
        Product product = products.get(productType);
        return product;
    }

}
