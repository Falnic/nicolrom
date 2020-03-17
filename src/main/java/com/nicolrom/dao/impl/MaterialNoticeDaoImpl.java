package com.nicolrom.dao.impl;

import com.nicolrom.dao.MaterialNoticeDao;
import com.nicolrom.entities.MaterialNotice;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Transactional
@Repository("materialNoticeDao")
public class MaterialNoticeDaoImpl implements MaterialNoticeDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveMaterialNotice(MaterialNotice notice) {
        Session session = sessionFactory.getCurrentSession();
        session.save(notice);
    }
}
