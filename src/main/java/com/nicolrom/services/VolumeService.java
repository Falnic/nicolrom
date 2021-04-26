package com.nicolrom.services;

import com.nicolrom.entities.Contract;
import com.nicolrom.entities.Hole;
import com.nicolrom.entities.Volume;

import java.util.Date;
import java.util.List;

public interface VolumeService {

    Volume createVolume(Contract contract, String regNr, String district, Date startDate, Date endDate, List<Hole> holes);
}
