package com.nicolrom.services;

import com.nicolrom.entities.Hole;
import com.nicolrom.entities.Phase;
import com.nicolrom.enums.PhaseEnum;

import java.util.List;
import java.util.Map;

public interface PhaseService {

    Phase createHolePhase(Hole hole, String phaseDate, PhaseEnum nextPhase);

    List<Phase> createPhases(Hole hole, List<Phase> holePhases);

    Phase getPhase(Integer phaseId);

    void savePhase(Phase phase);

    void updatePhase(Phase phase);

    void deletePhase(List<Phase> phases);

    Map<PhaseEnum, Phase> getPhasesByPhaseTypeMap(List<PhaseEnum> phaseEnums, List<Phase> phases);

    Phase getHolePhaseByPhaseType(Hole hole, PhaseEnum phaseEnum);

    PhaseEnum getNextPhase(List<Phase> phases);
}
