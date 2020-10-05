package com.nicolrom.services.impl;

import com.nicolrom.dao.TeamDao;
import com.nicolrom.entities.Team;
import com.nicolrom.services.EmployeeService;
import com.nicolrom.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamDao teamDao;

    @Autowired
    private EmployeeService employeeService;

    @Override
    public Team create(List<String> employeesSofer, List<String> employeesMecanic, List<String> employeesNecalificat, String executor) {
        Team updatedTeam = new Team();
        List<String> employeesStringArray = new ArrayList<>();

        if ("Nicol Rom".equals(executor)){
            employeesStringArray.addAll(employeesSofer);
            employeesStringArray.addAll(employeesMecanic);
            employeesStringArray.addAll(employeesNecalificat);
        } else {
            employeesStringArray.addAll(employeesSofer);
        }
        updatedTeam.setEmployees(employeeService.getEmployeesById(parseEmployeesStringArray(employeesStringArray)));
        return updatedTeam;
    }

    @Override
    public Team getTeam(Integer id) {
        return teamDao.getTeam(id);
    }

    @Override
    public void saveTeam(Team team) {
        teamDao.saveTeam(team);
    }

    @Override
    public void updateTeam(Team team) {
        teamDao.updateTeam(team);
    }

    @Override
    public void deleteTeam(Team team) {
        teamDao.deleteTeam(team);
    }

    private List<Integer> parseEmployeesStringArray(List<String> employeesStringArray){
        List<Integer> employees = new ArrayList<>();
        for (String employeeId : employeesStringArray) {
            employees.add(Integer.parseInt(employeeId));
        }
        return employees;
    }
}
