package com.workshop.springdemo.repository;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import com.workshop.springdemo.model.Course;

public class CourseRepository {
    private final Map<String, Course> courses = new LinkedHashMap<>();

    public CourseRepository() {
        save(new Course("SPRING", "Spring Framework Basics", 2));
        save(new Course("JDBC", "JDBC With MySQL", 3));
        save(new Course("SERVLET", "Servlet and JSP Revision", 2));
    }

    public Course findByCode(String courseCode) {
        return courses.get(courseCode.toUpperCase());
    }

    public Collection<Course> findAll() {
        return courses.values();
    }

    public boolean existsByCode(String courseCode) {
        return courses.containsKey(courseCode.toUpperCase());
    }

    public void save(Course course) {
        courses.put(course.getCode(), course);
    }
}