package com.workshop.springdemo.model;

public class EnrollmentResult {
    private final String studentName;
    private final String courseCode;
    private final boolean successful;
    private final String message;

    public EnrollmentResult(String studentName, String courseCode, boolean successful, String message) {
        this.studentName = studentName;
        this.courseCode = courseCode;
        this.successful = successful;
        this.message = message;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public String getMessage() {
        return message;
    }
}