package com.company.restaurant.controllers;

import com.company.restaurant.dao.EmployeeDao;
import com.company.restaurant.dao.JobPositionDao;
import com.company.restaurant.model.Employee;
import com.company.restaurant.model.JobPosition;

import java.util.List;

public class EmployeeController extends BasicTransactionManagerController   {
    private JobPositionDao jobPositionDao;
    private EmployeeDao employeeDao;

    public void setJobPositionDao(JobPositionDao jobPositionDao) {
        this.jobPositionDao = jobPositionDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public JobPosition addJobPosition(String name) {
        return jobPositionDao.addJobPosition(name);
    }

    public String delJobPosition(String name) {
        return jobPositionDao.delJobPosition(name);
    }

    public JobPosition findJobPositionByName(String name) {
        return jobPositionDao.findJobPositionByName(name);
    }

    public JobPosition findJobPositionById(int jobPositionId) {
        return jobPositionDao.findJobPositionById(jobPositionId);
    }

    public List<JobPosition> findAllJobPositions() {
        return jobPositionDao.findAllJobPositions();
    }

    public Employee addEmployee(Employee employee) {
        return employeeDao.addEmployee(employee);
    }

    public String delEmployee(Employee employee) {
        return employeeDao.delEmployee(employee);
    }

    public String delEmployee(int employeeId) {
        return employeeDao.delEmployee(employeeId);
    }

    public List<Employee> findAllEmployees() {
        return employeeDao.findAllEmployees();
    }

    public List<Employee> findEmployeeByFirstName(String firstName) {
        return employeeDao.findEmployeeByFirstName(firstName);
    }

    public List<Employee> findEmployeeBySecondName(String lastName) {
        return employeeDao.findEmployeeBySecondName(lastName);
    }

    public List<Employee> findEmployeeByFirstAndSecondName(String firstName, String secondName) {
        return employeeDao.findEmployeeByFirstAndSecondName(firstName, secondName);
    }

    public Employee findEmployeeById(int id) {
        return employeeDao.findEmployeeById(id);
    }
}