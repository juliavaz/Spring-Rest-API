package com.example.payroll.demo.config;

import com.example.payroll.demo.Model.Employee;
import com.example.payroll.demo.Repository.EmployeeRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository) {
        // expressão lambda
        return args -> {
            log.info("Preloading " + repository.save(new Employee("Julia Vaz", "Developer")));
            log.info("Preloading " + repository.save(new Employee("Fulano", "Developer")));
        };
    }
}
