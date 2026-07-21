package com.workshop.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workshop.school.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}