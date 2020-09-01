package com.nicolrom.dao.impl;

import com.nicolrom.dao.TeamDao;
import com.nicolrom.entities.Team;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository("teamDao")
@Transactional
public class TeamDaoImpl implements TeamDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveTeam(Team team) {
        Session session = sessionFactory.getCurrentSession();
        session.save(team);
    }

    @Override
    public void updateTeam(Team team) {
        Session session = sessionFactory.getCurrentSession();
        session.update(team);
    }
}
