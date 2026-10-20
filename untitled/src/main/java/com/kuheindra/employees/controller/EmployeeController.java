package com.kuheindra.employees.controller;

import com.kuheindra.employees.dto.EmployeeRequest;
import com.kuheindra.employees.dto.EmployeeResponse;
import com.kuheindra.employees.service.EmployeeService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeResponse>
    createEmployee(
            @Valid
            @RequestBody
            EmployeeRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        employeeService
                                .createEmployee(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse>
    getEmployee(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                employeeService
                        .getEmployee(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse>
    updateEmployee(
            @PathVariable Long id,

            @Valid
            @RequestBody
            EmployeeRequest request) {

        return ResponseEntity.ok(
                employeeService
                        .updateEmployee(
                                id,
                                request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>
    deleteEmployee(
            @PathVariable Long id) {

        employeeService.deleteEmployee(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<EmployeeResponse>>
    searchEmployee(

            @RequestParam String department,

            @RequestParam(
                    defaultValue = "0")
            int page,

            @RequestParam(
                    defaultValue = "10")
            int size) {

        return ResponseEntity.ok(
                employeeService
                        .searchEmployees(
                                department,
                                page,
                                size));
    }

    @GetMapping("/google")
    public ResponseEntity<String>
    callGoogle() {

        return ResponseEntity.ok(
                employeeService.pingGoogle());
    }
}