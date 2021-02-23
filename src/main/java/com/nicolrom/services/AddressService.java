package com.nicolrom.services;

import com.nicolrom.entities.Address;

import java.util.List;

public interface AddressService {
    List<Address> getAllAddresses();

    Address getAddressByStreet(String street);

    List<String> getStreetsByLocality(String locality);

    List<String> getAllLocalities();

    String getDistrictByStreet(String street);

    List<String> getAllDistricts();
}
