package com.nicolrom.services;

import com.nicolrom.entities.Team;
import com.nicolrom.entities.TeamDeploy;

public interface TeamDeployService {

    TeamDeploy create(Team team, Integer idEmployee, Integer idMachinery);

    void saveTeamDeploy(TeamDeploy teamDeploy);

    void deleteTeamDeploy(TeamDeploy teamDeploy);

    void updateTeamDeploy(TeamDeploy teamDeploy);
}
