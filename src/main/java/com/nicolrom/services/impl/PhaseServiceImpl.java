package com.nicolrom.services.impl;

import com.nicolrom.dao.PhaseDao;
import com.nicolrom.entities.Phase;
import com.nicolrom.services.PhaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhaseServiceImpl implements PhaseService {

    @Autowired
    private PhaseDao phaseDao;

    @Override
    public void savePhase(Phase phase) {
        phaseDao.savePhase(phase);
    }
}
