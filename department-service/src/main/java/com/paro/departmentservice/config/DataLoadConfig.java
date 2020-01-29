package com.paro.departmentservice.config;

import com.paro.departmentservice.model.Department;
import com.paro.departmentservice.repository.DepartmentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!production")
@Configuration
public class DataLoadConfig {

    @Bean
    public CommandLineRunner dataLoader(DepartmentRepository departmentRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                departmentRepository.save(new Department(11L, "Cardiology", 1L, null));
                departmentRepository.save(new Department(12L, "Neurology", 1L, null));
                departmentRepository.save(new Department(13L, "Oncology", 1L, null));

            };

        };
    }
}




