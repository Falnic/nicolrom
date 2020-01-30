package com.nicolrom.services;

import com.nicolrom.entities.Employee;
import com.nicolrom.enums.EmployeePositionEnum;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    List<Employee> getEmployeeByPosition(EmployeePositionEnum positionEnum);
}
