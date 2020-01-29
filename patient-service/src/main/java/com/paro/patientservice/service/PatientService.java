package com.paro.patientservice.service;

import com.paro.patientservice.model.Patient;
import com.paro.patientservice.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PatientService.class);

    //Field Injection - The Ugly
    /*
    @Autowired
    private PatientRepository patientRepository;
    */
    //Setter Injection - The Bad
    /*
    private PatientRepository patientRepository;
    @Autowired
    public void setPatientRepository(PatientRepository patientRepository){
        this.patientRepository=patientRepository;
    }
    */
    //Constructor Injection (1) - The Good
    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository){
        this.patientRepository=patientRepository;
    }
    //Constructor Injection (2) - The Good
    //(For a single-constructor scenario)
/*    private PatientRepository patientRepository;
    public PatientService(PatientRepository patientRepository){

        this.patientRepository=patientRepository;
    }*/

    public Patient getById(Long patientId) {
        Optional<Patient> patientFound=patientRepository.findById(patientId);
        if (patientFound.isPresent()) {
            LOGGER.info("Patient found with id={}", patientId);
            return patientFound.get();
        }
        return null;

    }
    // 2.way ResponseEntity- Returning HttpStatus.NOT_FOUND in case if the patient with required id does not exist
/*
    public ResponseEntity<Patient> getById(Long patientId) {
        Optional<Patient> patientFound=patientRepository.findById(patientId);
        if (patientFound.isPresent()) {
            LOGGER.info("Patient found with id={}", patientId);
            return new ResponseEntity<>(patientFound.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    }
*/

    public List<Patient> getAll() {
        //In case if we would want 20 patients from 1st page
        /*
        PageRequest pageRequest=PageRequest.of(0, 20);
        List<Patient> patientsFound=patientRepository.findAll(pageRequest).getContent();
        */
        List<Patient> patientsFound=patientRepository.findAll();
        LOGGER.info("Patients found");
        return patientsFound;
    }

    public Patient add(Patient patient) {
        Patient patientSaved= patientRepository.save(patient);
        LOGGER.info("Patient added with id={}", patient.getId());
        return patientSaved;
    }

    public Patient put(Patient patient) {
        Patient patientSaved= patientRepository.save(patient);
        LOGGER.info("Patient put with id={}", patient.getId());
        return patientSaved;
    }

    public Patient patch(Long patientId, Patient patientToPatch) {
        Patient patientFound=patientRepository.findById(patientId).get();
        if (patientToPatch.getId()!=null) {
            patientFound.setId(patientToPatch.getId());
        }
        if (patientToPatch.getFirstname()!=null) {
            patientFound.setFirstname(patientToPatch.getFirstname());
        }
        if (patientToPatch.getSurname()!=null) {
            patientFound.setSurname(patientToPatch.getSurname());
        }
        if (patientToPatch.getDepartmentId()!=null) {
            patientFound.setDepartmentId(patientToPatch.getDepartmentId());
        }
        if (patientToPatch.getHospitalId()!=null) {
            patientFound.setHospitalId(patientToPatch.getHospitalId());
        }
        Patient patientPatched= patientRepository.save(patientFound);
        LOGGER.info("Patient patched with id={}", patientFound.getId());
        return patientPatched;
    }

    public void deleteById(Long patientId) {
        try {
            patientRepository.deleteById(patientId);
            LOGGER.info("Patient deleted with id={}", patientId);
        } catch (EmptyResultDataAccessException e){}
    }

    public List<Patient> getByDepartmentId(Long departmentId) {
        List<Patient> patientsFound=patientRepository.findByDepartmentId(departmentId);
        LOGGER.info("Patients found in department with id={}", departmentId);
        return patientsFound;
    }

    public List<Patient> getByHospitalId(Long hospitalId) {
        List<Patient> patientsFound=patientRepository.findByHospitalId(hospitalId);
        LOGGER.info("Patients found in hospital with id={}", hospitalId);
        return patientsFound;
    }


}
