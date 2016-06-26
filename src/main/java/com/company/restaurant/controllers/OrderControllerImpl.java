package com.company.restaurant.controllers;

import com.company.restaurant.dao.OrderDao;
import com.company.restaurant.model.Course;
import com.company.restaurant.model.Order;
import com.company.util.DataIntegrityException;

import java.util.List;

/**
 * Created by Yevhen on 22.05.2016.
 */
public class OrderControllerImpl implements OrderController {
    private static final String IMPOSSIBLE_TO_DELETE_ORDER_PATTERN =
            "It is impossible to delete order in <%s> state (<order_id> = %d)!";
    private static final String IMPOSSIBLE_TO_ADD_COURSE_TO_ORDER_PATTERN =
            "It is impossible to add course to order in <%s> state (<order_id> = %d)!";
    private static final String IMPOSSIBLE_TO_DEL_COURSE_FROM_ORDER_PATTERN =
            "It is impossible to delete course from order in <%s> state (<order_id> = %d)!";

    private OrderDao orderDao;
    private StateGraphRules stateGraphRules;

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void setStateGraphRules(StateGraphRules stateGraphRules) {
        this.stateGraphRules = stateGraphRules;
    }

    private String orderCreationState() {
        return stateGraphRules.creationState(orderDao.orderEntityName());
    }

    private String orderClosedState(Order order) {
        return stateGraphRules.closedState(orderDao.orderEntityName(), (order == null) ? null :
                order.getState().getType());
    }

    private String orderClosedState() {
        return orderClosedState(null);
    }

    private String orderDeletedState(Order order) {
        return stateGraphRules.deletedState(orderDao.orderEntityName(), order.getState().getType());
    }

    private boolean isFillingActionEnabled(Order order) {
        return stateGraphRules.isFillingActionEnabled(orderDao.orderEntityName(), order.getState().getType());
    }

    private void errorMessage(String message) {
        throw new DataIntegrityException(message);
    }

    @Override
    public Order addOrder(Order order) {
        order.getState().setType(orderCreationState());

        return orderDao.addOrder(order);
    }

    @Override
    public void delOrder(Order order) {
        if (orderDeletedState(order) != null) {
            orderDao.delOrder(order);
        } else {
            throw new DataIntegrityException(String.format(
                    IMPOSSIBLE_TO_DELETE_ORDER_PATTERN, order, order.getOrderId()));
        }
    }

    @Override
    public Order findOrderById(int id) {
        return orderDao.findOrderById(id);
    }

    @Override
    public Order closeOrder(Order order) {
        return orderDao.updOrderState(order, orderClosedState(order));
    }

    @Override
    public List<Order> findAllOrders() {
        return orderDao.findAllOrders();
    }

    @Override
    public List<Order> findAllOrders(String stateType) {
        return orderDao.findAllOrders(stateType);
    }

    @Override
    public List<Order> findAllOpenOrders() {
        return findAllOrders(orderCreationState());
    }

    @Override
    public List<Order> findAllClosedOrders() {
        return findAllOrders(orderClosedState());
    }

    @Override
    public String addCourseToOrder(Order order, Course course) {
        String result = null;

        try {
            if (isFillingActionEnabled(order)) {
                orderDao.addCourseToOrder(order, course);
            } else {
                // Perhaps, to raise exception seems to be unnecessary and excessive, but let use such a "mechanism"!
                errorMessage(String.format(
                        IMPOSSIBLE_TO_ADD_COURSE_TO_ORDER_PATTERN, order.getState().getName(), order.getOrderId()));
            }
        } catch (Exception e) {
            result = e.getMessage();
        }

        return result;
    }

    @Override
    public String takeCourseFromOrder(Order order, Course course) {
        String result = null;

        try {
            if (isFillingActionEnabled(order)) {
                orderDao.takeCourseFromOrder(order, course);
            } else {
                errorMessage(String.format(
                        IMPOSSIBLE_TO_DEL_COURSE_FROM_ORDER_PATTERN, order.getState().getName(), order.getOrderId()));
            }
        } catch (Exception e) {
            result = e.getMessage();
        }

        return result;
    }

    @Override
    public List<Course> findAllOrderCourses(Order order) {
        return orderDao.findAllOrderCourses(order);
    }

    @Override
    public List<Order> findOrderByNumber(String orderNumber) {
        return orderDao.findOrderByNumber(orderNumber);
    }

    @Override
    public Course findOrderCourseByCourseId(Order order, int courseId) {
        return orderDao.findOrderCourseByCourseId(order, courseId);
    }

    @Override
    public Order updOrderState(Order order, String stateType) {
        return orderDao.updOrderState(order, stateType);
    }
}
