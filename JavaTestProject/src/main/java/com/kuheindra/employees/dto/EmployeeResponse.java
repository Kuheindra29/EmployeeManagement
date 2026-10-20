package com.kuheindra.employees.dto;

public record EmployeeResponse(

        Long id,

        String firstName,

        String lastName,

        String email,

        String departmentName
) {
}