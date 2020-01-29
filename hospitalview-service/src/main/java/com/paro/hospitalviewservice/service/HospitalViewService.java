package com.paro.hospitalviewservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.paro.hospitalviewservice.model.Department;
import com.paro.hospitalviewservice.model.Hospital;
import com.paro.hospitalviewservice.model.Patient;
import com.paro.hospitalviewservice.repository.DepartmentRepository;
import com.paro.hospitalviewservice.repository.HospitalRepository;
import com.paro.hospitalviewservice.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class HospitalViewService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HospitalViewService.class);

    private final HospitalRepository hospitalRepository;
    private final DepartmentRepository departmentRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public HospitalViewService(HospitalRepository hospitalRepository, DepartmentRepository departmentRepository, PatientRepository patientRepository) {
        this.hospitalRepository = hospitalRepository;
        this.departmentRepository = departmentRepository;
        this.patientRepository=patientRepository;
    }

    @HystrixCommand(fallbackMethod = "getHospitalWithDepartments_Fallback")
    public Hospital getHospitalWithDepartments(Long hospitalId) {
        Hospital hospital = hospitalRepository.findByHospitalId(hospitalId);
        List<Department> departmentList= departmentRepository.findByHospitalId(hospital.getHospitalId());
        hospital.setDepartmentList(departmentList);
        LOGGER.info("Departments found with hospital id={}", hospitalId);
        return hospital;
    }

    @HystrixCommand(fallbackMethod = "getHospitalWithDepartmentsAndPatients_Fallback")
    public Hospital getHospitalWithDepartmentsAndPatients(Long hospitalId) {
        Hospital hospital = hospitalRepository.findByHospitalId(hospitalId);
        List<Department> departmentList=departmentRepository.findByHospitalId(hospital.getHospitalId());
        for (Department department:departmentList) {
            List<Patient> patientList = patientRepository.findByDepartmentId(department.getDepartmentId());
            department.setPatientList(patientList);
        }
        hospital.setDepartmentList(departmentList);
        LOGGER.info("Departments and patients found with hospital id={}", hospitalId);
        return hospital;
    }

    @HystrixCommand(fallbackMethod = "getHospitalWithPatients_Fallback")
    public Hospital getHospitalWithPatients(Long hospitalId) {
        Hospital hospital = hospitalRepository.findByHospitalId(hospitalId);
        List<Patient> patientList=patientRepository.findByHospitalId(hospital.getHospitalId());
        hospital.setPatientList(patientList);
        LOGGER.info("Patients found with hospital id={}", hospitalId);
        return hospital;
    }

    @SuppressWarnings("unused")
    private Hospital getHospitalWithDepartments_Fallback(Long hospitalId) {
        Hospital hospital = hospitalRepository.findById(hospitalId).orElse(null);
        Department departmentNotFound = new Department(0L, "UNKNOWN", 0L);
        List<Department> departmentListNotFound = new ArrayList<>();
        departmentListNotFound.add(departmentNotFound);
        hospital.setDepartmentList(departmentListNotFound);
        return hospital;
    }

    @SuppressWarnings("unused")
    private Hospital getHospitalWithDepartmentsAndPatients_Fallback(Long hospitalId) {
        Hospital hospital = hospitalRepository.findById(hospitalId).orElse(null);
        Department departmentNotFound=new Department(0L, "UNKNOWN", hospitalId);
        List<Department> departmentList=new ArrayList<>();
        List<Patient> patientListNotFound=new ArrayList<>();
        departmentList.add(departmentNotFound);
        for (Department department:departmentList) {
            Patient patientNotFound=new Patient(0L, "UNKNOWN", "UNKNOWN", hospitalId, department.getDepartmentId());
            patientListNotFound.add(patientNotFound);
            department.setPatientList(patientListNotFound);
        }
        hospital.setDepartmentList(departmentList);
        return hospital;
    }

    @SuppressWarnings("unused")
    private Hospital getHospitalWithPatients_Fallback(Long hospitalId) {
        Hospital hospital = hospitalRepository.findById(hospitalId).orElse(null);
        Patient patientNotFound = new Patient(0L, "UNKNOWN", "UNKNOWN", hospitalId, 0L);
        List<Patient> patientListNotFound = new ArrayList<>();
        patientListNotFound.add(patientNotFound);
        hospital.setPatientList(patientListNotFound);
        return hospital;
    }
}
