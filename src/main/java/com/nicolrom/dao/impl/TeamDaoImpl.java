package com.nicolrom.dao.impl;

import com.nicolrom.dao.TeamDao;
import com.nicolrom.entities.Team;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
    public Team getTeam(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Team as T where T.idTeam = :id");
        query.setParameter("id", id);
        return (Team) query.getSingleResult();
    }

    @Override
    public void updateTeam(Team team) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(team);
    }

    @Override
    public void deleteTeam(Team team) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete Team where idTeam = :id");
        query.setParameter("id", team.getIdTeam());
        query.executeUpdate();
    }
}
