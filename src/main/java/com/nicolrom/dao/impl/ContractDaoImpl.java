package com.nicolrom.dao.impl;

import com.nicolrom.dao.ContractDao;
import com.nicolrom.entities.Contract;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository("contractDao")
public class ContractDaoImpl implements ContractDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Contract> getAllContracts() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT c FROM Contract as c", Contract.class).getResultList();
    }
}
