package com.paro.hospitalviewservice.repository;

import com.paro.hospitalviewservice.model.Hospital;
import org.springframework.data.repository.CrudRepository;

public interface HospitalRepository extends CrudRepository<Hospital, Long> {
    Hospital findByHospitalId(Long hospitalId);
}
