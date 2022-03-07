package com.nicolrom.services.impl;

import com.nicolrom.dao.VolumeDao;
import com.nicolrom.entities.Contract;
import com.nicolrom.entities.Hole;
import com.nicolrom.entities.Volume;
import com.nicolrom.services.VolumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VolumeServiceImpl implements VolumeService {

    @Autowired
    private VolumeDao volumeDao;

    @Override
    public Volume createVolume(String volumeNr, Contract contract, String regNr, String district, Date startDate, Date endDate, List<Hole> holes) {
        Volume volume = new Volume();
        volume.setNr(volumeNr);
        volume.setRegNr(regNr);
        volume.setDistrict(district);
        volume.setStartDate(startDate);
        volume.setEndDate(endDate);
        volume.setContract(contract);
        volume.setHoles(holes);
        volume.setTotal(0.00);

        return volume;
    }

    @Override
    public void save(Volume volume) {
        volumeDao.save(volume);
    }
}
