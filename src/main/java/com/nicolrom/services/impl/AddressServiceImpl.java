package com.nicolrom.services.impl;

import com.nicolrom.dao.AddressDao;
import com.nicolrom.entities.Address;
import com.nicolrom.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressDao addressDao;

    @Override
    public List<Address> getAllAddresses() {
        return addressDao.getAllAddresses();
    }

    @Override
    public Address getAddressByStreet(String street) {
        return addressDao.getAddressByStreet(street);
    }

    @Override
    public List<String> getStreetsByLocality(String locality) {
        return addressDao.getStreetsByLocality(locality);
    }

    @Override
    public List<String> getAllLocalities() {
        return addressDao.getAllLocalities();
    }

    @Override
    public String getDistrictByStreet(String street) {
        return addressDao.getDistrictByStreet(street);
    }

    @Override
    public List<String> getAllDistricts() {
        return addressDao.getAllDistricts();
    }
}
