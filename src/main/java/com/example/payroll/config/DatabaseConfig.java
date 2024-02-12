package com.example.payroll.config;


import com.example.payroll.models.Employee;
import com.example.payroll.repositories.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class DatabaseConfig {

    private static final Logger log = LoggerFactory.getLogger(DatabaseConfig.class);

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Employee("John", "Smith", "CTO", 125000.00)));
            log.info("Preloading " + repository.save(new Employee("Tim", "Scott", "CEO", 130000.00)));
        };
    }

}
