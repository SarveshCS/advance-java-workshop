package com.workshop.course.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workshop.course.model.Course;
import com.workshop.course.service.CourseService;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable int id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @GetMapping(value = "/{id}/view", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> viewCourse(@PathVariable int id) {
        Course course = courseService.getCourseById(id);
        String html = """
                <html>
                <body style="font-family:Arial;padding:20px;">
                    <h2>Course Details</h2>
                    <table border="1" cellpadding="8" cellspacing="0">
                        <tr><th>Course Code</th><td>%s</td></tr>
                        <tr><th>Course Name</th><td>%s</td></tr>
                        <tr><th>Instructor</th><td>%s</td></tr>
                        <tr><th>Credits</th><td>%d</td></tr>
                    </table>
                </body>
                </html>
                """.formatted(course.getCourseCode(), course.getCourseName(), course.getInstructor(), course.getCredits());
        return ResponseEntity.status(HttpStatus.OK).body(html);
    }
}
