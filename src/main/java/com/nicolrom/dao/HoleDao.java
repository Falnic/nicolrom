package com.nicolrom.dao;

import com.nicolrom.entities.Hole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoleDao {

    List<Hole> getAllHoles();

    List<Hole> getAllHoles(Integer pageNo, Integer pageSize);

    List<Hole> getHolesOrderedByDate(Integer pageNo, Integer pageSize);

    List<Hole> getHolesOrderedByAddress(Integer pageNo, Integer pageSize);

    Hole getHoleById(Integer id);

    List<Hole> findHolesByStreet(String street);

    List<Hole> getDuplicates(Hole hole);

    double countHoles();

    void saveHole(Hole hole);

    void updateHole(Hole hole);

    void deleteHole(Hole hole);
}
