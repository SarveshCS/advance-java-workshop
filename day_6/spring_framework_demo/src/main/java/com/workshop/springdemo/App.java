package com.workshop.springdemo;

import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.workshop.springdemo.config.AppConfig;
import com.workshop.springdemo.service.EnrollmentService;
import com.workshop.springdemo.service.ReportService;

public class App {
    public static void main(String[] args) {
        System.out.println("Starting Spring Course Enrollment System...");

        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {
            EnrollmentService enrollmentService = context.getBean(EnrollmentService.class);
            ReportService reportService = context.getBean(ReportService.class);

            runMenu(enrollmentService, reportService);
        }
    }

    private static void runMenu(EnrollmentService enrollmentService, ReportService reportService) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt(scanner, "Enter your choice: ");

            switch (choice) {
                case 1 -> reportService.printCourseSummary();
                case 2 -> enrollStudent(scanner, enrollmentService, reportService);
                case 3 -> addCourse(scanner, enrollmentService);
                case 4 -> running = false;
                default -> System.out.println("Please choose a valid menu option.");
            }
        }

        System.out.println("Thank you for using the Spring Course Enrollment System.");
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("========== Course Enrollment Menu ==========");
        System.out.println("1. View all courses");
        System.out.println("2. Enroll a student");
        System.out.println("3. Add a new course");
        System.out.println("4. Exit");
        System.out.println("===========================================");
    }

    private static void enrollStudent(Scanner scanner, EnrollmentService enrollmentService, ReportService reportService) {
        String studentName = readText(scanner, "Enter student name: ");
        String courseCode = readText(scanner, "Enter course code: ");

        reportService.printEnrollment(enrollmentService.enrollStudent(studentName, courseCode));
    }

    private static void addCourse(Scanner scanner, EnrollmentService enrollmentService) {
        String courseCode = readText(scanner, "Enter new course code: ");
        String courseTitle = readText(scanner, "Enter course title: ");
        int seatLimit = readInt(scanner, "Enter seat limit: ");

        String message = enrollmentService.addCourse(courseCode, courseTitle, seatLimit);
        System.out.println(message);
    }

    private static String readText(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a number.");
            }
        }
    }
}