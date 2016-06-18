package com.company.restaurant.controllers;

import com.company.restaurant.dao.IngredientDao;
import com.company.restaurant.dao.PortionDao;
import com.company.restaurant.dao.WarehouseViewDao;
import com.company.restaurant.model.Ingredient;
import com.company.restaurant.model.Portion;
import com.company.restaurant.model.WarehouseView;

import java.util.List;

public class WarehouseControllerImpl implements WarehouseController {
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

    @Override
    public void addIngredientToWarehouse(Ingredient ingredient, Portion portion, float amount) {
        if (amount > 0.0) {
            warehouseViewDao.addIngredientToWarehouse(ingredient, portion, amount);
        }
    }

    @Override
    public void takeIngredientFromWarehouse(Ingredient ingredient, Portion portion, float amount) {
        if (amount > 0.0) {
            warehouseViewDao.takeIngredientFromWarehouse(ingredient, portion, amount);
        }
    }

    @Override
    public List<WarehouseView> findIngredientInWarehouseByName(String name) {
        return warehouseViewDao.findIngredientInWarehouseByName(name);
    }

    @Override
    public List<WarehouseView> findIngredientInWarehouseById(int ingredientId) {
        return warehouseViewDao.findIngredientInWarehouseById(ingredientId);
    }

    @Override
    public List<WarehouseView> findAllWarehouseIngredients() {
        return warehouseViewDao.findAllWarehouseIngredients();
    }

    @Override
    public List<WarehouseView> findAllElapsingWarehouseIngredients(float limit) {
        return warehouseViewDao.findAllElapsingWarehouseIngredients(limit);
    }

    @Override
    public List<Ingredient> findAllIngredients() {
        return ingredientDao.findAllIngredients();
    }

    @Override
    public Ingredient findIngredientById(int ingredientId) {
        return ingredientDao.findIngredientById(ingredientId);
    }

    @Override
    public List<Portion> findAllPortions() {
        return portionDao.findAllPortions();
    }

    @Override
    public Portion findPortionById(int portionId) {
        return portionDao.findPortionById(portionId);
    }
}