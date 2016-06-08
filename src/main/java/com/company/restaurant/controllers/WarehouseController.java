package com.company.restaurant.controllers;

import com.company.restaurant.dao.IngredientDao;
import com.company.restaurant.dao.PortionDao;
import com.company.restaurant.dao.WarehouseDao;
import com.company.restaurant.model.Ingredient;
import com.company.restaurant.model.Portion;
import com.company.restaurant.model.Warehouse;

import java.util.List;

public class WarehouseController extends BasicTransactionManagerController {
    private WarehouseDao warehouseDao;
    private IngredientDao ingredientDao;
    private PortionDao portionDao;

    public void setWarehouseDao(WarehouseDao warehouseDao) {
        this.warehouseDao = warehouseDao;
    }

    public void setIngredientDao(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }

    public void setPortionDao(PortionDao portionDao) {
        this.portionDao = portionDao;
    }

    public void addIngredientToWarehouse(Ingredient ingredient, Portion portion, float amount) {
        warehouseDao.addIngredientToWarehouse(ingredient, portion, amount);
    }

    public void takeIngredientFromWarehouse(Ingredient ingredient, Portion portion, float amount) {
        warehouseDao.takeIngredientFromWarehouse(ingredient, portion, amount);
    }

    public List<Warehouse> findIngredientInWarehouseByName(String name) {
        return warehouseDao.findIngredientInWarehouseByName(name);
    }

    public List<Warehouse> findIngredientInWarehouseById(int ingredientId) {
        return warehouseDao.findIngredientInWarehouseById(ingredientId);
    }

    public List<Warehouse> findAllWarehouseIngredients() {
        return warehouseDao.findAllWarehouseIngredients();
    }

    public List<Warehouse> findAllElapsingWarehouseIngredients(float limit) {
        return warehouseDao.findAllElapsingWarehouseIngredients(limit);
    }

    public List<Ingredient> findAllIngredients() {
        return ingredientDao.findAllIngredients();
    }

    public Ingredient findIngredientById(int ingredientId) {
        return ingredientDao.findIngredientById(ingredientId);
    }

    public List<Portion> findAllPortions() {
        return portionDao.findAllPortions();
    }

    public Portion findPortionById(int portionId) {
        return portionDao.findPortionById(portionId);
    }
}