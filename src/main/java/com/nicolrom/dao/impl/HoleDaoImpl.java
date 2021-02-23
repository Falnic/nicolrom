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
        session.delete(hole);
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

        Query query = session.createQuery("FROM Hole as H order by H.date");
        query.setFirstResult(startValue);
        query.setMaxResults(pageSize);
        return query.list();
    }

    @Override
    public List<Hole> getHolesOrderedByAddress(Integer pageNo, Integer pageSize) {
        Integer startValue = pageNo * pageSize;
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("SELECT h FROM Hole h JOIN h.holeAddress ha JOIN ha.address a order by a.street ASC");
        query.setFirstResult(startValue);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public List<Hole> getHolesByDistricts(String[] districts) {
        Session session = sessionFactory.getCurrentSession();
        StringBuilder queryString = new StringBuilder("SELECT h FROM Hole h JOIN h.holeAddress ha JOIN ha.address a where");
        for (int i = 0; i < districts.length; i++) {
            if (i + 1 != districts.length) {
                queryString.append(" a.district = :district").append(i).append(" OR");
            } else {
                queryString.append(" a.district = :district").append(i);
            }
        }
        Query query = session.createQuery(queryString.toString());
        for (int i = 0; i < districts.length; i++) {
            String queryParam = "district" + i;
            query.setParameter(queryParam, districts[i]);
        }
        return query.getResultList();
    }

    @Override
    public List<String> getHoleDistricts() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT distinct a.district FROM Hole h JOIN h.holeAddress ha JOIN ha.address a", String.class);
        return query.getResultList();
    }

    @Override
    public List<Hole> searchHolesByStreet(String street) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT h FROM Hole h JOIN h.holeAddress ha JOIN ha.address a where a.street like :street", Hole.class);
        query.setParameter("street", "%" + street + "%");
        return query.getResultList();
    }

    @Override
    public List<Hole> getHolesAtSameAddres(Hole hole) {
        //TODO: REMOVE
//        Session session = sessionFactory.getCurrentSession();
//        Query query = session.createQuery("FROM Address as A where A.street = :street" +
//                " and lower(H.streetNr) = lower(:streetNr)");
//        query.setParameter("street", hole.getStreet());
//        query.setParameter("streetNr", hole.getStreetNr());
        return null;
    }

    @Override
    public double countHoles() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT COUNT (H.holeId) from Hole H");
        return (long) query.uniqueResult();
    }

    @Override
    public double countHoles(String searchValue) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT count (h) FROM Hole h JOIN h.holeAddress ha JOIN ha.address a where a.street like :street", Long.class);
        query.setParameter("street", "%" + searchValue + "%");
        return (long) query.uniqueResult();
    }

    @Override
    public double countHoles(String[] districts) {
        Session session = sessionFactory.getCurrentSession();
        StringBuilder queryString = new StringBuilder("SELECT count (h) FROM Hole h JOIN h.holeAddress ha JOIN ha.address a where ");
        for (int i = 0; i < districts.length; i++) {
            if (i + 1 != districts.length) {
                queryString.append(" a.district = :district").append(i).append(" OR");
            } else {
                queryString.append(" a.district = :district").append(i);
            }
        }
        Query query = session.createQuery(queryString.toString());
        for (int i = 0; i < districts.length; i++) {
            String queryParam = "district" + i;
            query.setParameter(queryParam, districts[i]);
        }
        return (long) query.uniqueResult();
    }
}
