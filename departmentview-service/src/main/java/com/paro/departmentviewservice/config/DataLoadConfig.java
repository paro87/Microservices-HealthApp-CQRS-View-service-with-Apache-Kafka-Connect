package com.paro.departmentviewservice.config;

import com.paro.departmentviewservice.model.Department;
import com.paro.departmentviewservice.model.Patient;
import com.paro.departmentviewservice.repository.DepartmentRepository;
import com.paro.departmentviewservice.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!production")
@Configuration
public class DataLoadConfig {

    @Bean
    public CommandLineRunner dataLoader(PatientRepository patientRepository, DepartmentRepository departmentRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                patientRepository.save(new Patient(1L, "John", "Graham", 1L, 11L));
                patientRepository.save(new Patient(2L, "Mary", "Adams", 1L, 11L));
                patientRepository.save(new Patient(3L, "Adam", "Allison", 1L, 11L));
                patientRepository.save(new Patient(4L, "Brad", "Richards", 1L, 12L));
                patientRepository.save(new Patient(5L, "Alan", "Johnson", 1L, 12L));
                patientRepository.save(new Patient(6L, "Jessica", "Alba", 1L, 13L));

                departmentRepository.save(new Department(11L, "Cardiology", 1L));
                departmentRepository.save(new Department(12L, "Neurology", 1L));
                departmentRepository.save(new Department(13L, "Oncology", 1L));
            };

        };
    }

    @Bean
    public CommandLineRunner dataLoader(PatientRepository patientRepository) {

        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                patientRepository.save(new Patient(1L, "John", "Graham", 1L, 11L));
                patientRepository.save(new Patient(2L, "Mary", "Adams", 1L, 11L));
                patientRepository.save(new Patient(3L, "Adam", "Allison", 1L, 11L));
                patientRepository.save(new Patient(4L, "Brad", "Richards", 1L, 12L));
                patientRepository.save(new Patient(5L, "Alan", "Johnson", 1L, 12L));
                patientRepository.save(new Patient(6L, "Jessica", "Alba", 1L, 13L));
            };

        };
    }
}




