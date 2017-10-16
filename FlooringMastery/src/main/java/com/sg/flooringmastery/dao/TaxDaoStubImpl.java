/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author apitz_000
 */
public class TaxDaoStubImpl implements TaxDao {

    Tax onlyState;
    private Map<String, Tax> stateTax = new HashMap<>();

    public TaxDaoStubImpl() {
        onlyState = new Tax();
        onlyState.setStateName("NM");
        onlyState.setTaxRate(new BigDecimal("6.50"));

        stateTax.put(onlyState.getStateName(), onlyState );

    }

    @Override
    public Tax getStateTax(String stateName) throws DaoException {
        return stateTax.get(stateName);

    }

}
