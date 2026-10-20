package com.kuheindra.employees.service.impl;

import com.kuheindra.employees.dto.DepartmentResponse;
import com.kuheindra.employees.entity.Department;
import com.kuheindra.employees.exception.ResourceNotFoundException;
import com.kuheindra.employees.repository.DepartmentRepository;
import com.kuheindra.employees.service.DepartmentService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl
        implements DepartmentService {

    private final DepartmentRepository repository;

    @Override
    public List<DepartmentResponse>
    getAllDepartments() {

        return repository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public DepartmentResponse
    getDepartment(Long id) {

        Department department =
                repository.findById(id)
                        .orElseThrow(
                                () ->
                                        new ResourceNotFoundException(
                                                "Department not found"));

        return toDto(department);
    }

    private DepartmentResponse
    toDto(Department department) {

        return new DepartmentResponse(
                department.getId(),
                department.getDepartmentName()
        );
    }
}