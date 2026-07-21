package com.workshop.school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.workshop.school.repository.SchoolClassRepository;
import com.workshop.school.repository.StudentRepository;
import com.workshop.school.repository.SubjectRepository;
import com.workshop.school.repository.TeacherRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class DashboardController {
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final SchoolClassRepository schoolClassRepository;
    private final SubjectRepository subjectRepository;

    public DashboardController(StudentRepository studentRepository, TeacherRepository teacherRepository,
            SchoolClassRepository schoolClassRepository, SubjectRepository subjectRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.schoolClassRepository = schoolClassRepository;
        this.subjectRepository = subjectRepository;
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        if (session.getAttribute("adminUser") == null) {
            return "redirect:/login";
        }

        model.addAttribute("adminUser", session.getAttribute("adminUser"));
        model.addAttribute("studentCount", studentRepository.count());
        model.addAttribute("teacherCount", teacherRepository.count());
        model.addAttribute("classCount", schoolClassRepository.count());
        model.addAttribute("subjectCount", subjectRepository.count());
        model.addAttribute("students", studentRepository.findAll());
        model.addAttribute("classes", schoolClassRepository.findAll());
        return "dashboard";
    }
}