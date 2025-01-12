package com.assesment.ems.service;

import com.assesment.ems.entity.Employee;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    Employee updateEmployee(Long id, Employee employee);
    Optional<Employee> getEmployeeById(Long id);
    void deleteEmployee(Long id);

    // New paginated methods
    Page<Employee> searchEmployeesByName(String name, int page, int size);
    Page<Employee> filterEmployeesByDepartment(String department, int page, int size);
    Page<Employee> getPaginatedEmployees(int page, int size);

    // Department list
    List<String> getAllDepartments();

    // Existing methods
    List<Employee> getAllEmployees();
    List<Employee> searchEmployeesByName(String name);
    List<Employee> filterEmployeesByDepartment(String department);
}