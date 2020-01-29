package com.paro.departmentviewservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.paro.departmentviewservice.model.Department;
import com.paro.departmentviewservice.model.Patient;
import com.paro.departmentviewservice.repository.DepartmentRepository;
import com.paro.departmentviewservice.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentViewService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentViewService.class);
    private final DepartmentRepository departmentRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public DepartmentViewService(DepartmentRepository departmentRepository, PatientRepository patientRepository) {
        this.departmentRepository=departmentRepository;
        this.patientRepository = patientRepository;
    }

    @HystrixCommand(fallbackMethod = "getByHospitalWithPatients_Fallback")
    public List<Department> getByHospitalWithPatients(Long hospitalId){
        List<Department> departmentList=departmentRepository.findByHospitalId(hospitalId);
        for (Department department:departmentList) {
            List<Patient> patientList = patientRepository.findByDepartmentId(department.getDepartmentId());
            department.setPatientList(patientList);
        }

        return departmentList;
    }

    @SuppressWarnings("unused")
    private List<Department> getByHospitalWithPatients_Fallback(Long hospitalId){

        Department departmentNotFound=new Department(0L, "UNKNOWN", 0L);
        List<Department> departmentList=new ArrayList<>();
        List<Patient> patientListNotFound=new ArrayList<>();
        departmentList.add(departmentNotFound);
        for (Department department:departmentList) {
            Patient patientNotFound=new Patient(0L, "UNKNOWN", "UNKNOWN", hospitalId, department.getDepartmentId());
            patientListNotFound.add(patientNotFound);
            department.setPatientList(patientListNotFound);
        }

       return departmentList;
    }

}
