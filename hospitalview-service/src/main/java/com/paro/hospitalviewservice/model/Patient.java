package com.paro.hospitalviewservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@ToString(exclude = "id")
@Getter
@Setter
@Document(collection = "HospitalView-patient")
public class Patient {
    @Id
    private String id;
    private Long patientId;
    private String firstname;
    private String surname;
    private Long hospitalId;
    private Long departmentId;

    public Patient(Long patientId, String firstname, String surname, Long hospitalId, Long departmentId) {
        this.patientId = patientId;
        this.firstname = firstname;
        this.surname = surname;
        this.hospitalId = hospitalId;
        this.departmentId = departmentId;
    }

}
