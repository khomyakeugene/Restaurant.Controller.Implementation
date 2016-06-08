package com.company.restaurant.controllers;

import com.company.restaurant.dao.CookedCourseDao;
import com.company.restaurant.model.CookedCourse;
import com.company.restaurant.model.Course;
import com.company.restaurant.model.Employee;

import java.util.List;

public class KitchenController extends BasicTransactionManagerController {
    private CookedCourseDao cookedCourseDao;

    public void setCookedCourseDao(CookedCourseDao cookedCourseDao) {
        this.cookedCourseDao = cookedCourseDao;
    }

    public void addCookedCourse(Course course, Employee employee, Float weight) {
        cookedCourseDao.addCookedCourse(course, employee, weight);
    }

    public List<CookedCourse> findAllCookedCourses() {
        return cookedCourseDao.findAllCookedCourses();
    }
}