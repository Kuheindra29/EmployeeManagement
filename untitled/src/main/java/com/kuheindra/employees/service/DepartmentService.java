package com.kuheindra.employees.service;

import com.kuheindra.employees.dto.DepartmentResponse;

import java.util.List;

public interface DepartmentService {

    List<DepartmentResponse> getAllDepartments();

    DepartmentResponse getDepartment(
            Long id);
}