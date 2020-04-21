package com.nicolrom.services;

import com.nicolrom.entities.Hole;
import com.nicolrom.entities.dto.HoleDTO;

import java.util.List;

public interface HoleService {

    List<HoleDTO> getAllHoles();

    List<HoleDTO> getAllHoles(Integer pageNo, Integer pageSize, String sortBy);

    double getLastPageNr(Integer pageSize);

    Hole getHoleById(Integer id);

    void saveHole(Hole hole);
}
