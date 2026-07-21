package com.workshop.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workshop.school.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}