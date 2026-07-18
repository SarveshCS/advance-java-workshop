package com.workshop.springdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.workshop.springdemo.repository.CourseRepository;
import com.workshop.springdemo.service.EnrollmentService;
import com.workshop.springdemo.service.ReportService;

@Configuration
public class AppConfig {
    @Bean
    public CourseRepository courseRepository() {
        return new CourseRepository();
    }

    @Bean
    public EnrollmentService enrollmentService(CourseRepository courseRepository) {
        return new EnrollmentService(courseRepository);
    }

    @Bean
    public ReportService reportService(EnrollmentService enrollmentService) {
        return new ReportService(enrollmentService);
    }
}