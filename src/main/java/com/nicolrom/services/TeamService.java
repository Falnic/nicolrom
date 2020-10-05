package com.nicolrom.services;

import com.nicolrom.entities.Team;

import java.util.List;

public interface TeamService {

    Team create(List<String> employeesSofer, List<String> employeesMecanic, List<String> employeesNecalificat, String executor);

    Team getTeam(Integer id);

    void saveTeam(Team team);

    void updateTeam(Team team);

    void deleteTeam(Team team);
}
