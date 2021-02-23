package com.nicolrom.services.impl;

import com.nicolrom.dao.HoleDao;
import com.nicolrom.entities.Address;
import com.nicolrom.entities.Hole;
import com.nicolrom.entities.HoleAddress;
import com.nicolrom.entities.Phase;
import com.nicolrom.entities.dto.HoleDTO;
import com.nicolrom.enums.OrderOptionsEnum;
import com.nicolrom.enums.PhaseEnum;
import com.nicolrom.services.*;
import com.nicolrom.translators.HoleTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HoleServiceImpl implements HoleService {

    @Autowired
    private AreaService areaService;

    @Autowired
    private HoleDao holeDao;

    @Autowired
    private HoleTranslator holeTranslator;

    @Autowired
    private PipeService pipeService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private HoleAddressService holeAddressService;

    @Override
    public List<HoleDTO> getAllHoles() {
        return populateDTO(holeDao.getAllHoles());
    }

    @Override
    public List<HoleDTO> getAllHoles(Integer pageNo, Integer pageSize, String orderBy) {

        OrderOptionsEnum orderOptionEnum = holeTranslator.translateOrderOption(orderBy);

        List<Hole> pagedResult;
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
            return new ArrayList<>();
        }
    }

    @Override
    public List<HoleDTO> searchHolesByAddress(String address) {
        return populateDTO(holeDao.searchHolesByStreet(address));
    }

    @Override
    public List<HoleDTO> getHolesByDistricts(String[] districts) {
        return populateDTO(holeDao.getHolesByDistricts(districts));
    }

    @Override
    public List<HoleDTO> filterHolesByDistricts(List<HoleDTO> holes, List<String> districts){
        return holes
                .stream()
                .filter(holeDTO -> districts.contains(holeDTO.getDistrict()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getHoleDistricts() {
        return holeDao.getHoleDistricts();
    }

    @Override
    public List<HoleDTO> getHolesOrdered(List<HoleDTO> holes, String orderBy) {

        OrderOptionsEnum orderOptionEnum = holeTranslator.translateOrderOption(orderBy);

        switch (orderOptionEnum){
            case DATA_DESCRESCATOR:
                holes.sort(Comparator.comparing(HoleDTO::getDate));
            case ADRESA_ALFABETIC:
                holes.sort(Comparator.comparing(HoleDTO::getStreet));
            default:
                return holes;
        }
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
    public double getLastPageNr(Integer pageSize, String[] districts) {
        double holesNr = holeDao.countHoles(districts);
        return Math.ceil(holesNr / pageSize);
    }

    @Override
    public Hole getHoleById(Integer id) {
        return holeDao.getHoleById(id);
    }

    @Override
    public void saveHole(Hole hole) {
        holeDao.saveHole(hole);
        holeAddressService.save(hole.getHoleAddress());
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
    public Hole create(String holeDate, String street, String streetNr, String locality, String county, String district,
                       Integer areaId, Double holeLenght, Double holeWidth, Double holeDepth, String executor,
                       Double autoRouteDistance, Integer autoStationaryTime, String pipeDiameter) {
        Hole hole = new Hole();

        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(holeDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        hole.setDate(date);

        Address address = addressService.getAddressByStreet(street);
        HoleAddress holeAddress = holeAddressService.create(hole, address, streetNr);
        hole.setHoleAddress(holeAddress);

        hole.setArea(areaService.getArea(areaId));
        hole.setHoleLength(holeLenght);
        hole.setHoleWidth(holeWidth);
        hole.setHoleDepth(holeDepth);
        hole.setHoleVolume(hole.getHoleLength() * hole.getHoleWidth() * hole.getHoleDepth());
        hole.setExecutor(executor);
        hole.setAutoRouteDistance(autoRouteDistance);
        hole.setAutoStationaryTime(autoStationaryTime);
        if (pipeDiameter != null){
            hole.setPipe(pipeService.getPipeByDiameter(pipeDiameter));
        }

        return hole;
    }

    @Override
    public String checkHole(Hole hole){
//        List<Hole> duplicates = holeDao.getHolesAtSameAddres(hole);
//        return checkHoleForSameDate(duplicates, hole);
        return null;
    }

    private String checkHoleForSameDate(List<Hole> duplicates, Hole hole){
        String messaje = "Nu se poate adauga aceeasi sapatura in aceeasi zi";
        for (Hole duplicate : duplicates){
            if ((hole.getHoleId() != duplicate.getHoleId()) && (hole.getDate().compareTo(duplicate.getDate()) == 0)){
                return messaje;
            }
        }
        return null;
    }

    @Override
    public String checkHole(Hole hole, Hole updatedHole) {
//        List<Hole> holeDuplicates = holeDao.getHolesAtSameAddres(updatedHole);
//        return checkHoleForSameDate(holeDuplicates, updatedHole);
        return null;
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
//        if (hole.getDate().compareTo(updatedHole.getDate()) != 0) return false;
//        if (hole.getStreet().compareToIgnoreCase(updatedHole.getStreet()) != 0) return false;
//        if (hole.getStreetNr().compareToIgnoreCase(updatedHole.getStreetNr()) != 0) return false;

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

        HoleAddress holeAddress = hole.getHoleAddress();
        Address address = holeAddress.getAddress();

        holeDTO.setStreet(address.getStreet());
        holeDTO.setStreetNr(holeAddress.getStreetNr());
        holeDTO.setLocality(address.getLocality());
        holeDTO.setCounty(address.getCounty());
        holeDTO.setDistrict(address.getDistrict());

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
