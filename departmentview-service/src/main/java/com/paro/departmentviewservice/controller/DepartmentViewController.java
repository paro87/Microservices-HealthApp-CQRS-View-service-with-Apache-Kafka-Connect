package com.paro.departmentviewservice.controller;

import com.paro.departmentviewservice.model.Department;
import com.paro.departmentviewservice.service.DepartmentViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/service", produces = "application/json")   //Will only handle requests if the request’s Accept header includes “application/json”, For Swagger UI: http://localhost:8091/swagger-ui.html
@CrossOrigin(origins="*")                                           //Allows clients from any domain to consume the API, especially for frontend
public class DepartmentViewController {
    private final DepartmentViewService departmentViewService;

    @Autowired
    public DepartmentViewController(DepartmentViewService departmentViewService){
        this.departmentViewService = departmentViewService;
    }

    @GetMapping(value = "/hospital/{hospitalId}/with-patients")
    public List<Department> getByHospitalWithPatients(@PathVariable("hospitalId") Long hospitalId){
        List<Department> departmentList= departmentViewService.getByHospitalWithPatients(hospitalId);
        return departmentList;
    }
}
