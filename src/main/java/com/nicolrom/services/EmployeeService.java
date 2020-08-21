package com.nicolrom.services;

import com.nicolrom.entities.Employee;
import com.nicolrom.entities.Phase;
import com.nicolrom.enums.EmployeePositionEnum;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    List<Employee> getEmployeesByPosition(EmployeePositionEnum positionEnum);

    List<Employee> getEmployeesByPosition(List<EmployeePositionEnum> positionEnums);

    Map<EmployeePositionEnum, List<Employee>> getEmployeesByPositionAsMap(List<EmployeePositionEnum> positionEnums);

    Employee getEmployeeById(Integer id);

    Set<Employee> getEmployeesById(List<Integer> employeesId);

    Map<Phase, List<EmployeePositionEnum>> getEmployeePositionsByPhases(List<Phase> phases);
}
