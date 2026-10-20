package com.kuheindra.employees.service;

import com.kuheindra.employees.client.GoogleClient;
import com.kuheindra.employees.dto.EmployeeRequest;
import com.kuheindra.employees.dto.EmployeeResponse;
import com.kuheindra.employees.entity.Department;
import com.kuheindra.employees.entity.Employee;
import com.kuheindra.employees.mapper.EmployeeMapper;
import com.kuheindra.employees.repository.DepartmentRepository;
import com.kuheindra.employees.repository.EmployeeRepository;
import com.kuheindra.employees.service.impl.EmployeeServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private EmployeeMapper mapper;

    @Mock
    private GoogleClient googleClient;

    @InjectMocks
    private EmployeeServiceImpl service;

    @Test
    void shouldCreateEmployee() {

        Department department =
                Department.builder()
                        .id(1L)
                        .departmentName("IT")
                        .build();

        Employee employee =
                Employee.builder()
                        .id(1L)
                        .firstName("John")
                        .build();

        EmployeeRequest request =
                new EmployeeRequest(
                        "John",
                        "Smith",
                        "john@gmail.com",
                        1L);

        EmployeeResponse response =
                new EmployeeResponse(
                        1L,
                        "John",
                        "Smith",
                        "john@gmail.com",
                        "IT");

        when(departmentRepository.findById(1L))
                .thenReturn(Optional.of(department));

        when(employeeRepository.save(any(Employee.class)))
                .thenReturn(employee);

        when(mapper.toResponse(employee))
                .thenReturn(response);

        EmployeeResponse result =
                service.createEmployee(request);

        assertNotNull(result);
        assertEquals(1L, result.id());
    }
}