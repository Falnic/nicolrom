package com.nicolrom.dao;

import com.nicolrom.entities.Team;

public interface TeamDao {

    void saveTeam(Team team);

    void updateTeam(Team team);
}
