package com.paro.patientservice.controller;

import com.paro.patientservice.model.Patient;
import com.paro.patientservice.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/service", produces = "application/json")   //Will only handle requests if the request’s Accept header includes “application/json”
@CrossOrigin(origins="*")                                           //Allows clients from any domain to consume the API, especially for frontend
public class PatientController {

    private final PatientService patientService;
    @Autowired
    public PatientController(PatientService patientService){
        this.patientService=patientService;
    }

    @GetMapping(value = "/")
    public List<Patient> getAll(){
        List<Patient> patientsFound=patientService.getAll();
        return patientsFound;
    }

    @GetMapping(value = "/{id}")
    public Patient getById(@PathVariable("id") Long patientId){
        Patient patientFound=patientService.getById(patientId);
        return patientFound;
    }

    @PostMapping(value = "/", consumes = "application/json")    //Will only handle requests whose Content-type matches application/json
    @ResponseStatus(code = HttpStatus.CREATED)
    public Patient add(@RequestBody Patient patient){
        //OLD
        //patientService.add(patient);
        //Patient patientAdded= patientService.getById(patient.getId());

        //NEW
        Patient patientAdded=patientService.add(patient);
        return patientAdded;
    }

    @PutMapping(value = "/{id}")
    public Patient putById(@RequestBody Patient patient){
        Patient patientPut=patientService.put(patient);
        return patientPut;
    }

    @PatchMapping(value = "/{id}", consumes = "application/json")
    public Patient patchById(@PathVariable("id") Long patientId, @RequestBody Patient patient){
        Patient patientPatched=patientService.patch(patientId, patient);
        return patientPatched;
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)   // 204
    public void deleteById(@PathVariable("id") Long patientId) {
        patientService.deleteById(patientId);
    }

    @GetMapping(value = "/department/{departmentId}")
    public List<Patient> getByDepartment(@PathVariable("departmentId") Long departmentId){
        List<Patient> patientsFound=patientService.getByDepartmentId(departmentId);
        return patientsFound;
    }

    @GetMapping(value="/hospital/{hospitalId}")
    public List<Patient> getByHospital(@PathVariable("hospitalId") Long hospitalId) {
        List<Patient> patientsFound=patientService.getByHospitalId(hospitalId);
        return patientsFound;
    }
}
