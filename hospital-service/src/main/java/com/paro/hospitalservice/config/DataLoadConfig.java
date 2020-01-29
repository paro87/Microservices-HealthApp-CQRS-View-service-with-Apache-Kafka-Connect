package com.paro.hospitalservice.config;

import com.paro.hospitalservice.model.Hospital;
import com.paro.hospitalservice.repository.HospitalRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!production")
@Configuration
public class DataLoadConfig {

    @Bean
    public CommandLineRunner dataLoader(HospitalRepository hospitalRepository) {

        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                hospitalRepository.save(new Hospital(1L, "Mayo Clinic", "Rochester", null, null));
                hospitalRepository.save(new Hospital(2L, "Massachusetts", "Boston", null, null));
                hospitalRepository.save(new Hospital(3L, "Johns Hopkins", "Baltimore", null ,null));
            };

        };
    }
}




