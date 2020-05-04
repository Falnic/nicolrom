package com.nicolrom.dao;

import com.nicolrom.entities.Area;

import java.util.List;

public interface AreaDao {

    Area getArea(int id);

    List<Area> getAllAreas();

    List<String> getAllAreaTypes();
}
