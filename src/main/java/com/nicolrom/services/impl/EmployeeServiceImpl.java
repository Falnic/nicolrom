package com.nicolrom.services.impl;

import com.nicolrom.dao.EmployeeDao;
import com.nicolrom.entities.Employee;
import com.nicolrom.entities.Hole;
import com.nicolrom.entities.Phase;
import com.nicolrom.enums.EmployeePositionEnum;
import com.nicolrom.enums.PhaseEnum;
import com.nicolrom.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public Map<EmployeePositionEnum, List<Employee>> getEmployeesByPositionAsMap(List<EmployeePositionEnum> positionEnums) {
        Map<EmployeePositionEnum, List<Employee>> employeePositionMap = new HashMap<>();

        for (EmployeePositionEnum positionEnum : positionEnums){
            employeePositionMap.put(positionEnum, new ArrayList<Employee>());
        }

        List<Employee> allEmployeesByGivenPositions = getEmployeesByPosition(positionEnums);
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

    @Override
    public Set<Employee> getEmployeesById(List<Integer> employeesId) {
        Set<Employee> employees = new HashSet<>();
        for (Integer id : employeesId){
            if (id != null){
                employees.add(getEmployeeById(id));
            }
        }
        return employees;
    }

    @Override
    public Map<Phase, List<EmployeePositionEnum>> getEmployeePositionsByPhases(List<Phase> phases) {
        Map<Phase, List<EmployeePositionEnum>> employeePositionsByPhase = new HashMap<>();

        for (Phase phase : phases){
            List<EmployeePositionEnum> positions = new ArrayList<>();
            for (Employee employee : phase.getTeam().getEmployees()){
                if (!positions.contains(employee.getPosition())){
                    positions.add(employee.getPosition());
                }
            }
            employeePositionsByPhase.put(phase, positions);
        }

        return employeePositionsByPhase;
    }

    @Override
    public List<Employee> getHoleEmployeesByPhase(Hole hole, PhaseEnum phaseEnum) {
        for(Phase phase : hole.getPhases()){
            if (phase.getPhaseType() == phaseEnum){
                return new ArrayList<>(phase.getTeam().getEmployees());
            }
        }
        return null;
    }

    @Override
    public List<Employee> getHoleEmployeesByPhase(Hole hole, PhaseEnum phaseEnum, EmployeePositionEnum positionEnum) {
        List<Employee> sortedEmployees = new ArrayList<>();
        List<Employee> employees = getHoleEmployeesByPhase(hole, phaseEnum);
        for (Employee employee : employees){
            if (employee.getPosition().equals(positionEnum)){
                sortedEmployees.add(employee);
            }
        }
        return sortedEmployees;
    }
}
