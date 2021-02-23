package com.nicolrom.services.impl;

import com.nicolrom.dao.HoleAddressDao;
import com.nicolrom.entities.Address;
import com.nicolrom.entities.Hole;
import com.nicolrom.entities.HoleAddress;
import com.nicolrom.services.HoleAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HoleAddressServiceImpl implements HoleAddressService {

    @Autowired
    private HoleAddressDao holeAddressDao;

    @Override
    public HoleAddress create(Hole hole, Address address, String streetNr) {
        HoleAddress holeAddress = new HoleAddress();
        holeAddress.setHole(hole);
        holeAddress.setAddress(address);
        holeAddress.setStreetNr(streetNr);
        return holeAddress;
    }

    @Override
    public void save(HoleAddress holeAddress) {
        holeAddressDao.save(holeAddress);
    }
}
