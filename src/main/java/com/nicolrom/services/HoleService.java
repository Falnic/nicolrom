package com.nicolrom.services;

import com.nicolrom.entities.Hole;
import com.nicolrom.entities.Phase;
import com.nicolrom.entities.dto.HoleDTO;
import com.nicolrom.enums.PhaseEnum;

import java.util.List;

public interface HoleService {

    List<HoleDTO> getAllHoles();

    Phase getHolePhaseByType(Hole hole, PhaseEnum phaseEnum);
}
