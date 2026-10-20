package com.kuheindra.employees.service.impl;

import com.kuheindra.employees.client.GoogleClient;
import com.kuheindra.employees.dto.EmployeeRequest;
import com.kuheindra.employees.dto.EmployeeResponse;
import com.kuheindra.employees.entity.Department;
import com.kuheindra.employees.entity.Employee;
import com.kuheindra.employees.exception.ResourceNotFoundException;
import com.kuheindra.employees.mapper.EmployeeMapper;
import com.kuheindra.employees.repository.DepartmentRepository;
import com.kuheindra.employees.repository.EmployeeRepository;
import com.kuheindra.employees.service.EmployeeService;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl
        implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final DepartmentRepository departmentRepository;

    private final EmployeeMapper mapper;

    private final GoogleClient googleClient;

    @Override
    public EmployeeResponse createEmployee(
            EmployeeRequest request) {

        Department department =
                departmentRepository.findById(
                                request.departmentId())
                        .orElseThrow(
                                () -> new ResourceNotFoundException(
                                        "Department not found"));

        Employee employee =
                Employee.builder()
                        .firstName(
                                request.firstName())
                        .lastName(
                                request.lastName())
                        .email(
                                request.email())
                        .department(
                                department)
                        .build();

        employee =
                employeeRepository.save(employee);

        return mapper.toResponse(employee);
    }

    @Override
    public EmployeeResponse getEmployee(
            Long id) {

        Employee employee =
                employeeRepository
                        .findById(id)
                        .orElseThrow(
                                () ->
                                        new ResourceNotFoundException(
                                                "Employee not found"));

        return mapper.toResponse(employee);
    }

    @Override
    public EmployeeResponse updateEmployee(
            Long id,
            EmployeeRequest request) {

        Employee employee =
                employeeRepository.findById(id)
                        .orElseThrow(
                                () ->
                                        new ResourceNotFoundException(
                                                "Employee not found"));

        Department department =
                departmentRepository.findById(
                                request.departmentId())
                        .orElseThrow(
                                () ->
                                        new ResourceNotFoundException(
                                                "Department not found"));

        employee.setFirstName(
                request.firstName());

        employee.setLastName(
                request.lastName());

        employee.setEmail(
                request.email());

        employee.setDepartment(
                department);

        employee =
                employeeRepository.save(employee);

        return mapper.toResponse(employee);
    }

    @Override
    public void deleteEmployee(
            Long id) {

        employeeRepository.deleteById(id);
    }

    @Override
    public Page<EmployeeResponse>
    searchEmployees(
            String department,
            int page,
            int size) {

        Pageable pageable =
                PageRequest.of(
                        page,
                        size,
                        Sort.by("firstName"));

        return employeeRepository
                .searchByDepartment(
                        department,
                        pageable)
                .map(mapper::toResponse);
    }

    @Override
    public String pingGoogle() {

        return googleClient.pingGoogle();
    }
}
