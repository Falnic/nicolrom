package com.nicolrom.dao;

import com.nicolrom.entities.Hole;

import java.util.List;

public interface HoleDao {

    public List<Hole> getAllHoles();

    void saveHole(Hole hole);
}
