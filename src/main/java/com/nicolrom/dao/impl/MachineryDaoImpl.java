package com.nicolrom.dao.impl;

import com.nicolrom.dao.MachineryDao;
import com.nicolrom.entities.Machinery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository("machineryDao")
@Transactional
public class MachineryDaoImpl implements MachineryDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Machinery getMachineryById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Machinery as M where M.id = :id");
        query.setParameter("id", id);
        return (Machinery) query.getSingleResult();
    }
}
