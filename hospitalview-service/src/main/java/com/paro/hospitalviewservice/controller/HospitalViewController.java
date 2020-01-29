package com.paro.hospitalviewservice.controller;

import com.paro.hospitalviewservice.model.Hospital;
import com.paro.hospitalviewservice.service.HospitalViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/service", produces = "application/json")
//Will only handle requests if the request’s Accept header includes “application/json”, For Swagger UI: http://localhost:8091/swagger-ui.html
@CrossOrigin(origins = "*")
//Allows clients from any domain to consume the API, especially for frontend
public class HospitalViewController {

    private final HospitalViewService hospitalViewService;

    @Autowired
    public HospitalViewController(HospitalViewService hospitalViewService) {
        this.hospitalViewService = hospitalViewService;
    }

     @GetMapping(value = "/{id}/with-departments")
    public Hospital getHospitalWithDepartments(@PathVariable("id") Long hospitalId) {
        Hospital hospital = hospitalViewService.getHospitalWithDepartments(hospitalId);
        return hospital;
    }

    @GetMapping(value = "/{id}/with-departments-and-patients")
    public Hospital getHospitalWithDepartmentsAndPatients(@PathVariable("id") Long hospitalId) {
        Hospital hospital = hospitalViewService.getHospitalWithDepartmentsAndPatients(hospitalId);
        return hospital;
    }

    @GetMapping(value = "/{id}/with-patients")
    public Hospital getHospitalWithPatients(@PathVariable("id") Long hospitalId) {
        Hospital hospital = hospitalViewService.getHospitalWithPatients(hospitalId);
        return hospital;
    }

}
