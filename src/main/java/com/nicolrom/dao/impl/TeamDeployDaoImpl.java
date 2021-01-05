package com.nicolrom.dao.impl;

import com.nicolrom.dao.TeamDeployDao;
import com.nicolrom.entities.TeamDeploy;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository("teamDeployDao")
@Transactional
public class TeamDeployDaoImpl implements TeamDeployDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveTeamDeploy(TeamDeploy teamDeploy) {
        Session session = sessionFactory.getCurrentSession();
        session.save(teamDeploy);
    }

    @Override
    public void deleteTeamDeploy(TeamDeploy teamDeploy) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(teamDeploy);
    }

    @Override
    public void updateTeamDeploy(TeamDeploy teamDeploy) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(teamDeploy);
    }
}
