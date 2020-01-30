package com.nicolrom.services.impl;

import com.nicolrom.dao.MaterialDao;
import com.nicolrom.entities.Material;
import com.nicolrom.services.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialDao materialDao;

    @Override
    public List<Material> getAllMaterials() {
        return materialDao.getAllMaterials();
    }
}
