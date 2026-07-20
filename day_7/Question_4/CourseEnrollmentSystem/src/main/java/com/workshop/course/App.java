package com.workshop.course;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.workshop.course.config.AppConfig;
import com.workshop.course.model.Student;

public class App {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {
            Student student = context.getBean(Student.class);
            student.displayStudentDetails();
        }
    }
}