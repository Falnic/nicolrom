package com.nicolrom.services.impl;

import com.nicolrom.dao.AreaDao;
import com.nicolrom.entities.Area;
import com.nicolrom.services.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaDao areaDao;

    @Override
    public Area getArea(int areaId) {
        return areaDao.getArea(areaId);
    }

    @Override
    public List<Area> getAllAreas() {
        return areaDao.getAllAreas();
    }

    @Override
    public List<String> getAllAreaTypes() {
        return areaDao.getAllAreaTypes();
    }

    @Override
    public void deleteArea(Area area) {
     areaDao.deleteArea(area);
    }
}
