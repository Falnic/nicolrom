package com.nicolrom.services.impl;

import com.nicolrom.dao.PhaseDao;
import com.nicolrom.entities.Hole;
import com.nicolrom.entities.Phase;
import com.nicolrom.services.PhaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhaseServiceImpl implements PhaseService {

    @Autowired
    private PhaseDao phaseDao;

    @Override
    public List<Phase> createPhases(Hole hole, List<Phase> holePhases) {
        List<Phase> updatedPhases = new ArrayList<>();
        for (Phase phase : holePhases){
            Phase updatedPhase = new Phase();
            updatedPhase.setPhaseId(phase.getPhaseId());
            updatedPhase.setHole(hole);
            updatedPhase.setPhaseDate(hole.getDate());

            updatedPhases.add(updatedPhase);
        }
        return updatedPhases;
    }

    @Override
    public void savePhase(Phase phase) {
        phaseDao.savePhase(phase);
    }

    @Override
    public void updatePhase(Phase phase) {
        phaseDao.updatePhase(phase);
    }
}
