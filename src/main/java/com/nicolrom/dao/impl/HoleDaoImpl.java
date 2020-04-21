package com.nicolrom.dao.impl;

import com.nicolrom.dao.HoleDao;
import com.nicolrom.entities.Hole;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
    public Hole getHoleById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Hole as H where H.holeId = :id");
        query.setParameter("id", id);
        return (Hole) query.getSingleResult();
    }

    @Override
    public void saveHole(Hole hole) {
        Session session = sessionFactory.getCurrentSession();
        session.save(hole);
    }

    @Override
    public List<Hole> getAllHoles(Integer pageNo, Integer pageSize, String sortBy) {
        Integer startValue = pageNo * pageSize;
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("FROM Hole as H");
        query.setFirstResult(startValue);
        query.setMaxResults(pageSize);
        return query.list();
    }

    @Override
    public double countHoles() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT COUNT (H.holeId) from Hole H");
        return (long) query.uniqueResult();
    }
}
