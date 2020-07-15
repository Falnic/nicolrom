package com.nicolrom.services;

import com.nicolrom.entities.Hole;
import com.nicolrom.entities.dto.HoleDTO;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface HoleService {

    List<HoleDTO> getAllHoles();

    List<HoleDTO> getAllHoles(Integer pageNo, Integer pageSize, String sortBy);

    List<Hole> findHolesByStreet(String address);

    double getLastPageNr(Integer pageSize);

    Hole getHoleById(Integer id);

    void saveHole(Hole hole);

    void updateHole(Hole hole);

    Hole create(Date date, String street, String streetNr, String locality, String district, Integer areaId,
                Double holeLenght, Double holeWidth, Double holeDepth, String executor, Integer autoRouteDistance,
                Integer autoStationaryTime);

    HashMap<Boolean, String> checkHole(Hole hole);

}
