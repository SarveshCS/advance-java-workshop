package com.workshop.course.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Student {
    private int studentId = 101;
    private String studentName = "Aarav Sharma";

    @Autowired
    private Course course;

    public int getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public Course getCourse() {
        return course;
    }

    public void displayStudentDetails() {
        System.out.println("Student Details");
        System.out.println("Student ID: " + studentId);
        System.out.println("Student Name: " + studentName);
        System.out.println("Course ID: " + course.getCourseId());
        System.out.println("Course Name: " + course.getCourseName());
        System.out.println("Faculty Name: " + course.getFacultyName());
    }
}