/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespring.dao;

import com.sg.vendingmachinespring.model.Item;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author apitz_000
 */
public class VendingMachineDaoDbImpl implements VendingMachineDao {

    private static final String SQL_SELECT_ALL_ITEMS
            = "select * from Items";
    private static final String SQL_SELECT_ITEM_BY_ID
            = "select * from Items where itemID = ?";
    private static final String SQL_UPDATE_ITEM_INVENTORY
            = "update Items set itemInventory = ? "
            + "where itemID = ?";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Item> listItems() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ITEMS,
                new ItemMapper());
    }

    @Override
    public Item getItem(String id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ITEM_BY_ID,
                    new ItemMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void changeInventory(Item item) {
        item.setInventory(item.getInventory() -1);
        jdbcTemplate.update(SQL_UPDATE_ITEM_INVENTORY,
                item.getInventory(),
                item.getId());
    }

    private static final class ItemMapper implements RowMapper<Item> {

        public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
            Item item = new Item();
            item.setId(rs.getString("itemID"));
            item.setName(rs.getString("itemName"));
            item.setPrice(rs.getString("itemPrice"));
            item.setInventory(rs.getInt("itemInventory"));
            return item;
        }
    }
}
