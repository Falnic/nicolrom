package com.nicolrom.services;

import com.nicolrom.entities.Contract;
import com.nicolrom.entities.Hole;
import com.nicolrom.entities.Volume;
import com.nicolrom.entities.dto.VolumeDTO;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface VolumeService {

    List<VolumeDTO> getVolumesInDateRange(LocalDate startDate, LocalDate endDate, Integer pageNo, Integer pageSize);

    List<VolumeDTO> getLastVolumes(Integer volumeCount);

    Volume createVolume(String volumeNr, Contract contract, String regNr, String district, Date startDate, Date endDate, List<Hole> holes);

    void save(Volume volume);

    double getLastPageNr(LocalDate startDate, LocalDate endDate, Integer pageSize);
}
