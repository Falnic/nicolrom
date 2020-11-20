package com.nicolrom.services.impl;

import com.nicolrom.dao.MaterialNoticeDao;
import com.nicolrom.entities.Hole;
import com.nicolrom.entities.MaterialNotice;
import com.nicolrom.entities.Phase;
import com.nicolrom.services.MaterialNoticeService;
import com.nicolrom.services.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MaterialNoticeServiceImpl implements MaterialNoticeService {

    @Autowired
    private MaterialService materialService;

    @Autowired
    private MaterialNoticeDao materialNoticeDao;

    @Override
    public void saveMaterialNotice(MaterialNotice notice) {
        materialNoticeDao.saveMaterialNotice(notice);
    }

    @Override
    public void saveMaterialNotices(Set<MaterialNotice> materialNotices) {
        for (MaterialNotice materialNotice : materialNotices){
            saveMaterialNotice(materialNotice);
        }
    }

    @Override
    public Set<MaterialNotice> calculateMaterialsForPhase(Hole hole, Phase phase) {
        switch (phase.getPhaseType()){
            case UMPLERE:
                 return calculateMaterialsForPhaseUmplere(hole, phase);
            default:
                return null;
        }
    }

    @Override
    public void deleteMaterialNotice(Set<MaterialNotice> materialNotices) {
        for (MaterialNotice materialNotice : materialNotices){
            materialNoticeDao.deleteMaterialNotice(materialNotice);
        }
    }

    public Set<MaterialNotice> calculateMaterialsForPhaseUmplere(Hole hole, Phase phase) {
        /*
            Cantitatea materialelor se calculeaza automat cu 2 zecimale
            Nisip = L*l*(0,30m + ϕteava)
            Balastru = Volum - Nisip
         */

        MaterialNotice nisipNotice = new MaterialNotice();
        nisipNotice.setMaterial(materialService.getMaterialByName("Nisip"));
        nisipNotice.setPhase(phase);

        double nisipQuantity = hole.getHoleLength() * hole.getHoleWidth() * (0.3 + hole.getPipe().getDiameterValue());
        nisipNotice.setQuantity(nisipQuantity);

        MaterialNotice balastNotice = new MaterialNotice();
        balastNotice.setMaterial(materialService.getMaterialByName("Balast"));
        balastNotice.setPhase(phase);

        double balastQuantity = hole.getHoleVolume() - nisipQuantity;
        balastNotice.setQuantity(balastQuantity);

        Set<MaterialNotice> materialNotices = new HashSet<>();
        materialNotices.add(nisipNotice); materialNotices.add(balastNotice);

        return materialNotices;
    }

    @Override
    public Set<MaterialNotice> getMaterialNoticeSet(Phase phase, List<Integer> materialIds, List<Double> materialsQuantities) {
        Set<MaterialNotice> materialNotices = new HashSet<>();
        for (int i = 0; i < materialIds.size(); i++){
            MaterialNotice materialNotice = new MaterialNotice();
            materialNotice.setMaterial(materialService.getMaterialById(materialIds.get(i)));
            materialNotice.setQuantity(materialsQuantities.get(i));
            materialNotice.setPhase(phase);
            materialNotices.add(materialNotice);
        }
        return materialNotices;
    }


}
