package com.nicolrom.services;

import com.nicolrom.entities.Material;

import java.util.List;

public interface MaterialService {
    List<Material> getAllMaterials();

    Material getMaterialById(Integer id);

    Material getMaterialByName(String name);
}
