package com.nicolrom.services.impl;

import com.nicolrom.dao.VolumeDao;
import com.nicolrom.entities.Contract;
import com.nicolrom.entities.Hole;
import com.nicolrom.entities.Volume;
import com.nicolrom.entities.dto.VolumeDTO;
import com.nicolrom.services.VolumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VolumeServiceImpl implements VolumeService {

    @Autowired
    private VolumeDao volumeDao;

    @Override
    public List<VolumeDTO> getVolumesInDateRange(LocalDate startDate, LocalDate endDate, Integer pageNo, Integer pageSize) {
        return volumeDao.getAllVolumesInDateRange(java.sql.Date.valueOf(startDate), java.sql.Date.valueOf(endDate), pageNo, pageSize).stream()
                .map(this::populateDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<VolumeDTO> getLastVolumes(Integer volumeCount) {
        return volumeDao.getLastVolumes(volumeCount).stream()
                .map(this::populateDTO)
                .collect(Collectors.toList());
    }

    private VolumeDTO populateDTO(Volume volume) {
        VolumeDTO volumeDTO = new VolumeDTO();
        volumeDTO.setVolumeId(volume.getIdVolume());
        volumeDTO.setDistrict(volume.getDistrict());
        volumeDTO.setNr(volume.getNr());
        volumeDTO.setRegNr(volume.getRegNr());
        volumeDTO.setStartDate(volume.getStartDate());
        volumeDTO.setEndDate(volume.getEndDate());
        return volumeDTO;
    }

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

    @Override
    public double getLastPageNr(LocalDate startDate, LocalDate endDate, Integer pageSize) {
        double holesNr = volumeDao.countVolumes(java.sql.Date.valueOf(startDate),java.sql.Date.valueOf(endDate));
        return Math.ceil(holesNr / pageSize);
    }
}
