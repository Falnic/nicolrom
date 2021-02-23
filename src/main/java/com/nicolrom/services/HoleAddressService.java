package com.nicolrom.services;

import com.nicolrom.entities.Address;
import com.nicolrom.entities.Hole;
import com.nicolrom.entities.HoleAddress;

public interface HoleAddressService {

    HoleAddress create(Hole hole, Address address, String streetNr);

    void save(HoleAddress holeAddress);
}
