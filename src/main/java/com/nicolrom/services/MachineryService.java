package com.nicolrom.services;

import com.nicolrom.entities.Machinery;

import java.util.List;
import java.util.Set;

public interface MachineryService {

    Machinery getMachineryById(Integer id);

    Set<Machinery> getMachineriesById(List<Integer> machineriesId);

}
