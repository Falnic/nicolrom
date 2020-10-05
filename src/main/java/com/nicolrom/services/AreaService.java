package com.nicolrom.services;

import com.nicolrom.entities.Area;

import java.util.List;

public interface AreaService {

    Area getArea(int areaId);

    List<Area> getAllAreas();

    List<String> getAllAreaTypes();

    void deleteArea(Area area);
}
