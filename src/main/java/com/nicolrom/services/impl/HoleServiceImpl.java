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

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public List<Hole> findHolesByStreet(String street) {
        return holeDao.findHolesByStreet(street);
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
        holeDao.saveHole(hole);
    }

    @Override
    public void updateHole(Hole hole) {
        holeDao.updateHole(hole);
    }

    @Override
    public Hole create(String holeDate, String street, String streetNr, String locality, String district, Integer areaId,
                       Double holeLenght, Double holeWidth, Double holeDepth, String executor, Integer autoRouteDistance, Integer autoStationaryTime) {
        Hole hole = new Hole();

        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(holeDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        hole.setDate(date);
        hole.setStreet(street);
        hole.setStreetNr(streetNr);
        hole.setLocality(locality);
        hole.setDistrict(district);
        hole.setArea(areaService.getArea(areaId));
        hole.setHoleLength(holeLenght);
        hole.setHoleWidth(holeWidth);
        hole.setHoleDepth(holeDepth);
        hole.setHoleVolume(hole.getHoleLength() * hole.getHoleWidth() * hole.getHoleDepth());
        hole.setExecutor(executor);
        hole.setAutoRouteDistance(autoRouteDistance);
        hole.setAutoStationaryTime(autoStationaryTime);

        return hole;
    }

    @Override
    public void checkHole(Hole hole){
        List<Hole> duplicates = holeDao.getDuplicates(hole);
        checkHoleForSameAddress(duplicates, hole);
    }

    private void checkHoleForSameAddress(List<Hole> duplicates, Hole hole){
        if (duplicates != null && duplicates.size() > 0) {
            if (duplicates.size() == 1){
                Hole duplicate = duplicates.get(0);
                duplicate.setHoleNrAtSameAddress(1);
                updateHole(duplicate);

                hole.setHoleNrAtSameAddress(duplicates.size() + 1);
            } else {
                hole.setHoleNrAtSameAddress(duplicates.size() + 1);
            }
        }
    }

    @Override // TODO: not working
    public void checkHole(Hole hole, Hole updatedHole) {
        // if hole and updatedHole are different remove duplicate lists
        if (!compareHoles(hole, updatedHole)){
            List<Hole> holeDuplicates = holeDao.getDuplicates(hole);
            if (holeDuplicates.size() > 1){
//                holeDuplicates.remove(hole);
                for (Hole duplicate : holeDuplicates){
                    if (duplicate.getDate().compareTo(hole.getDate()) > 0){
                        duplicate.setHoleNrAtSameAddress(duplicate.getHoleNrAtSameAddress() - 1);
                    }
                }
                List<Hole> updatedHoleDuplicates = holeDao.getDuplicates(updatedHole);
                if (updatedHoleDuplicates.size() > 0){
                    updatedHole.setHoleNrAtSameAddress(updatedHoleDuplicates.size() + 1);
                }
            } else {
                List<Hole> updatedHoleDuplicates = holeDao.getDuplicates(updatedHole);
                if (updatedHoleDuplicates.size() > 0) {
                    for (Hole duplicate : updatedHoleDuplicates){
                        if (duplicate.getHoleNrAtSameAddress() > updatedHole.getHoleNrAtSameAddress()){
                            duplicate.setHoleNrAtSameAddress(duplicate.getHoleNrAtSameAddress() - 1);
                        }
                    }
                } else  {
                    updatedHole.setHoleNrAtSameAddress(0);
                }
            }
        }
    }


    /**
     * Compares two Holes for differences
     *
     * @param   hole the initial <code>Hole</code> to be compared.
     * @param   updatedHole  the updated <code>Hole</code> for comparison
     * @return  the value <code>false</code> if the argument hole is equal to
     *          the updatedHole; <code>true</code> if this hole haves the same
     *          arguments as updatedHole.
     */
    private boolean compareHoles(Hole hole, Hole updatedHole){
        if (hole.getDate().compareTo(updatedHole.getDate()) != 0) return false;
        if (hole.getStreet().compareToIgnoreCase(updatedHole.getStreet()) != 0) return false;
        if (hole.getStreetNr().compareToIgnoreCase(updatedHole.getStreetNr()) != 0) return false;

        return true;
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
        holeDTO.setHoleNrAtSameAddress(hole.getHoleNrAtSameAddress());
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
