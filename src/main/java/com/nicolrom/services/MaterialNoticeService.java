package com.nicolrom.services;

import com.nicolrom.entities.Hole;
import com.nicolrom.entities.MaterialNotice;
import com.nicolrom.entities.Phase;

import java.util.List;
import java.util.Set;

public interface MaterialNoticeService {
    void saveMaterialNotice (MaterialNotice notice);

    void saveMaterialNotice(Set<MaterialNotice> materialNotices);

    void updateMaterialNotice(Set<MaterialNotice> materialNotices);

    void updateMaterialNotice(Set<MaterialNotice> materialNotices, Set<MaterialNotice> materialNoticesUpdated);

    void updateMaterialNotice(MaterialNotice materialNotice);

    Set<MaterialNotice> getMaterialNoticeSet(Phase phase, List<Integer> materialIds, List<Double> materialsQuantities);

    Set<MaterialNotice> getMaterialNoticeSet(Hole hole);

    Set<MaterialNotice> calculateMaterialsForPhase(Hole hole, Phase phase);

    void deleteMaterialNotice(Set<MaterialNotice> materialNotices);
}
