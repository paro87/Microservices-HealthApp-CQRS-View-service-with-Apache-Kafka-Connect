package com.paro.departmentservice.controller;
import com.paro.departmentservice.model.Department;
import com.paro.departmentservice.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/service", produces = "application/json")   //Will only handle requests if the request’s Accept header includes “application/json”, For Swagger UI: http://localhost:8091/swagger-ui.html
@CrossOrigin(origins="*")                                           //Allows clients from any domain to consume the API, especially for frontend
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService){
        this.departmentService=departmentService;
    }

    @GetMapping(value = "/")
    public List<Department> getAll() {
        List<Department> departmentList=departmentService.getAll();
        return departmentList;
    }

    @GetMapping(value = "/{id}")
    public Department getById(@PathVariable("id") Long departmentId){
        Department department=departmentService.getById(departmentId);
        return department;
    }
    @PostMapping(value = "/", consumes = "application/json")    //Will only handle requests whose Content-type matches application/json
    @ResponseStatus(code = HttpStatus.CREATED)
    public Department add(@RequestBody Department department){
        Department departmentAdd=departmentService.add(department);
        return departmentAdd;
    }

    @PutMapping(value = "/{id}")
    public Department putById(@RequestBody Department department){
        Department departmentPut=departmentService.put(department);
        return departmentPut;
    }

    @PatchMapping(value = "/{id}", consumes = "application/json")
    public Department patchById(@PathVariable("id") Long departmentId, @RequestBody Department department){
        Department departmentPatched=departmentService.patch(departmentId, department);
        return departmentPatched;
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)   // 204
    public void deleteById(@PathVariable("id") Long departmentId) {
        departmentService.deleteById(departmentId);
    }

    @GetMapping(value = "/hospital/{hospitalId}")
    public List<Department> getByHospital(@PathVariable("hospitalId") Long hospitalId){
        List<Department> departmentList=departmentService.getByHospitalId(hospitalId);
        return departmentList;
    }

}
