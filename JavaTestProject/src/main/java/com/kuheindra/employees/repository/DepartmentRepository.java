package com.kuheindra.employees.repository;

import com.kuheindra.employees.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository
        extends JpaRepository<Department, Long> {
}