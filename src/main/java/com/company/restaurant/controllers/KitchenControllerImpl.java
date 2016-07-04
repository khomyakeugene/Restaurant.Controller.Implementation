package com.company.restaurant.controllers;

import com.company.restaurant.controllers.proto.Controller;
import com.company.restaurant.dao.CookedCourseDao;
import com.company.restaurant.dao.CourseIngredientDao;
import com.company.restaurant.model.*;

import java.util.List;
import java.util.Set;

public class KitchenControllerImpl extends Controller implements KitchenController {
    private static final String THERE_IS_NO_INGREDIENT_LIST_FOR_COURSE_PATTERN =
            "There is no ingredient list for course <%s>";
    private static final String IT_IS_NEED_TO_HAVE_INGREDIENT_PATTERN =
            "To prepare %f kg of course <%s> you need to have %f (%s) of ingredient <%s>";
    private static final String THERE_IS_NO_INGREDIENT_IN_WAREHOUSE_MESSAGE =
            "; but there is no such ingredient in warehouse";
    private static final String THERE_IS_ONLY_OF_INGREDIENT_IN_WAREHOUSE_PATTERN =
            "; but there is only %f of such ingredient in warehouse";

    private CookedCourseDao cookedCourseDao;
    private CourseIngredientDao courseIngredientDao;
    private WarehouseController warehouseController;

    public void setCookedCourseDao(CookedCourseDao cookedCourseDao) {
        this.cookedCourseDao = cookedCourseDao;
    }

    public void setCourseIngredientDao(CourseIngredientDao courseIngredientDao) {
        this.courseIngredientDao = courseIngredientDao;
    }

    public void setWarehouseController(WarehouseController warehouseController) {
        this.warehouseController = warehouseController;
    }

    private String needToHaveIngredientMessage(CourseIngredient courseIngredient, Float weight) {
        return String.format(IT_IS_NEED_TO_HAVE_INGREDIENT_PATTERN, weight, courseIngredient.getCourse().getName(),
                weight * courseIngredient.getAmount(), courseIngredient.getPortion().getDescription(),
                courseIngredient.getIngredient().getName());
    }

    @Override
    public CookedCourse addCookedCourse(Course course, Employee employee, Float weight) {
        CookedCourse result = null;

        if (weight != null && weight > 0.0) {
            // Get ingredients for course
            Set<CourseIngredient> courseIngredients = courseIngredientDao.findCourseIngredients(course);
            // Check of the possibility to take ingredients consumption into account
            if (courseIngredients == null || courseIngredients.size() == 0) {
                errorMessage(String.format(THERE_IS_NO_INGREDIENT_LIST_FOR_COURSE_PATTERN, course.getName()));
            } else {
                // Take ingredients consumption into account
                for (CourseIngredient courseIngredient : courseIngredients) {
                    Ingredient ingredient = courseIngredient.getIngredient();
                    Portion portion = courseIngredient.getPortion();
                    Float amount = courseIngredient.getAmount();
                    if (portion != null && amount > 0.0) {
                        // Search ingredient in warehouse
                        Warehouse warehouse = warehouseController.findIngredientInWarehouse(ingredient, portion);
                        if (warehouse == null) {
                            errorMessage(needToHaveIngredientMessage(courseIngredient, weight) +
                                    THERE_IS_NO_INGREDIENT_IN_WAREHOUSE_MESSAGE);
                        } else {
                            // Take ingredient consumption into account
                            amount *= weight;
                            Float warehouseAmount = warehouse.getAmount();
                            if (amount > warehouseAmount) {
                                errorMessage(needToHaveIngredientMessage(courseIngredient, weight) +
                                        String.format(THERE_IS_ONLY_OF_INGREDIENT_IN_WAREHOUSE_PATTERN,
                                                warehouseAmount));
                            } else {
                                // Save ingredient remainder
                                warehouseController.takeIngredientFromWarehouse(ingredient, portion, amount);
                            }
                        }
                    }
                }

                // Save cooked course itself
                result = cookedCourseDao.addCookedCourse(course, employee, weight);
            }
        }

        return result;
    }

    @Override
    public void delCookedCourse(CookedCourse cookedCourse) {
        if (cookedCourse != null) {
            cookedCourseDao.delCookedCourse(cookedCourse);
        }
    }

    @Override
    public List<CookedCourse> findAllCookedCourses() {
        return cookedCourseDao.findAllCookedCourses();
    }

    @Override
    public Set<CourseIngredient> findCourseIngredients(Course course) {
        return courseIngredientDao.findCourseIngredients(course);
    }
}