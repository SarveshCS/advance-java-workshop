package com.workshop.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workshop.student.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
