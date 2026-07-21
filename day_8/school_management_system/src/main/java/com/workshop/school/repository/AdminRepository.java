package com.workshop.school.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workshop.school.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByUsername(String username);
}