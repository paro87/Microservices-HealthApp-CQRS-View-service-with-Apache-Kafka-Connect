package com.paro.hospitalviewservice.repository;

import com.paro.hospitalviewservice.model.Patient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PatientRepository extends CrudRepository<Patient, Long> {
    List<Patient> findByDepartmentId(Long departmentId);
    List<Patient> findByHospitalId(Long hospitalId);
}
