package com.company.restaurant.controllers;

import com.company.restaurant.dao.CookedCourseViewDao;
import com.company.restaurant.model.CookedCourseView;
import com.company.restaurant.model.Course;
import com.company.restaurant.model.Employee;

import java.util.List;

public class KitchenController extends BasicTransactionManagerController {
    private CookedCourseViewDao cookedCourseViewDao;

    public void setCookedCourseViewDao(CookedCourseViewDao cookedCourseViewDao) {
        this.cookedCourseViewDao = cookedCourseViewDao;
    }

    public CookedCourseView addCookedCourse(Course course, Employee employee, Float weight) {
        return cookedCourseViewDao.addCookedCourse(course, employee, weight);
    }

    public String delCookedCourse(CookedCourseView cookedCourseView) {
        return cookedCourseViewDao.delCookedCourse(cookedCourseView);
    }

    public List<CookedCourseView> findAllCookedCourses() {
        return cookedCourseViewDao.findAllCookedCourses();
    }
}