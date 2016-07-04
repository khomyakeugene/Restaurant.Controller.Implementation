package com.company.restaurant.controllers;

import com.company.restaurant.dao.CookedCourseDao;
import com.company.restaurant.dao.CourseIngredientDao;
import com.company.restaurant.model.CookedCourse;
import com.company.restaurant.model.Course;
import com.company.restaurant.model.CourseIngredient;
import com.company.restaurant.model.Employee;

import java.util.List;
import java.util.Set;

public class KitchenControllerImpl implements KitchenController {
    private CookedCourseDao cookedCourseDao;
    private CourseIngredientDao courseIngredientDao;

    public void setCookedCourseDao(CookedCourseDao cookedCourseDao) {
        this.cookedCourseDao = cookedCourseDao;
    }

    public void setCourseIngredientDao(CourseIngredientDao courseIngredientDao) {
        this.courseIngredientDao = courseIngredientDao;
    }

    @Override
    public CookedCourse addCookedCourse(Course course, Employee employee, Float weight) {
        CookedCourse result = null;

        if (weight != null && weight > 0.0) {
            result = cookedCourseDao.addCookedCourse(course, employee, weight);
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