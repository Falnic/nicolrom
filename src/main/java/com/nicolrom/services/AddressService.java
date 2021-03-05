package com.nicolrom.services;

import com.nicolrom.entities.Address;

import java.util.List;

public interface AddressService {
    List<Address> getAllAddresses();

    Address createAddress(String newStreet, String newLocality, String newCounty, String district);

    void saveAddress(Address address);

    Address getAddress(String street, String locality, String county);

    List<String> getStreetsByLocality(String locality);

    List<String> getAllLocalities();

    String getDistrictByStreet(String street);

    List<String> getAllDistricts();
}
