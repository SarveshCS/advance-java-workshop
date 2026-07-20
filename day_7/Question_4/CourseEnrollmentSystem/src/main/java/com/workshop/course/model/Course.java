package com.workshop.course.model;

import org.springframework.stereotype.Component;

@Component
public class Course {
    private int courseId = 501;
    private String courseName = "Advanced Java";
    private String facultyName = "Dr. Sharma";

    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getFacultyName() {
        return facultyName;
    }
}