/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author apitz_000
 */
public class ProductDaoStubImpl implements ProductDao {

    Product onlyProduct;
    private Map<String, Product> products = new HashMap<>();

    public ProductDaoStubImpl() {
        onlyProduct = new Product("Bearskin");
        onlyProduct.setCostPerSqFt(new BigDecimal("1.50"));
        onlyProduct.setLaborCostPerSqFt(new BigDecimal("3.00"));

        products.put(onlyProduct.getProductType(), onlyProduct);

    }

    @Override
    public Product getProductInfo(String material) throws DaoException {
        return products.get(material);
    }

}
