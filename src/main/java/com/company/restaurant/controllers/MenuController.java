package com.company.restaurant.controllers;

import com.company.restaurant.dao.MenuDao;
import com.company.restaurant.model.Course;
import com.company.restaurant.model.Menu;
import com.company.restaurant.model.MenuCourseView;

import java.util.List;

/**
 * Created by Yevhen on 19.05.2016.
 */
public class MenuController extends BasicTransactionManagerController {
    private MenuDao menuDao;

    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    public Menu addMenu(String name) {
        return menuDao.addMenu(name);
    }

    public String delMenu(String name) {
        return menuDao.delMenu(name);
    }

    public String delMenu(Menu menu) {
        return menuDao.delMenu(menu);
    }

    public Menu findMenuByName(String name) {
        return menuDao.findMenuByName(name);
    }

    public Menu findMenuById(int menuId) {
        return menuDao.findMenuById(menuId);
    }

    public List<Menu> findAllMenus() {
        return menuDao.findAllMenus();
    }

    public void addCourseToMenu(Menu menu, Course course) {
        menuDao.addCourseToMenu(menu, course);
    }

    public void delCourseFromMenu(Menu menu, Course course) {
        menuDao.delCourseFromMenu(menu, course);
    }

    public List<MenuCourseView> findMenuCourses(Menu menu) {
        return menuDao.findMenuCourses(menu);
    }

    public MenuCourseView findMenuCourseByCourseId(Menu menu, int courseId) {
        return menuDao.findMenuCourseByCourseId(menu, courseId);
    }
}
