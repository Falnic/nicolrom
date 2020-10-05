package com.nicolrom.dao.impl;

import com.nicolrom.dao.PhaseDao;
import com.nicolrom.entities.Phase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository("phaseDao")
@Transactional
public class PhaseDaoImpl implements PhaseDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void savePhase(Phase phase) {
        Session session = sessionFactory.getCurrentSession();
        session.save(phase);
    }

    @Override
    public Phase getPhase(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Phase as P where P.phaseId = :id");
        query.setParameter("id", id);
        return (Phase) query.getSingleResult();
    }

    @Override
    public void updatePhase(Phase phase) {
        Session session = sessionFactory.getCurrentSession();
        session.update(phase);
    }

    @Override
    public void deletePhase(Phase phase) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete Phase where phaseId = :id");
        query.setParameter("id", phase.getPhaseId());
        query.executeUpdate();
    }
}
