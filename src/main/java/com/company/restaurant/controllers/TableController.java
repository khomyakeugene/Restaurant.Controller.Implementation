package com.company.restaurant.controllers;

import com.company.restaurant.dao.TableDao;
import com.company.restaurant.model.Table;

import java.util.List;

public class TableController extends BasicTransactionManagerController {
    private TableDao tableDao;

    public void setTableDao(TableDao tableDao) {
        this.tableDao = tableDao;
    }

    public Table addTable(Table table) {
        return tableDao.addTable(table);
    }

    public void delTable(Table table) {
        tableDao.delTable(table);
    }

    public Table findTableById(int tableId) {
        return tableDao.findTableById(tableId);
    }

    public Table findTableByNumber(int number) {
        return tableDao.findTableByNumber(number);
    }

    public List<Table> findAllTables() {
        return tableDao.findAllTables();
    }
}