package com.paro.hospitalviewservice.repository;

import com.paro.hospitalviewservice.model.Department;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
    List<Department> findByHospitalId(Long hospitalId);
}
