package com.company.restaurant.controllers;

import com.company.restaurant.dao.OrderCourseViewDao;
import com.company.restaurant.dao.OrderViewDao;
import com.company.restaurant.model.Course;
import com.company.restaurant.model.OrderCourseView;
import com.company.restaurant.model.OrderView;
import com.company.util.DataIntegrityException;

import java.util.List;

/**
 * Created by Yevhen on 22.05.2016.
 */
public class OrderController extends BasicTransactionManagerController  {
    private static final String IMPOSSIBLE_TO_DELETE_ORDER_PATTERN =
            "It is impossible to delete order in <%s> state (<order_id> = %d)!";
    private static final String IMPOSSIBLE_TO_ADD_COURSE_TO_ORDER_PATTERN =
            "It is impossible to add course to order in <%s> state (<order_id> = %d)!";
    private static final String IMPOSSIBLE_TO_DEL_COURSE_FROM_ORDER_PATTERN =
            "It is impossible to delete course from order in <%s> state (<order_id> = %d)!";

    private OrderViewDao orderViewDao;
    private StateGraphRules stateGraphRules;
    private OrderCourseViewDao orderCourseViewDao;

    public void setOrderViewDao(OrderViewDao orderViewDao) {
        this.orderViewDao = orderViewDao;
    }

    public void setStateGraphRules(StateGraphRules stateGraphRules) {
        this.stateGraphRules = stateGraphRules;
    }

    public void setOrderCourseViewDao(OrderCourseViewDao orderCourseViewDao) {
        this.orderCourseViewDao = orderCourseViewDao;
    }

    private String orderCreationState() {
        return stateGraphRules.creationState(orderViewDao.orderEntityName());
    }

    private String orderClosedState(OrderView orderView) {
        return stateGraphRules.closedState(orderViewDao.orderEntityName(), (orderView == null) ? null : orderView.getStateType());
    }

    private String orderClosedState() {
        return orderClosedState(null);
    }

    private String orderDeletedState(OrderView orderView) {
        return stateGraphRules.deletedState(orderViewDao.orderEntityName(), orderView.getStateType());
    }

    private boolean isFillingActionEnabled(OrderView orderView) {
        return stateGraphRules.isFillingActionEnabled(orderViewDao.orderEntityName(), orderView.getStateType());
    }

    private void errorMessage(String message) {
        throw new DataIntegrityException(message);
    }

    public OrderView addOrder(OrderView orderView) {
        orderView.setStateType(orderCreationState());

        return orderViewDao.addOrder(orderView);
    }

    public void delOrder(OrderView orderView) {
        if (orderDeletedState(orderView) != null) {
            orderViewDao.delOrder(orderView);
        } else {
            throw new DataIntegrityException(String.format(
                    IMPOSSIBLE_TO_DELETE_ORDER_PATTERN, orderView.getStateTypeName(), orderView.getOrderId()));
        }
    }

    public OrderView findOrderById(int id) {
        return orderViewDao.findOrderById(id);
    }

    public OrderView closeOrder(OrderView orderView) {
        return orderViewDao.updOrderState(orderView, orderClosedState(orderView));
    }

    public List<OrderView> findAllOrders() {
        return orderViewDao.findAllOrders();
    }

    public List<OrderView> findAllOrders(String stateType) {
        return orderViewDao.findAllOrders(stateType);
    }

    public List<OrderView> findAllOpenOrders() {
        return findAllOrders(orderCreationState());
    }

    public List<OrderView> findAllClosedOrders() {
        return findAllOrders(orderClosedState());
    }

    public String addCourseToOrder(OrderView orderView, Course course, int quantity) {
        String result = null;

        try {
            if (isFillingActionEnabled(orderView)) {
                orderCourseViewDao.addCourseToOrder(orderView, course, quantity);
            } else {
                // Perhaps, to raise exception seems to be unnecessary and excessive, but let use such a "mechanism"!
                errorMessage(String.format(
                        IMPOSSIBLE_TO_ADD_COURSE_TO_ORDER_PATTERN, orderView.getStateTypeName(), orderView.getOrderId()));
            }
        } catch (Exception e) {
            result = e.getMessage();
        }

        return result;
    }

    public String addCourseToOrder(OrderView orderView, Course course) {
        return addCourseToOrder(orderView, course, 1);
    }

    public String takeCourseFromOrder(OrderView orderView, Course course, int quantity) {
        String result = null;

        try {
            if (isFillingActionEnabled(orderView)) {
                orderCourseViewDao.takeCourseFromOrder(orderView, course, quantity);
            } else {
                errorMessage(String.format(
                        IMPOSSIBLE_TO_DEL_COURSE_FROM_ORDER_PATTERN, orderView.getStateTypeName(), orderView.getOrderId()));
            }
        } catch (Exception e) {
            result = e.getMessage();
        }

        return result;
    }

    public String takeCourseFromOrder(OrderView orderView, Course course) {
        return takeCourseFromOrder(orderView, course, 1);
    }

    public List<OrderCourseView> findAllOrderCourses(OrderView orderView) {
        return orderCourseViewDao.findAllOrderCourses(orderView);
    }

    public List<OrderView> findOrderByNumber(String orderNumber) {
        return orderViewDao.findOrderByNumber(orderNumber);
    }

    public OrderCourseView findOrderCourseByCourseId(OrderView orderView, int courseId) {
        return orderCourseViewDao.findOrderCourseByCourseId(orderView, courseId);
    }

    public OrderView updOrderState(OrderView orderView, String stateType) {
        return orderViewDao.updOrderState(orderView, stateType);
    }
}
