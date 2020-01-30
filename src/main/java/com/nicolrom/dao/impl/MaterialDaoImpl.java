package com.nicolrom.dao.impl;

import com.nicolrom.dao.MaterialDao;
import com.nicolrom.entities.Material;
import org.hibernate.SessionFactory;
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
}
