package com.paro.hospitalservice.service;

import com.paro.hospitalservice.model.Hospital;
import com.paro.hospitalservice.repository.HospitalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class HospitalService {
    private static final Logger LOGGER= LoggerFactory.getLogger(HospitalService.class);

    private final HospitalRepository hospitalRepository;

    @Autowired
    public HospitalService(HospitalRepository hospitalRepository){
        this.hospitalRepository=hospitalRepository;
    }

    public List<Hospital> getAll(){

        List<Hospital> hospitalList=hospitalRepository.findAll();
        LOGGER.info("Hospitals found");
        return hospitalList;
    }

    public Hospital getById(Long hospitalId) {
        Optional<Hospital> hospitalFound=hospitalRepository.findById(hospitalId);
        if (hospitalFound.isPresent()) {
            LOGGER.info("Hospital found with id={}", hospitalId);
            return hospitalFound.get();
        }
        return null;

    }
    // 2.way ResponseEntity- Returning HttpStatus.NOT_FOUND in case if the hospital with required id does not exist
/*
    public ResponseEntity<Hospital> getById(Long hospitalId) {
        Optional<Hospital> hospitalFound=hospitalRepository.findById(hospitalId);
        if (hospitalFound.isPresent()) {
            LOGGER.info("Hospital found with id={}", hospitalId);
            return new ResponseEntity<>(patientFound.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    }
*/

    public Hospital add( Hospital hospital){
        Hospital hospitalSaved=hospitalRepository.save(hospital);
        LOGGER.info("Hospital added with id={}", hospital.getId());
        return hospitalSaved;
    }

    public Hospital put(Hospital hospital) {
        Hospital hospitalSaved= hospitalRepository.save(hospital);
        LOGGER.info("Hospital put with id={}", hospital.getId());
        return hospitalSaved;
    }

    public Hospital patch(Long hospitalId, Hospital hospitalToPatch) {
        Hospital hospitalFound=hospitalRepository.findById(hospitalId).get();
        if (hospitalToPatch.getId()!=null) {
            hospitalFound.setId(hospitalToPatch.getId());
        }
        if (hospitalToPatch.getName()!=null) {
            hospitalFound.setName(hospitalToPatch.getName());
        }
        if (hospitalToPatch.getAddress()!=null) {
            hospitalFound.setAddress(hospitalToPatch.getAddress());
        }
        //There’s no way of removing or adding a subset of items from a collection.
        //If the hospital wants to add or remove an entry from a collection, it must send the complete altered collection.
        if (hospitalToPatch.getPatientList()!=null) {
            hospitalFound.setPatientList(hospitalToPatch.getPatientList());
        }
        if (hospitalToPatch.getDepartmentList()!=null) {
            hospitalFound.setDepartmentList(hospitalToPatch.getDepartmentList());
        }
        Hospital hospitalPatched= hospitalRepository.save(hospitalFound);
        LOGGER.info("Hospital patched with id={}", hospitalFound.getId());
        return hospitalPatched;
    }

    public void deleteById(Long hospitalId) {
        try {
            hospitalRepository.deleteById(hospitalId);
            LOGGER.info("Hospital deleted with id={}", hospitalId);
        } catch (EmptyResultDataAccessException e){}
    }

}
