package com.nicolrom.services.impl;

import com.nicolrom.dao.MaterialNoticeDao;
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
    public Set<MaterialNotice> getMaterialNoticeSet(Phase phase, List<Integer> materialIds, List<Integer> materialsQuantities) {
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
