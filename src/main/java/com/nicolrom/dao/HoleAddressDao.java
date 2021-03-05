package com.nicolrom.dao;

import com.nicolrom.entities.HoleAddress;

public interface HoleAddressDao {

    void save (HoleAddress holeAddress);

    void update (HoleAddress holeAddress);

    void delete (HoleAddress holeAddress);
}
