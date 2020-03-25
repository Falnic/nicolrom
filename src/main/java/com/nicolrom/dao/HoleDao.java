package com.nicolrom.dao;

import com.nicolrom.entities.Hole;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface HoleDao {

    List<Hole> getAllHoles();

    Hole getHoleById(Integer id);

    void saveHole(Hole hole);
}
