package com.workshop.university.model;

import java.util.List;

public class University {
    private String universityName;
    private List<String> departments;

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public List<String> getDepartments() {
        return departments;
    }

    public void setDepartments(List<String> departments) {
        this.departments = departments;
    }

    public void displayDepartments() {
        System.out.println("University Name: " + universityName);
        System.out.println("Departments:");

        for (String department : departments) {
            System.out.println(department);
        }
    }
}