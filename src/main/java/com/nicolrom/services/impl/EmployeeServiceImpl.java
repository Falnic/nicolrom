package com.nicolrom.services.impl;

import com.nicolrom.dao.EmployeeDao;
import com.nicolrom.entities.Employee;
import com.nicolrom.enums.EmployeePositionEnum;
import com.nicolrom.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    @Override
    public List<Employee> getEmployeeByPosition(EmployeePositionEnum positionEnum) {
        return employeeDao.getEmployeeByPosition(positionEnum);
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeDao.getEmployeeById(id);
    }


}
