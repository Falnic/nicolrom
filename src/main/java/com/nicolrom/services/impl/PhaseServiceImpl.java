package com.nicolrom.services.impl;

import com.nicolrom.dao.PhaseDao;
import com.nicolrom.entities.Hole;
import com.nicolrom.entities.Phase;;
import com.nicolrom.enums.PhaseEnum;
import com.nicolrom.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PhaseServiceImpl implements PhaseService {

    @Autowired
    private PhaseDao phaseDao;

    @Autowired
    private TeamService teamService;

    @Autowired
    private MaterialNoticeService materialNoticeService;

    @Override
    public Phase createHolePhase(Hole hole, String phaseDate, PhaseEnum nextPhase) {
        Phase phase = new Phase();
        phase.setPhaseType(nextPhase);

        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(phaseDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        phase.setPhaseDate(date);
        phase.setHole(hole);

        return phase;
    }

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
    public Phase getPhase(Integer phaseId) {
        return phaseDao.getPhase(phaseId);
    }

    @Override
    public void savePhase(Phase phase) {
        phaseDao.savePhase(phase);
    }

    @Override
    public void updatePhase(Phase phase) {
        phaseDao.updatePhase(phase);
    }

    @Override
    public void deletePhase(List<Phase> phases) {
        for (Phase phase : phases){
            switch (phase.getPhaseType()){
                case SAPATURA:
                    teamService.deleteTeam(phase.getTeam());
                    phaseDao.deletePhase(phase);
                    break;
                case UMPLERE:
                    materialNoticeService.deleteMaterialNotice(phase.getMaterialNoticeSet());
                    teamService.deleteTeam(phase.getTeam());
                    phaseDao.deletePhase(phase);
                    break;
            }
        }
    }

    @Override
    public Map<PhaseEnum, Phase> getPhasesByPhaseTypeMap(List<PhaseEnum> phaseEnums, List<Phase> phases) {
        Map<PhaseEnum, Phase> phaseEnumPhaseMap = new HashMap<>();
        for (PhaseEnum phaseEnum : phaseEnums){
            for (Phase phase : phases){
                if (phase.getPhaseType().equals(phaseEnum)){
                    phaseEnumPhaseMap.put(phaseEnum, phase);
                }
            }
            phaseEnumPhaseMap.putIfAbsent(phaseEnum, null);
        }
        return phaseEnumPhaseMap;
    }

    @Override
    public Phase getHolePhaseByPhaseType(Hole hole, PhaseEnum phaseEnum) {
        for (Phase phase : hole.getPhases()){
            if (phaseEnum.equals(phase.getPhaseType())){
                return phase;
            }
        }
        return null;
    }

    @Override
    public PhaseEnum getNextPhase(List<Phase> phases) {
        PhaseEnum[] enums = PhaseEnum.values();
        for (PhaseEnum phaseEnum : enums){
            boolean contains = false;
            for (Phase phase : phases){
                if (phaseEnum.equals(phase.getPhaseType())) {
                    contains = true;
                    break;
                }
            }
            if (!contains){
                return phaseEnum;
            }
        }
        return null;
    }
}
