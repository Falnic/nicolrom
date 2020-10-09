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
    public void updateHole(Hole hole) {
        Session session = sessionFactory.getCurrentSession();
        session.update(hole);
    }

    @Override
    public void deleteHole(Hole hole) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete Hole where holeId = :id");
        query.setParameter("id", hole.getHoleId());
        query.executeUpdate();
    }

    @Override
    public List<Hole> getAllHoles(Integer pageNo, Integer pageSize) {
        Integer startValue = pageNo * pageSize;
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("FROM Hole as H");
        query.setFirstResult(startValue);
        query.setMaxResults(pageSize);
        return query.list();
    }

    @Override
    public List<Hole> getHolesOrderedByDate(Integer pageNo, Integer pageSize) {
        Integer startValue = pageNo * pageSize;
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("FROM Hole as H order by H.date DESC");
        query.setFirstResult(startValue);
        query.setMaxResults(pageSize);
        return query.list();
    }

    @Override
    public List<Hole> getHolesOrderedByAddress(Integer pageNo, Integer pageSize) {
        Integer startValue = pageNo * pageSize;
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("FROM Hole as H order by H.street ASC");
        query.setFirstResult(startValue);
        query.setMaxResults(pageSize);
        return query.list();
    }

    @Override
    public List<Hole> findHolesByStreet(String street) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Hole as H where H.street = :street");
        query.setParameter("street", street);
        return query.list();
    }

    @Override
    public List<Hole> getDuplicates(Hole hole) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Hole as H where H.date = :holeDate " +
                "and lower(H.street) = lower(:street) and lower(H.streetNr) = lower(:streetNr)");
        query.setParameter("holeDate", hole.getDate());
        query.setParameter("street", hole.getStreet());
        query.setParameter("streetNr", hole.getStreetNr());
        return query.getResultList();
    }

    @Override
    public double countHoles() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT COUNT (H.holeId) from Hole H");
        return (long) query.uniqueResult();
    }
}
