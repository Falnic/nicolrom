package com.nicolrom.services.impl;

import com.nicolrom.dao.MaterialDao;
import com.nicolrom.entities.Material;
import com.nicolrom.entities.MaterialNotice;
import com.nicolrom.services.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialDao materialDao;

    @Override
    public List<Material> getAllMaterials() {
        return materialDao.getAllMaterials();
    }

    @Override
    public List<Material> getAllMaterialsExcept(List<Material> filterMaterials) {
        List<Material> resultedMaterials = materialDao.getAllMaterials();
        for (Material filterMaterial : filterMaterials){
            resultedMaterials.removeIf(material -> material.equals(filterMaterial));
        }
        return resultedMaterials;
    }

    @Override
    public List<Material> getMaterials(Set<MaterialNotice> materialNotices) {
        List<Material> materials = new ArrayList<>();
        for (MaterialNotice materialNotice : materialNotices){
            materials.add(materialNotice.getMaterial());
        }
        return materials;
    }


    @Override
    public Material getMaterialById(Integer id) {
        return materialDao.getMaterialById(id);
    }

    @Override
    public Material getMaterialByName(String name) {
        return materialDao.getMaterialByName(name);
    }
}
