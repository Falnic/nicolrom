package com.nicolrom.services;

import com.nicolrom.entities.Hole;
import com.nicolrom.entities.dto.HoleDTO;

import java.util.List;

public interface HoleService {

    List<HoleDTO> getAllHoles();

    List<HoleDTO> getAllHoles(Integer pageNo, Integer pageSize, String orderBy);

    List<HoleDTO> searchHolesByAddress(String address);

    double getLastPageNr(Integer pageSize);

    double getLastPageNr(Integer pageSize, String searchValue);

    Hole getHoleById(Integer id);

    void saveHole(Hole hole);

    void updateHole(Hole hole);

    void deleteHole(Hole hole);

    Hole create(String date, String street, String streetNr, String locality, String district, Integer areaId,
                Double holeLenght, Double holeWidth, Double holeDepth, String executor, Integer autoRouteDistance,
                Integer autoStationaryTime);

    String checkHole(Hole hole);

    String checkHole(Hole hole, Hole updatedHole);

}
