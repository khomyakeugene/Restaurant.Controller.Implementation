package com.company.restaurant.controllers;

import com.company.restaurant.dao.MenuDao;
import com.company.restaurant.model.Course;
import com.company.restaurant.model.Menu;
import com.company.restaurant.model.MenuCourseView;

import java.util.List;

/**
 * Created by Yevhen on 19.05.2016.
 */
public class MenuControllerImpl extends BasicTransactionManagerController implements MenuController {
    private MenuDao menuDao;

    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    @Override
    public Menu addMenu(String name) {
        return menuDao.addMenu(name);
    }

    @Override
    public void delMenu(String name) {
        menuDao.delMenu(name);
    }

    @Override
    public void delMenu(Menu menu) {
        menuDao.delMenu(menu);
    }

    @Override
    public Menu findMenuByName(String name) {
        return menuDao.findMenuByName(name);
    }

    @Override
    public Menu findMenuById(int menuId) {
        return menuDao.findMenuById(menuId);
    }

    @Override
    public List<Menu> findAllMenus() {
        return menuDao.findAllMenus();
    }

    @Override
    public void addCourseToMenu(Menu menu, Course course) {
        menuDao.addCourseToMenu(menu, course);
    }

    @Override
    public void delCourseFromMenu(Menu menu, Course course) {
        menuDao.delCourseFromMenu(menu, course);
    }

    @Override
    public List<MenuCourseView> findMenuCourses(Menu menu) {
        return menuDao.findMenuCourses(menu);
    }

    @Override
    public MenuCourseView findMenuCourseByCourseId(Menu menu, int courseId) {
        return menuDao.findMenuCourseByCourseId(menu, courseId);
    }
}
