package com.nicolrom.dao;

import com.nicolrom.entities.Material;

import java.util.List;

public interface MaterialDao {
    List<Material> getAllMaterials();

    Material getMaterialById(Integer id);

    Material getMaterialByName(String name);
}
