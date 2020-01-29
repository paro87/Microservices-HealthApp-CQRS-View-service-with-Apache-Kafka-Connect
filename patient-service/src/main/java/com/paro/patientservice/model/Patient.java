package com.paro.patientservice.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Patient {
    @Id
    @Column(name="patient_id")
    private Long id;
    @Column(name="patient_firstname")
    private String firstname;
    @Column(name="patient_surname")
    private String surname;
    @Column(name="hospital_id")
    private Long hospitalId;
    @Column(name = "department_id")
    private Long departmentId;
}
