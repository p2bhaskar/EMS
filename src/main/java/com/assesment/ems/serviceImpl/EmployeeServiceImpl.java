package com.assesment.ems.serviceImpl;

import com.assesment.ems.entity.Employee;
import com.assesment.ems.exception.EmployeeNotFoundException;
import com.assesment.ems.repository.EmployeeRepository;
import com.assesment.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Optional<Employee> existingEmployee = employeeRepository.findById(id);
        if (existingEmployee.isPresent()) {
            Employee updatedEmployee = existingEmployee.get();
            updatedEmployee.setName(employee.getName());
            updatedEmployee.setEmail(employee.getEmail());
            updatedEmployee.setDepartment(employee.getDepartment());
            updatedEmployee.setDesignation(employee.getDesignation());
            updatedEmployee.setJoiningDate(employee.getJoiningDate());
            updatedEmployee.setSalary(employee.getSalary());
            return employeeRepository.save(updatedEmployee);
        } else {
            throw new EmployeeNotFoundException("Employee with ID " + id + " not found.");
        }
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return Optional.of(employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found.")));
    }

    @Override
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException("Employee with ID " + id + " not found.");
        }
        employeeRepository.deleteById(id);
    }

    @Override
    public Page<Employee> searchEmployeesByName(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return employeeRepository.findByNameContainingIgnoreCase(name, pageable);
    }

    @Override
    public Page<Employee> filterEmployeesByDepartment(String department, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return employeeRepository.findByDepartment(department, pageable);
    }

    @Override
    public Page<Employee> getPaginatedEmployees(int page, int size) {
        return employeeRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public List<String> getAllDepartments() {
        return employeeRepository.findAll().stream()
                .map(Employee::getDepartment)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    // These methods are kept for backward compatibility
    @Override
    public List<Employee> searchEmployeesByName(String name) {
        return employeeRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Employee> filterEmployeesByDepartment(String department) {
        return employeeRepository.findByDepartment(department);
    }
}