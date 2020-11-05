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

    List<Hole> getHolesByDistricts(String[] districts);

    List<String> getHoleDistricts();

    Hole getHoleById(Integer id);

    List<Hole> searchHolesByStreet(String street);

    List<Hole> getHolesAtSameAddres(Hole hole);

    double countHoles();

    double countHoles(String searchValue);

    double countHoles(String[] districts);

    void saveHole(Hole hole);

    void updateHole(Hole hole);

    void deleteHole(Hole hole);
}
