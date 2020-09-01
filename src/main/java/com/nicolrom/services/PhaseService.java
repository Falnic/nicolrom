package com.nicolrom.services;

import com.nicolrom.entities.Hole;
import com.nicolrom.entities.Phase;

import java.util.List;

public interface PhaseService {

    List<Phase> createPhases(Hole hole, List<Phase> holePhases);

    void savePhase(Phase phase);

    void updatePhase(Phase phase);
}
