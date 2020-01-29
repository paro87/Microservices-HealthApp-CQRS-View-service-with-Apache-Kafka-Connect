package com.paro.patientservice.repository;

import com.paro.patientservice.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByDepartmentId(Long departmentId);

    List<Patient> findByHospitalId(Long hospitalId);
}
