package com.workshop.school.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String email;
    private String phone;
    private String qualification;
    private String assignedSubject;

    public Teacher() {
    }

    public Teacher(String name, String email, String phone, String qualification, String assignedSubject) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.qualification = qualification;
        this.assignedSubject = assignedSubject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getAssignedSubject() {
        return assignedSubject;
    }

    public void setAssignedSubject(String assignedSubject) {
        this.assignedSubject = assignedSubject;
    }
}