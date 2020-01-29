package com.paro.departmentservice.repository;

import com.paro.departmentservice.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findByHospitalId(Long hospitalId);
}
