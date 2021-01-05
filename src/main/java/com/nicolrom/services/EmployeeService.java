package com.nicolrom.services;

import com.nicolrom.entities.Employee;
import com.nicolrom.entities.Hole;
import com.nicolrom.entities.Phase;
import com.nicolrom.entities.Team;
import com.nicolrom.enums.EmployeePositionEnum;
import com.nicolrom.enums.PhaseEnum;

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

    List<Employee> getEmployeesByPositions(Team team, EmployeePositionEnum employeePositionEnum);

    List<Employee> getHoleEmployeesByPhase(Hole hole, PhaseEnum phaseEnum);

    List<Employee> getHoleEmployeesByPhase(Hole hole, PhaseEnum phaseEnum, EmployeePositionEnum positionEnum);

    List<String> parseEmployees(List<String> employeesSofer, List<String> employeesMecanic, List<String> employeesNecalificat);
}
