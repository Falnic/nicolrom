package com.nicolrom.dao;

import com.nicolrom.entities.Employee;
import com.nicolrom.enums.EmployeePositionEnum;

import java.util.List;

public interface EmployeeDao {
    List<Employee> getAllEmployees();

    List<Employee> getEmployeeByPosition(EmployeePositionEnum positionEnum);

    Employee getEmployeeById(Integer id);
}
