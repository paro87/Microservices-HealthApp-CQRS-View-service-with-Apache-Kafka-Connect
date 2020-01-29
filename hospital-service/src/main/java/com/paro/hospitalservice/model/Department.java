package com.paro.hospitalservice.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Department {
    private Long id;
    private String name;
    private Long hospitalId;
    private List<Patient> patientList=new ArrayList<>();



}
