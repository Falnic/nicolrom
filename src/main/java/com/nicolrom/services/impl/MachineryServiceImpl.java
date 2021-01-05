package com.nicolrom.services.impl;

import com.nicolrom.dao.MachineryDao;
import com.nicolrom.entities.Machinery;
import com.nicolrom.services.MachineryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MachineryServiceImpl implements MachineryService {

    @Autowired
    private MachineryDao machineryDao;

    @Override
    public Machinery getMachineryById(Integer id) {
        return machineryDao.getMachineryById(id);
    }

    @Override
    public Set<Machinery> getMachineriesById(List<Integer> machineriesId) {
        Set<Machinery> machineries = new HashSet<>();
        for (Integer id : machineriesId){
            if (id != null){
                machineries.add(getMachineryById(id));
            }
        }
        return machineries;
    }
}
