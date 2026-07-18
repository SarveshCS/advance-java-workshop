package com.workshop.springbootdemo.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.workshop.springbootdemo.model.Course;
import com.workshop.springbootdemo.model.EnrollmentResult;
import com.workshop.springbootdemo.repository.CourseRepository;

@Service
public class EnrollmentService {
    private final CourseRepository courseRepository;

    public EnrollmentService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public EnrollmentResult enrollStudent(String studentName, String courseCode) {
        if (isBlank(studentName) || isBlank(courseCode)) {
            return new EnrollmentResult(studentName, courseCode, false, "Student name and course code are required.");
        }

        Course course = courseRepository.findByCode(courseCode);

        if (course == null) {
            return new EnrollmentResult(studentName, courseCode, false, "Course does not exist.");
        }

        if (!course.hasAvailableSeat()) {
            return new EnrollmentResult(studentName, courseCode, false, "Course is full.");
        }

        course.enrollOneStudent();
        return new EnrollmentResult(studentName, course.getCode(), true, "Enrollment successful.");
    }

    public String addCourse(String courseCode, String courseTitle, int seatLimit) {
        if (isBlank(courseCode) || isBlank(courseTitle)) {
            return "Course code and title are required.";
        }

        String normalizedCourseCode = courseCode.toUpperCase();

        if (seatLimit <= 0) {
            return "Seat limit must be greater than zero.";
        }

        if (courseRepository.existsByCode(normalizedCourseCode)) {
            return "Course already exists.";
        }

        courseRepository.save(new Course(normalizedCourseCode, courseTitle, seatLimit));
        return "Course added successfully.";
    }

    public Collection<Course> getCourses() {
        return courseRepository.findAll();
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}