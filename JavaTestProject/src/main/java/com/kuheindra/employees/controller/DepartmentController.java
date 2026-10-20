package com.kuheindra.employees.controller;

import com.kuheindra.employees.dto.DepartmentResponse;
import com.kuheindra.employees.service.DepartmentService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<DepartmentResponse>>
    getAllDepartments() {

        return ResponseEntity.ok(
                departmentService.getAllDepartments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponse>
    getDepartment(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                departmentService.getDepartment(id));
    }
}