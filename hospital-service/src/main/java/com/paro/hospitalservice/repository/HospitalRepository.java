package com.paro.hospitalservice.repository;

import com.paro.hospitalservice.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
}
