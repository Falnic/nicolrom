package com.nicolrom.dao.impl;

import com.nicolrom.dao.VolumeDao;
import com.nicolrom.entities.Volume;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

@Transactional
@Repository("VolumeDao")
public class VolumeDaoImpl implements VolumeDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Volume> getAllVolumesInDateRange(Date startDate, Date endDate, Integer pageNo, Integer pageSize) {
        int startVal = pageNo * pageSize;
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT v FROM Volume v WHERE v.startDate >= :startDate AND v.endDate <= :endDate")
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .setFirstResult(startVal)
                .setMaxResults(pageSize)
                .list();
    }

    @Override
    public List<Volume> getLastVolumes(Integer volumeCount) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT v FROM Volume v ORDER BY v.startDate DESC")
                .setMaxResults(volumeCount)
                .list();
    }


    @Override
    public void save(Volume volume) {
        Session session = sessionFactory.getCurrentSession();
        session.save(volume);
    }

    @Override
    public long countVolumes(Date startDate, Date endDate) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT COUNT (*) from Volume v  WHERE v.startDate >= :startDate AND v.endDate <= :endDate")
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate);
        return (long) query.uniqueResult();
    }
}
