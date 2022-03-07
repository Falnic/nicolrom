package com.nicolrom.dao.impl;

import com.nicolrom.dao.VolumeDao;
import com.nicolrom.entities.Volume;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository("VolumeDao")
public class VolumeDaoImpl implements VolumeDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Volume volume) {
        Session session = sessionFactory.getCurrentSession();
        session.save(volume);
    }
}
