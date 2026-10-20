package com.kuheindra.employees.repository;

import com.kuheindra.employees.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository
        extends JpaRepository<Employee, Long> {

    @Query("""
          SELECT e
          FROM Employee e
          JOIN e.department d
          WHERE lower(d.departmentName)
          LIKE lower(concat('%',:department,'%'))
          """)
    Page<Employee> searchByDepartment(
            @Param("department")
            String department,
            Pageable pageable);
}