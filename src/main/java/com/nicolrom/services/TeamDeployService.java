package com.nicolrom.services;

import com.nicolrom.entities.Employee;
import com.nicolrom.entities.Machinery;
import com.nicolrom.entities.Team;
import com.nicolrom.entities.TeamDeploy;

public interface TeamDeployService {

    TeamDeploy create(Team team, Integer idEmployee, Integer idMachinery);

    TeamDeploy create(Team team, Employee employee, Machinery machinery);

    void saveTeamDeploy(TeamDeploy teamDeploy);

    void deleteTeamDeploy(TeamDeploy teamDeploy);

    void updateTeamDeploy(TeamDeploy teamDeploy);
}
