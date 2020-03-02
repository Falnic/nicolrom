package com.nicolrom.services.impl;

import com.nicolrom.dao.EmployeeDao;
import com.nicolrom.entities.Employee;
import com.nicolrom.enums.EmployeePositionEnum;
import com.nicolrom.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }


    @Override
    public List<Employee> getEmployeesByPosition(List<EmployeePositionEnum> positionsEnums) {
        return employeeDao.getEmployeesByPosition(positionsEnums);
    }

    @Override
    public Map<EmployeePositionEnum, List<Employee>> getEmployeesByPositionAsMap(List<EmployeePositionEnum> positionsEnums) {
        Map<EmployeePositionEnum, List<Employee>> employeePositionMap = new HashMap<>();

        for (EmployeePositionEnum positionEnum : positionsEnums){
            employeePositionMap.put(positionEnum, new ArrayList<Employee>());
        }

        List<Employee> allEmployeesByGivenPositions = getEmployeesByPosition(positionsEnums);
        for(Employee employee : allEmployeesByGivenPositions){
            if (employeePositionMap.containsKey(employee.getPosition())){
                employeePositionMap.get(employee.getPosition()).add(employee);
            }
        }

        return employeePositionMap;
    }

    @Override
    public List<Employee> getEmployeesByPosition(EmployeePositionEnum positionEnum) {
        return employeeDao.getEmployeesByPosition(positionEnum);
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeDao.getEmployeeById(id);
    }


}
