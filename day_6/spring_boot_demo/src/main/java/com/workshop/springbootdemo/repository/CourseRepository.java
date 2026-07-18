package com.workshop.springbootdemo.repository;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.workshop.springbootdemo.model.Course;

@Repository
public class CourseRepository {
    private final Map<String, Course> courses = new LinkedHashMap<>();

    public CourseRepository() {
        save(new Course("BOOT", "Spring Boot Fundamentals", 3));
        save(new Course("MVC", "Spring MVC Web Development", 2));
        save(new Course("DATA", "Spring Data Introduction", 4));
    }

    public Collection<Course> findAll() {
        return courses.values();
    }

    public Course findByCode(String courseCode) {
        if (courseCode == null) {
            return null;
        }

        return courses.get(courseCode.toUpperCase());
    }

    public boolean existsByCode(String courseCode) {
        return courses.containsKey(courseCode.toUpperCase());
    }

    public void save(Course course) {
        courses.put(course.getCode().toUpperCase(), course);
    }
}