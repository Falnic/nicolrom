package com.nicolrom.dao.impl;

import com.nicolrom.dao.AddressDao;
import com.nicolrom.entities.Address;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("addressDao")
@Transactional
public class AddressDaoImpl implements AddressDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Address> getAllAddresses() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT a FROM Address a", Address.class).getResultList();
    }

    @Override
    public Address getAddressByStreet(String street) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Address as A where A.street = :street");
        query.setParameter("street", street);
        return (Address) query.getSingleResult();
    }

    @Override
    public List<String> getAllLocalities() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT distinct a.locality FROM Address a", String.class).getResultList();
    }

    @Override
    public List<String> getStreetsByLocality(String locality) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT a.street FROM Address a where a.locality = :locality", String.class);
        query.setParameter("locality", locality);
        return query.getResultList();
    }

    @Override
    public String getDistrictByStreet(String street) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT a.district FROM Address a where a.street = :street", String.class);
        query.setParameter("street", street);
        return (String) query.getSingleResult();
    }

    @Override
    public List<String> getAllDistricts() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT distinct a.district FROM Address a", String.class);
        return query.getResultList();
    }
}
