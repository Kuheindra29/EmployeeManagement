package com.kuheindra.employees.mapper;

import com.kuheindra.employees.dto.EmployeeResponse;
import com.kuheindra.employees.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring"
)
public interface EmployeeMapper {

    @Mapping(
            target = "departmentName",
            source = "department.departmentName"
    )
    EmployeeResponse toResponse(
            Employee employee);
}