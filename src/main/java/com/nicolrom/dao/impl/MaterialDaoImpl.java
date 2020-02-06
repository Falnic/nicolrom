package com.nicolrom.dao.impl;

import com.nicolrom.dao.MaterialDao;
import com.nicolrom.entities.Material;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("materialDao")
@Transactional
public class MaterialDaoImpl implements MaterialDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Material> getAllMaterials() {
        return sessionFactory.getCurrentSession().createQuery("FROM Material AS M").list();
    }

    @Override
    public Material getMaterialById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Material as M where M.id = :id");
        query.setParameter("id", id);
        return (Material) query.getSingleResult();
    }
}
