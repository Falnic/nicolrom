package com.nicolrom.services.impl;

import com.nicolrom.entities.Contract;
import com.nicolrom.entities.Hole;
import com.nicolrom.entities.Volume;
import com.nicolrom.services.VolumeService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VolumeServiceImpl implements VolumeService {

    @Override
    public Volume createVolume(Contract contract, String regNr, String district, Date startDate, Date endDate, List<Hole> holes) {
        Volume volume = new Volume();
        volume.setContract(contract);
        volume.setRegNr(regNr);
        volume.setDistrict(district);
        volume.setStartDate(startDate);
        volume.setEndDate(endDate);
        volume.setHoles(holes);

        return volume;
    }
}
