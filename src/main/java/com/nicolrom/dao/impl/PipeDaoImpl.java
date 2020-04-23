package com.nicolrom.dao.impl;

import com.nicolrom.dao.PipeDao;
import com.nicolrom.entities.Pipe;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("pipeDao")
@Transactional
public class PipeDaoImpl implements PipeDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Pipe> getAllPipes() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Pipe as P").list();
    }

    @Override
    public Pipe getPipeByDiameter(String diameter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Pipe as P where P.diameter = :diameter");
        query.setParameter("diameter", diameter);
        return (Pipe) query.getSingleResult();
    }
}
