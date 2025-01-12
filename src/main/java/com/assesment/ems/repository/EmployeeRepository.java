package com.assesment.ems.repository;

import com.assesment.ems.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Paginated methods
    Page<Employee> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Employee> findByDepartment(String department, Pageable pageable);


    List<Employee> findByNameContainingIgnoreCase(String name);
    List<Employee> findByDepartment(String department);
}