package com.workshop.employee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.workshop.employee.dao.EmployeeDAO;
import com.workshop.employee.model.Employee;

@Controller
public class EmployeeController {
    private final EmployeeDAO employeeDAO;

    public EmployeeController(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @GetMapping("/")
    public String showSearchPage() {
        return "search";
    }

    @PostMapping("/search")
    public String searchEmployee(@RequestParam("employeeId") int employeeId, Model model) {
        Employee employee = employeeDAO.findEmployeeById(employeeId);

        if (employee == null) {
            model.addAttribute("message", "Employee not found.");
        } else {
            model.addAttribute("employee", employee);
        }

        return "employee";
    }
}