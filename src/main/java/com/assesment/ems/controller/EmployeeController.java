package com.assesment.ems.controller;

import com.assesment.ems.entity.Employee;
import com.assesment.ems.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Add an employee
    @PostMapping
    public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.saveEmployee(employee));
    }

    // Update an employee
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Long id,
            @Validated @RequestBody Employee employee) {
        System.out.println(id);
        return ResponseEntity.ok(employeeService.updateEmployee(id, employee));
    }

    // Delete an employee
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    // Get employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Search employees by name
    @GetMapping("/search")
    public ResponseEntity<List<Employee>> searchEmployeesByName(@RequestParam String name) {
        return ResponseEntity.ok(employeeService.searchEmployeesByName(name));
    }

    // Filter employees by department
    @GetMapping("/filter")
    public ResponseEntity<List<Employee>> filterEmployeesByDepartment(@RequestParam String department) {
        return ResponseEntity.ok(employeeService.filterEmployeesByDepartment(department));
    }

    // Get paginated employees
    @GetMapping("/paginated")
    public ResponseEntity<Page<Employee>> getPaginatedEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(employeeService.getPaginatedEmployees(page, size));
    }

    // Bulk insert employees
    @PostMapping("/bulk")
    public ResponseEntity<List<Employee>> createEmployees(@RequestBody List<Employee> employees) {
        List<Employee> savedEmployees = employees.stream()
                .map(employeeService::saveEmployee)
                .toList();
        return ResponseEntity.ok(savedEmployees);
    }

}

