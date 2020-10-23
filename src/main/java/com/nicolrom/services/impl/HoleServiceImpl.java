package com.nicolrom.services.impl;

import com.nicolrom.dao.HoleDao;
import com.nicolrom.entities.Hole;
import com.nicolrom.entities.Phase;
import com.nicolrom.entities.dto.HoleDTO;
import com.nicolrom.enums.OrderOptionsEnum;
import com.nicolrom.enums.PhaseEnum;
import com.nicolrom.services.AreaService;
import com.nicolrom.services.HoleService;
import com.nicolrom.translators.HoleTranslator;
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

    @Autowired
    private HoleTranslator holeTranslator;

    @Override
    public List<HoleDTO> getAllHoles() {
        return populateDTO(holeDao.getAllHoles());
    }

    @Override
    public List<HoleDTO> getAllHoles(Integer pageNo, Integer pageSize, String orderBy) {

        OrderOptionsEnum orderOptionEnum = holeTranslator.translateOrderOption(orderBy);

        List<Hole> pagedResult = new ArrayList<>();
        switch (orderOptionEnum){
            case DATA_DESCRESCATOR:
                pagedResult = holeDao.getHolesOrderedByDate(pageNo, pageSize);
                break;
            case ADRESA_ALFABETIC:
                pagedResult = holeDao.getHolesOrderedByAddress(pageNo, pageSize);
                break;
            default:
                pagedResult = holeDao.getAllHoles(pageNo, pageSize);
        }

        if (!pagedResult.isEmpty()){
            return populateDTO(pagedResult);
        } else {
            return new ArrayList<HoleDTO>();
        }
    }

    @Override
    public List<HoleDTO> searchHolesByAddress(String address) {
        return populateDTO(holeDao.searchHolesByStreet(address));
    }

    @Override
    public double getLastPageNr(Integer pageSize) {
        double holesNr = holeDao.countHoles();
        return Math.ceil(holesNr / pageSize);
    }

    @Override
    public double getLastPageNr(Integer pageSize, String searchValue) {
        double holesNr = holeDao.countHoles(searchValue);
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
    public void deleteHole(Hole hole) {
        holeDao.deleteHole(hole);
    }

    @Override
    public Hole create(String holeDate, String street, String streetNr, String locality, String county, String district, Integer areaId,
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
        hole.setCounty(county);
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
    public String checkHole(Hole hole){
        List<Hole> duplicates = holeDao.getHolesAtSameAddres(hole);
        return checkHoleForSameDate(duplicates, hole);
    }

    private String checkHoleForSameDate(List<Hole> duplicates, Hole hole){
        String messaje = "Nu se pot adauga mai multe sapaturi in aceeasi zi";
        for (Hole duplicate : duplicates){
            if (hole.getDate().compareTo(duplicate.getDate()) == 0){
                return messaje;
            }
        }
        return null;
    }

    @Override
    public String checkHole(Hole hole, Hole updatedHole) {
        if (!compareHoles(hole, updatedHole)){
            List<Hole> holeDuplicates = holeDao.getHolesAtSameAddres(updatedHole);
            return checkHoleForSameDate(holeDuplicates, updatedHole);
        } else {
            return "Nu se pot adauga mai multe sapaturi in aceeasi zi";
        }
        // TODO: Create the algorithm for duplicate Holes in order to set HoleNrAtSameAddress
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
        holeDTO.setCounty(hole.getCounty());
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
}
