package com.workshop.springdemo.service;

import com.workshop.springdemo.model.Course;
import com.workshop.springdemo.model.EnrollmentResult;

public class ReportService {
    private final EnrollmentService enrollmentService;

    public ReportService(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    public void printEnrollment(EnrollmentResult result) {
        String status = result.isSuccessful() ? "SUCCESS" : "FAILED";
        System.out.println(status + " | " + result.getStudentName() + " -> " + result.getCourseCode()
                + " | " + result.getMessage());
    }

    public void printCourseSummary() {
        System.out.println();
        System.out.println("Available Courses");
        System.out.println("-----------------");

        for (Course course : enrollmentService.getCourses()) {
            System.out.println(course.getCode() + " | " + course.getTitle() + " | Enrolled: "
                    + course.getEnrolledStudents() + "/" + course.getSeatLimit() + " | Seats left: "
                    + course.getAvailableSeats());
        }
    }
}