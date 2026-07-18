package com.workshop.springbootdemo.controller;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.workshop.springbootdemo.dto.CourseRequest;
import com.workshop.springbootdemo.dto.EnrollmentRequest;
import com.workshop.springbootdemo.model.Course;
import com.workshop.springbootdemo.model.EnrollmentResult;
import com.workshop.springbootdemo.service.EnrollmentService;

@RestController
public class CourseController {
    private final EnrollmentService enrollmentService;

    public CourseController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping("/")
    public String home() {
        return "Spring Boot Course API is running. Visit /courses to view available courses.";
    }

    @GetMapping("/courses")
    public Collection<Course> getCourses() {
        return enrollmentService.getCourses();
    }

    @PostMapping("/courses")
    public ResponseEntity<String> addCourse(@RequestBody CourseRequest request) {
        String message = enrollmentService.addCourse(request.getCourseCode(), request.getCourseTitle(), request.getSeatLimit());

        if ("Course added successfully.".equals(message)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        }

        return ResponseEntity.badRequest().body(message);
    }

    @PostMapping("/enrollments")
    public ResponseEntity<EnrollmentResult> enrollStudent(@RequestBody EnrollmentRequest request) {
        EnrollmentResult result = enrollmentService.enrollStudent(request.getStudentName(), request.getCourseCode());

        if (result.isSuccessful()) {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }
}