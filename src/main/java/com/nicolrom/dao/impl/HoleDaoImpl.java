package com.nicolrom.dao.impl;

import com.nicolrom.dao.HoleDao;
import com.nicolrom.entities.Hole;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("holeDao")
@Transactional
public class HoleDaoImpl implements HoleDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Hole> getAllHoles() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Hole as H").list();
    }

    @Override
    public void saveHole(Hole hole) {
        Session session = sessionFactory.getCurrentSession();
        session.save(hole);
    }


}
