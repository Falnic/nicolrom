package com.nicolrom.services.impl;

import com.nicolrom.dao.HoleDao;
import com.nicolrom.entities.Hole;
import com.nicolrom.entities.Phase;
import com.nicolrom.entities.dto.HoleDTO;
import com.nicolrom.enums.PhaseEnum;
import com.nicolrom.services.AreaService;
import com.nicolrom.services.HoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HoleServiceImpl implements HoleService {

    @Autowired
    private AreaService areaService;

    @Autowired
    private HoleDao holeDao;

    @Override
    public List<HoleDTO> getAllHoles() {
        return populateDTO(holeDao.getAllHoles());
    }

    @Override
    public List<HoleDTO> getAllHoles(Integer pageNo, Integer pageSize, String sortBy) {

        List<Hole> pagedResult = holeDao.getAllHoles(pageNo, pageSize, sortBy);
        if (!pagedResult.isEmpty()){
            return populateDTO(pagedResult);
        } else {
            return new ArrayList<HoleDTO>();
        }
    }

    @Override
    public double getLastPageNr(Integer pageSize) {
        double holesNr = holeDao.countHoles();
        return Math.ceil(holesNr / pageSize);
    }

    @Override
    public Hole getHoleById(Integer id) {
        return holeDao.getHoleById(id);
    }

    @Override
    public void saveHole(Hole hole) {
        hole.setHoleVolume(hole.getHoleLength() * hole.getHoleWidth() * hole.getHoleDepth());
        holeDao.saveHole(hole);
    }

    @Override
    public void updateHole(Hole hole) {
        holeDao.updateHole(hole);
    }

    @Override
    public Hole create(Date date, String street, String streetNr, String locality, String district, Integer areaId,
                       Double holeLenght, Double holeWidth, Double holeDepth, String executor, Integer autoRouteDistance, Integer autoStationaryTime) {
        Hole hole = new Hole();

        //todo: Validari pt input-uri

        hole.setDate(date);
        hole.setStreet(street);
        hole.setStreetNr(streetNr);
        hole.setLocality(locality);
        hole.setDistrict(district);
        hole.setArea(areaService.getArea(areaId));
        hole.setHoleLength(holeLenght);
        hole.setHoleWidth(holeWidth);
        hole.setHoleDepth(holeDepth);
        hole.setExecutor(executor);
        hole.setAutoRouteDistance(autoRouteDistance);
        hole.setAutoStationaryTime(autoStationaryTime);

        return hole;
    }

    private List<HoleDTO> populateDTO(List<Hole> holes){

        List<HoleDTO> holeDTOList = new ArrayList<>();
        for (Hole hole : holes) {
            HoleDTO holeDTO = populateDTO(hole);
            holeDTOList.add(holeDTO);
        }
        return holeDTOList;
    }

    private HoleDTO populateDTO(Hole hole){
        HoleDTO holeDTO = new HoleDTO();

        holeDTO.setHoleId(hole.getHoleId());
        holeDTO.setDate(hole.getDate());
        holeDTO.setStreet(hole.getStreet());
        holeDTO.setStreetNr(hole.getStreetNr());
        holeDTO.setLocality(hole.getLocality());
        holeDTO.setDistrict(hole.getDistrict());
        setHoleDtoPhase(holeDTO, hole);
        holeDTO.setHoleLength(hole.getHoleLength());
        holeDTO.setHoleWidth(hole.getHoleWidth());
        holeDTO.setHoleDepth(hole.getHoleDepth());
        holeDTO.setHoleVolume(hole.getHoleVolume());

        if (hole.getArea() != null){
            holeDTO.setHoleArea(hole.getArea().getType());
        }

        return holeDTO;
    }

    private void setHoleDtoPhase(HoleDTO holeDTO, Hole hole){
        PhaseEnum phaseEnum = PhaseEnum.SAPATURA;
        for (Phase phase: hole.getPhases()){
            if (phase.getPhaseType().ordinal() > phaseEnum.ordinal()){
                phaseEnum = phase.getPhaseType();
            }
        }
        holeDTO.setPhase(phaseEnum.name());
    }
}
