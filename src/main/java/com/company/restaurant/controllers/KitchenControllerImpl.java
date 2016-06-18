package com.company.restaurant.controllers;

import com.company.restaurant.dao.CookedCourseViewDao;
import com.company.restaurant.model.CookedCourseView;
import com.company.restaurant.model.Course;
import com.company.restaurant.model.Employee;

import java.util.List;

public class KitchenControllerImpl implements KitchenController {
    private CookedCourseViewDao cookedCourseViewDao;

    public void setCookedCourseViewDao(CookedCourseViewDao cookedCourseViewDao) {
        this.cookedCourseViewDao = cookedCourseViewDao;
    }

    @Override
    public CookedCourseView addCookedCourse(Course course, Employee employee, Float weight) {
        CookedCourseView result = null;

        if (weight != null && weight > 0.0) {
            result = cookedCourseViewDao.addCookedCourse(course, employee, weight);
        }

        return result;
    }

    @Override
    public void delCookedCourse(CookedCourseView cookedCourseView) {
        if (cookedCourseView != null) {
            cookedCourseViewDao.delCookedCourse(cookedCourseView);
        }
    }

    @Override
    public List<CookedCourseView> findAllCookedCourses() {
        return cookedCourseViewDao.findAllCookedCourses();
    }
}