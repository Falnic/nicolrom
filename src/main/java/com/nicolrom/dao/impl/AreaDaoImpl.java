package com.nicolrom.dao.impl;

import com.nicolrom.dao.AreaDao;
import com.nicolrom.entities.Area;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository("areaDao")
public class AreaDaoImpl implements AreaDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Area getArea(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Area as A where A.idArea = :id");
        query.setParameter("id", id);
        return (Area) query.getSingleResult();
    }

    @Override
    public List<Area> getAllAreas() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Area as A").list();
    }

    @Override
    public List<String> getAllAreaTypes() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT a.type from Area a").list();
    }
}
