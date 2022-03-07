package com.nicolrom.dao.impl;

import com.nicolrom.dao.PaymentSituationDao;
import com.nicolrom.entities.PaymentSituation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository("PaymentSituationDao")
public class PaymentSituationDaoImpl implements PaymentSituationDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(PaymentSituation paymentSituation) {
        Session session = sessionFactory.getCurrentSession();
        session.save(paymentSituation);
    }
}
