package com.kuheindra.employees.config;

import com.kuheindra.employees.entity.Department;
import com.kuheindra.employees.repository.DepartmentRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader
        implements CommandLineRunner {

    private final DepartmentRepository repository;

    @Override
    public void run(String... args) {

        if (repository.count() == 0) {

            repository.save(
                    Department.builder()
                            .departmentName("IT")
                            .build());

            repository.save(
                    Department.builder()
                            .departmentName("HR")
                            .build());

            repository.save(
                    Department.builder()
                            .departmentName("Finance")
                            .build());
        }
    }
}