package com.nicolrom.services;

import com.nicolrom.entities.Hole;
import com.nicolrom.entities.MaterialNotice;
import com.nicolrom.entities.Phase;

import java.util.List;
import java.util.Set;

public interface MaterialNoticeService {
    void saveMaterialNotice (MaterialNotice notice);

    void saveMaterialNotices (Set<MaterialNotice> materialNotices);

    Set<MaterialNotice> getMaterialNoticeSet(Phase phase, List<Integer> materialIds, List<Integer> materialsQuantities);

    Set<MaterialNotice> calculateMaterialsForPhase(Hole hole, Phase phase);
}
