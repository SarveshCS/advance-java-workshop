package com.workshop.course.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.workshop.course.exception.CourseNotFoundException;
import com.workshop.course.model.Course;

@Service
public class CourseService {
    private final List<Course> courses = List.of(
            new Course(1, "CS301", "Advanced Java", "Dr. Neha Sharma", 4),
            new Course(2, "CS302", "Data Structures", "Prof. Amit Rao", 3),
            new Course(3, "CS303", "Database Management Systems", "Dr. Kavita Joshi", 3));

    public List<Course> getAllCourses() {
        return courses;
    }

    public Course getCourseById(int id) {
        return courses.stream()
                .filter(course -> course.getId() == id)
                .findFirst()
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id " + id));
    }
}
