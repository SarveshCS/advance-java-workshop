package com.workshop.school.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workshop.school.model.SchoolClass;

public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {
    List<SchoolClass> findByClassTeacherId(Long classTeacherId);
}