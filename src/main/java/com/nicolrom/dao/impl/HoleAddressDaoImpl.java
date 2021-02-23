package com.nicolrom.dao.impl;

import com.nicolrom.dao.HoleAddressDao;
import com.nicolrom.entities.HoleAddress;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository("holeaddressdao")
@Transactional
public class HoleAddressDaoImpl implements HoleAddressDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(HoleAddress holeAddress) {
        Session session = sessionFactory.getCurrentSession();
        session.save(holeAddress);
    }
}
