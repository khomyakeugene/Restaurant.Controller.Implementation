package com.company.restaurant.controllers;

import com.company.restaurant.dao.CookedCourseViewDao;
import com.company.restaurant.model.CookedCourseView;
import com.company.restaurant.model.Course;
import com.company.restaurant.model.Employee;

import java.util.List;

public class KitchenControllerImpl extends BasicTransactionManagerController implements KitchenController {
    private CookedCourseViewDao cookedCourseViewDao;

    public void setCookedCourseViewDao(CookedCourseViewDao cookedCourseViewDao) {
        this.cookedCourseViewDao = cookedCourseViewDao;
    }

    @Override
    public CookedCourseView addCookedCourse(Course course, Employee employee, Float weight) {
        return cookedCourseViewDao.addCookedCourse(course, employee, weight);
    }

    @Override
    public void delCookedCourse(CookedCourseView cookedCourseView) {
        cookedCourseViewDao.delCookedCourse(cookedCourseView);
    }

    @Override
    public List<CookedCourseView> findAllCookedCourses() {
        return cookedCourseViewDao.findAllCookedCourses();
    }
}