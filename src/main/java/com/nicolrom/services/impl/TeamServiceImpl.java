package com.nicolrom.services.impl;

import com.nicolrom.dao.TeamDao;
import com.nicolrom.entities.Employee;
import com.nicolrom.entities.Machinery;
import com.nicolrom.entities.Team;
import com.nicolrom.entities.TeamDeploy;
import com.nicolrom.services.EmployeeService;
import com.nicolrom.services.TeamDeployService;
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

    @Autowired
    private TeamDeployService teamDeployService;

    @Override
    public Team create(List<String> employeesSofer, List<String> employeesMecanic, List<String> employeesNecalificat) {
        Team updatedTeam = new Team();
        List<String> employeesStringArray = new ArrayList<>();

        if (employeesSofer != null && !employeesSofer.isEmpty()){
            employeesStringArray.addAll(employeesSofer);
        }
        if (employeesMecanic != null && !employeesMecanic.isEmpty()){
            employeesStringArray.addAll(employeesMecanic);
        }
        if (employeesNecalificat != null && !employeesNecalificat.isEmpty()){
            employeesStringArray.addAll(employeesNecalificat);
        }

        return updatedTeam;
    }

    @Override
    public Team create(List<String> employeesSofer, List<String> employeesMecanic, List<String> employeesNecalificat, List<String> machinariesSofer) {
        Team team = new Team();
        if (employeesSofer != null){
            for (String employeeString : employeesSofer) {
                Employee employee = employeeService.getEmployeeById(Integer.parseInt(employeeString));
                for (Machinery machinery : employee.getMachines()) {
                    if (machinariesSofer.contains(Integer.toString(machinery.getMachineryId()))) {
                        TeamDeploy teamDeploy = teamDeployService.create(team, employee, machinery);
                        team.getTeamDeploys().add(teamDeploy);
                    }
                }
            }
        }

        if (employeesMecanic != null){
            for (String stringEmployee : employeesMecanic){
                TeamDeploy teamDeploy = teamDeployService.create(team, Integer.parseInt(stringEmployee), null);
                team.getTeamDeploys().add(teamDeploy);
            }
        }

        if (employeesNecalificat != null){
            for (String stringEmployee : employeesNecalificat){
                TeamDeploy teamDeploy = teamDeployService.create(team, Integer.parseInt(stringEmployee), null);
                team.getTeamDeploys().add(teamDeploy);
            }
        }

        return team;
    }

    @Override
    public Team getTeam(Integer id) {
        return teamDao.getTeam(id);
    }

    @Override
    public void saveTeam(Team team) {
        teamDao.saveTeam(team);
        for (TeamDeploy teamDeploy : team.getTeamDeploys()){
            teamDeployService.saveTeamDeploy(teamDeploy);
        }
    }

    @Override
    public void updateTeam(Team updatedTeam, Team team) {
        //Remove
        for (TeamDeploy teamDeploy : team.getTeamDeploys()){
            boolean flag = false;
            for (TeamDeploy updatedTeamDeploy : updatedTeam.getTeamDeploys()){
                if ((updatedTeamDeploy.getEmployee().getIdEmployee() == teamDeploy.getEmployee().getIdEmployee())){
                    if ((updatedTeamDeploy.getMachinery() != null) && (teamDeploy.getMachinery() != null)
                            && (updatedTeamDeploy.getMachinery().getMachineryId() != teamDeploy.getMachinery().getMachineryId())){
                        updatedTeamDeploy.setId(teamDeploy.getId());
                        teamDeploy.setMachinery(updatedTeamDeploy.getMachinery());
                        teamDeployService.updateTeamDeploy(teamDeploy);
                        flag = true;
                    } else {
                        updatedTeamDeploy.setId(teamDeploy.getId());
                        teamDeployService.updateTeamDeploy(teamDeploy);
                        flag = true;
                    }
                }
            }
            if (!flag){
                teamDeployService.deleteTeamDeploy(teamDeploy);
            }
        }
        //Put
        for (TeamDeploy updatedTeamDeploy : updatedTeam.getTeamDeploys()){
            boolean flag = true;
            for (TeamDeploy teamDeploy : team.getTeamDeploys()){
                if (teamDeploy.getEmployee().getIdEmployee() == updatedTeamDeploy.getEmployee().getIdEmployee()){
                    flag = false;
                    break;
                }
            }
            if (flag){
                teamDeployService.saveTeamDeploy(updatedTeamDeploy);
            }
        }
    }

    @Override
    public void deleteTeam(Team team) {
        if (team != null){
            for (TeamDeploy teamDeploy : team.getTeamDeploys()){
                teamDeployService.deleteTeamDeploy(teamDeploy);
            }
            teamDao.deleteTeam(team);
        }
    }

    private List<Integer> parseEmployeesStringArray(List<String> employeesStringArray){
        List<Integer> employees = new ArrayList<>();
        for (String employeeId : employeesStringArray) {
            employees.add(Integer.parseInt(employeeId));
        }
        return employees;
    }
}
