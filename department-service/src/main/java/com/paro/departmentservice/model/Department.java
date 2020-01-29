package com.paro.departmentservice.model;

import lombok.*;

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
public class Department {
    @Id
    private Long id;
    private String name;
    private Long hospitalId;
    @Transient
    private List<Patient> patientList=new ArrayList<>();

}
