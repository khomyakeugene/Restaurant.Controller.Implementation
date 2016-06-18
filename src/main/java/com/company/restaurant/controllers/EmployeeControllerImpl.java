package com.company.restaurant.controllers;

import com.company.restaurant.dao.EmployeeDao;
import com.company.restaurant.dao.JobPositionDao;
import com.company.restaurant.model.Employee;
import com.company.restaurant.model.JobPosition;

import java.util.List;

public class EmployeeControllerImpl implements EmployeeController {
    private JobPositionDao jobPositionDao;
    private EmployeeDao employeeDao;

    public void setJobPositionDao(JobPositionDao jobPositionDao) {
        this.jobPositionDao = jobPositionDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public JobPosition addJobPosition(String name) {
        return jobPositionDao.addJobPosition(name);
    }

    @Override
    public void delJobPosition(String name) {
        jobPositionDao.delJobPosition(name);
    }

    @Override
    public JobPosition findJobPositionByName(String name) {
        return jobPositionDao.findJobPositionByName(name);
    }

    @Override
    public JobPosition findJobPositionById(int jobPositionId) {
        return jobPositionDao.findJobPositionById(jobPositionId);
    }

    @Override
    public List<JobPosition> findAllJobPositions() {
        return jobPositionDao.findAllJobPositions();
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeDao.addEmployee(employee);
    }

    @Override
    public void delEmployee(Employee employee) {
        employeeDao.delEmployee(employee);
    }

    @Override
    public void delEmployee(int employeeId) {
        employeeDao.delEmployee(employeeId);
    }

    @Override
    public List<Employee> findAllEmployees() {
        return employeeDao.findAllEmployees();
    }

    @Override
    public List<Employee> findEmployeeByFirstName(String firstName) {
        return employeeDao.findEmployeeByFirstName(firstName);
    }

    @Override
    public List<Employee> findEmployeeBySecondName(String lastName) {
        return employeeDao.findEmployeeBySecondName(lastName);
    }

    @Override
    public List<Employee> findEmployeeByFirstAndSecondName(String firstName, String secondName) {
        return employeeDao.findEmployeeByFirstAndSecondName(firstName, secondName);
    }

    @Override
    public Employee findEmployeeById(int id) {
        return employeeDao.findEmployeeById(id);
    }
}