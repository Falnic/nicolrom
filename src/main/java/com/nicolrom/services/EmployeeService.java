package com.nicolrom.services;

import com.nicolrom.entities.Employee;
import com.nicolrom.enums.EmployeePositionEnum;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    List<Employee> getEmployeesByPosition(EmployeePositionEnum positionEnum);

    List<Employee> getEmployeesByPosition(List<EmployeePositionEnum> positionEnums);

    Map<EmployeePositionEnum, List<Employee>> getEmployeesByPositionAsMap(List<EmployeePositionEnum> positionEnums);

    Employee getEmployeeById(Integer id);
}