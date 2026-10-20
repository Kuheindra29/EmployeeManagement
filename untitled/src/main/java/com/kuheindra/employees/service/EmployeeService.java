package com.kuheindra.employees.service;

import com.kuheindra.employees.dto.EmployeeRequest;
import com.kuheindra.employees.dto.EmployeeResponse;
import org.springframework.data.domain.Page;

public interface EmployeeService {

    EmployeeResponse createEmployee(
            EmployeeRequest request);

    EmployeeResponse getEmployee(
            Long id);

    EmployeeResponse updateEmployee(
            Long id,
            EmployeeRequest request);

    void deleteEmployee(
            Long id);

    Page<EmployeeResponse> searchEmployees(
            String department,
            int page,
            int size);

    String pingGoogle();
}