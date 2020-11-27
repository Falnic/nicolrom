package com.nicolrom.services;

import com.nicolrom.entities.Material;
import com.nicolrom.entities.MaterialNotice;

import java.util.List;
import java.util.Set;

public interface MaterialService {
    List<Material> getAllMaterials();

    List<Material> getAllMaterialsExcept(List<Material> materials);

    List<Material> getMaterials(Set<MaterialNotice> materialNotices);

    Material getMaterialById(Integer id);

    Material getMaterialByName(String name);
}
