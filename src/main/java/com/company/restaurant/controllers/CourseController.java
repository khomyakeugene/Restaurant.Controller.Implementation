package com.company.restaurant.controllers;

import com.company.restaurant.dao.CourseCategoryDao;
import com.company.restaurant.dao.CourseDao;
import com.company.restaurant.model.Course;
import com.company.restaurant.model.CourseCategory;

import java.util.List;

public class CourseController extends BasicTransactionManagerController {
    private CourseCategoryDao courseCategoryDao;
    private CourseDao courseDao;

    public void setCourseCategoryDao(CourseCategoryDao courseCategoryDao) {
        this.courseCategoryDao = courseCategoryDao;
    }

    public void setCourseDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    public CourseCategory addCourseCategory(String name) {
        return courseCategoryDao.addCourseCategory(name);
    }

    public String delCourseCategory(String name) {
        return courseCategoryDao.delCourseCategory(name);
    }

    public CourseCategory findCourseCategoryByName(String name) {
        return courseCategoryDao.findCourseCategoryByName(name);
    }

    public CourseCategory findCourseCategoryById(int CourseCategoryId) {
        return courseCategoryDao.findCourseCategoryById(CourseCategoryId);
    }

    public List<CourseCategory> findAllCourseCategories() {
        return courseCategoryDao.findAllCourseCategories();
    }

    public Course addCourse(Course course) {
        return courseDao.addCourse(course);
    }

    public String delCourse(Course course) {
        return courseDao.delCourse(course);
    }

    public String delCourse(String name) {
        return courseDao.delCourse(name);
    }

    public Course findCourseByName(String name) {
        return courseDao.findCourseByName(name);
    }

    public Course findCourseById(int courseId) {
        return courseDao.findCourseById(courseId);
    }

    public List<Course> findAllCourses() {
        return courseDao.findAllCourses();
    }
}