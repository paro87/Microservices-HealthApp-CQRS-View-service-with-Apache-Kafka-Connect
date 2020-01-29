package com.paro.departmentviewservice.repository;

import com.paro.departmentviewservice.model.Patient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PatientRepository extends CrudRepository<Patient, Long> {
    List<Patient> findByDepartmentId(Long departmentId);
}
