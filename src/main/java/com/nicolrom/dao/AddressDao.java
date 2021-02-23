package com.nicolrom.dao;

import com.nicolrom.entities.Address;

import java.util.List;

public interface AddressDao {

    List<Address> getAllAddresses();

    Address getAddressByStreet(String street);

    List<String> getStreetsByLocality(String locality);

    List<String> getAllLocalities();

    String getDistrictByStreet(String street);

    List<String> getAllDistricts();
}
