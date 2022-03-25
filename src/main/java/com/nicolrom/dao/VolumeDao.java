package com.nicolrom.dao;

import com.nicolrom.entities.Volume;

import java.sql.Date;
import java.util.List;

public interface VolumeDao {

    List<Volume> getAllVolumesInDateRange(Date startDate, Date endDate, Integer pageNo, Integer pageSize);

    List<Volume> getLastVolumes(Integer volumeCount);

    long countVolumes(Date startDate, Date endDate);

    void save(Volume volume);
}
