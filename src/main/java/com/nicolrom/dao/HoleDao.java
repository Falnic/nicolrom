package com.nicolrom.dao;

import com.nicolrom.entities.Hole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoleDao {

    List<Hole> getAllHoles();

    List<Hole> getAllHoles(Integer pageNo, Integer pageSize, String sortBy);

    double countHoles();

    Hole getHoleById(Integer id);

    void saveHole(Hole hole);

    void updateHole(Hole hole);
}
