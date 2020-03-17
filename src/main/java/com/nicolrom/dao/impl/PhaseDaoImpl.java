package com.nicolrom.dao.impl;

import com.nicolrom.dao.PhaseDao;
import com.nicolrom.entities.Phase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
}
