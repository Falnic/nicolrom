package com.nicolrom.dao;

import com.nicolrom.entities.TeamDeploy;

public interface TeamDeployDao {

    void saveTeamDeploy(TeamDeploy teamDeploy);

    void deleteTeamDeploy(TeamDeploy teamDeploy);

    void updateTeamDeploy(TeamDeploy teamDeploy);
}
