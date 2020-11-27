package com.nicolrom.dao.impl;

import com.nicolrom.dao.MaterialNoticeDao;
import com.nicolrom.entities.MaterialNotice;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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

    @Override
    public void updateMaterialNotice(MaterialNotice notice) {
        Session session = sessionFactory.getCurrentSession();
        session.update(notice);
    }

    @Override
    public void deleteMaterialNotice(MaterialNotice notice) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete MaterialNotice where id = :id");
        query.setParameter("id", notice.getId());
        query.executeUpdate();
    }
}
