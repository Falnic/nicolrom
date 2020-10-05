package com.nicolrom.dao;

import com.nicolrom.entities.Team;

public interface TeamDao {

    void saveTeam(Team team);

    Team getTeam(Integer id);

    void updateTeam(Team team);

    void deleteTeam(Team team);
}
