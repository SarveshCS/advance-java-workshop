CREATE DATABASE IF NOT EXISTS NIET;

USE NIET;

CREATE TABLE IF NOT EXISTS employee (
    employee_id INT PRIMARY KEY,
    name VARCHAR(50),
    department VARCHAR(50),
    salary DOUBLE
);

INSERT INTO employee (employee_id, name, department, salary) VALUES
(101, 'Aarav Sharma', 'Computer Science', 45000),
(102, 'Ananya Gupta', 'Information Technology', 48000),
(103, 'Rohan Verma', 'Accounts', 38000),
(104, 'Priya Singh', 'Human Resources', 42000),
(105, 'Kunal Mehta', 'Sales', 40000)
ON DUPLICATE KEY UPDATE
name = VALUES(name),
department = VALUES(department),
salary = VALUES(salary);