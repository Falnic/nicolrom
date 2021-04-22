package com.nicolrom.services;

import com.nicolrom.entities.Hole;
import com.nicolrom.entities.dto.HoleDTO;
import com.nicolrom.entities.dto.VPHoleDTO;

import java.util.List;

public interface HoleService {

    List<HoleDTO> getAllHoles();

    List<HoleDTO> getAllHoles(Integer pageNo, Integer pageSize, String orderBy);

    List<HoleDTO> searchHolesByAddress(String address);

    List<HoleDTO> getHolesByDistricts(String[] districts);

    List<HoleDTO> filterHolesByDistricts(List<HoleDTO> holes, List<String> districts);

    List<HoleDTO> getHolesOrdered(List<HoleDTO> holes, String orderBy);

    List<VPHoleDTO> getHolesWithoutVolume();

    List<VPHoleDTO> getHolesWithoutVolume(String district);

    List<String> getHoleDistricts();

    double getLastPageNr(Integer pageSize);

    double getLastPageNr(Integer pageSize, String searchValue);

    double getLastPageNr(Integer pageSize, String[] districts);

    Hole getHoleById(Integer id);

    void saveHole(Hole hole);

    void updateHole(Hole hole);

    void deleteHole(Hole hole);

    Hole create(String date, String street, String streetNr, String locality, String county, String district, Integer areaId,
                Double holeLenght, Double holeWidth, Double holeDepth, String executor, Double autoRouteDistance,
                Integer autoStationaryTime, String pipeDiameter);

    String checkHole(Hole hole);

    String checkHole(Hole hole, Hole updatedHole);

}
