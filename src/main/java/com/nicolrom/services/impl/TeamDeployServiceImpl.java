package com.nicolrom.services.impl;

import com.nicolrom.dao.TeamDeployDao;
import com.nicolrom.entities.Employee;
import com.nicolrom.entities.Machinery;
import com.nicolrom.entities.Team;
import com.nicolrom.entities.TeamDeploy;
import com.nicolrom.services.EmployeeService;
import com.nicolrom.services.MachineryService;
import com.nicolrom.services.TeamDeployService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamDeployServiceImpl implements TeamDeployService {

    @Autowired
    private TeamDeployDao teamDeployDao;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MachineryService machineryService;

    @Override
    public TeamDeploy create(Team team, Integer idEmployee, Integer idMachinery) {
        TeamDeploy teamDeploy = new TeamDeploy();

        teamDeploy.setTeam(team);
        teamDeploy.setEmployee(employeeService.getEmployeeById(idEmployee));
        teamDeploy.setMachinery((idMachinery != null) ?  machineryService.getMachineryById(idMachinery) : null);

        return teamDeploy;
    }

    @Override
    public TeamDeploy create(Team team, Employee employee, Machinery machinery) {
        TeamDeploy teamDeploy = new TeamDeploy();

        teamDeploy.setTeam(team);
        teamDeploy.setEmployee(employee);
        teamDeploy.setMachinery(machinery);

        return teamDeploy;
    }

    @Override
    public void saveTeamDeploy(TeamDeploy teamDeploy) {
        teamDeployDao.saveTeamDeploy(teamDeploy);
    }

    @Override
    public void deleteTeamDeploy(TeamDeploy teamDeploy) {
        teamDeployDao.deleteTeamDeploy(teamDeploy);
    }

    @Override
    public void updateTeamDeploy(TeamDeploy teamDeploy) {
        teamDeployDao.updateTeamDeploy(teamDeploy);
    }
}
