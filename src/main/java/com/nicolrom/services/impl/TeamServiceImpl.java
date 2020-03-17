package com.nicolrom.services.impl;

import com.nicolrom.dao.TeamDao;
import com.nicolrom.entities.Team;
import com.nicolrom.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamDao teamDao;

    @Override
    public void saveTeam(Team team) {
        teamDao.saveTeam(team);
    }
}
