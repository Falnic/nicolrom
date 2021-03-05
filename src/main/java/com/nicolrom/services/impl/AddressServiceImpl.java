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
    public Address createAddress(String newStreet, String newLocality, String newCounty, String district) {
        Address address = new Address();

        address.setStreet(newStreet);
        address.setLocality(newLocality);
        address.setCounty(newCounty);
        address.setDistrict(district);

        return address;
    }

    @Override
    public void saveAddress(Address address) {
        addressDao.save(address);
    }

    @Override
    public Address getAddress(String street, String locality, String county) {
        return addressDao.getAddress(street, locality, county);
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
