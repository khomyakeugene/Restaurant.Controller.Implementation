package com.company.restaurant.controllers;

import com.company.restaurant.dao.IngredientDao;
import com.company.restaurant.dao.PortionDao;
import com.company.restaurant.dao.WarehouseViewDao;
import com.company.restaurant.model.Ingredient;
import com.company.restaurant.model.Portion;
import com.company.restaurant.model.WarehouseView;

import java.util.List;

public class WarehouseController extends BasicTransactionManagerController {
    private WarehouseViewDao warehouseViewDao;
    private IngredientDao ingredientDao;
    private PortionDao portionDao;

    public void setWarehouseViewDao(WarehouseViewDao warehouseViewDao) {
        this.warehouseViewDao = warehouseViewDao;
    }

    public void setIngredientDao(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }

    public void setPortionDao(PortionDao portionDao) {
        this.portionDao = portionDao;
    }

    public void addIngredientToWarehouse(Ingredient ingredient, Portion portion, float amount) {
        warehouseViewDao.addIngredientToWarehouse(ingredient, portion, amount);
    }

    public void takeIngredientFromWarehouse(Ingredient ingredient, Portion portion, float amount) {
        warehouseViewDao.takeIngredientFromWarehouse(ingredient, portion, amount);
    }

    public List<WarehouseView> findIngredientInWarehouseByName(String name) {
        return warehouseViewDao.findIngredientInWarehouseByName(name);
    }

    public List<WarehouseView> findIngredientInWarehouseById(int ingredientId) {
        return warehouseViewDao.findIngredientInWarehouseById(ingredientId);
    }

    public List<WarehouseView> findAllWarehouseIngredients() {
        return warehouseViewDao.findAllWarehouseIngredients();
    }

    public List<WarehouseView> findAllElapsingWarehouseIngredients(float limit) {
        return warehouseViewDao.findAllElapsingWarehouseIngredients(limit);
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