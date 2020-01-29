package com.paro.hospitalservice.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Hospital {
    @Id
    private Long id;
    private String name;
    private String address;
    @Transient
    private List<Department> departmentList=new ArrayList<>();
    @Transient
    private List<Patient> patientList=new ArrayList<>();
}
