

package com.assesment.ems.controller;

import com.assesment.ems.entity.Employee;
import com.assesment.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String home(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        model.addAttribute("employees", employeeService.getPaginatedEmployees(page, size));
        model.addAttribute("departments", employeeService.getAllDepartments());
        model.addAttribute("content", "employee-list");
        model.addAttribute("currentUrl", "/");
        return "base-layout";
    }

    @GetMapping("/search")
    public String searchEmployees(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        model.addAttribute("employees", employeeService.searchEmployeesByName(name, page, size));
        model.addAttribute("departments", employeeService.getAllDepartments());
        model.addAttribute("searchTerm", name);
        model.addAttribute("content", "employee-list");
        model.addAttribute("currentUrl", "/search");
        return "base-layout";
    }

    @GetMapping("/filter")
    public String filterEmployees(
            @RequestParam String department,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        model.addAttribute("employees", employeeService.filterEmployeesByDepartment(department, page, size));
        model.addAttribute("departments", employeeService.getAllDepartments());
        model.addAttribute("selectedDepartment", department);
        model.addAttribute("content", "employee-list");
        model.addAttribute("currentUrl", "/filter");
        return "base-layout";
    }

    @GetMapping("/add-employee")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("content", "employee-form");
        return "base-layout";
    }

    @GetMapping("/edit-employee")
    public String showEditForm(@RequestParam Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        model.addAttribute("employee", employee);
        model.addAttribute("content", "employee-form");
        return "base-layout";
    }
}