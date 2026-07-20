package com.workshop.employee.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.workshop.employee.model.Employee;

public class EmployeeDAO {
    private final JdbcTemplate jdbcTemplate;

    public EmployeeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Employee findEmployeeById(int employeeId) {
        String sql = "SELECT employee_id, name, department, salary FROM employee WHERE employee_id = ?";

        try {
            return jdbcTemplate.queryForObject(sql, (resultSet, rowNumber) -> {
                Employee employee = new Employee();
                employee.setEmployeeId(resultSet.getInt("employee_id"));
                employee.setName(resultSet.getString("name"));
                employee.setDepartment(resultSet.getString("department"));
                employee.setSalary(resultSet.getDouble("salary"));
                return employee;
            }, employeeId);
        } catch (EmptyResultDataAccessException exception) {
            return null;
        }
    }
}