package com.nicolrom.dao;

import com.nicolrom.entities.Phase;

public interface PhaseDao {

    void savePhase(Phase phase);

    Phase getPhase(Integer id);

    void updatePhase(Phase phase);

    void deletePhase(Phase phase);
}
