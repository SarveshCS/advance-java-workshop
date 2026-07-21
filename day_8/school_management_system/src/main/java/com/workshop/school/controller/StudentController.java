package com.workshop.school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.workshop.school.model.SchoolClass;
import com.workshop.school.model.Student;
import com.workshop.school.repository.SchoolClassRepository;
import com.workshop.school.repository.StudentRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class StudentController {
    private final StudentRepository studentRepository;
    private final SchoolClassRepository schoolClassRepository;

    public StudentController(StudentRepository studentRepository, SchoolClassRepository schoolClassRepository) {
        this.studentRepository = studentRepository;
        this.schoolClassRepository = schoolClassRepository;
    }

    @GetMapping("/students")
    public String listStudents(HttpSession session, Model model) {
        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }

        model.addAttribute("students", studentRepository.findAll());
        return "students/list";
    }

    @GetMapping("/students/new")
    public String newStudent(HttpSession session, Model model) {
        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }

        model.addAttribute("student", new Student());
        model.addAttribute("classes", schoolClassRepository.findAll());
        model.addAttribute("pageTitle", "Add Student");
        return "students/form";
    }

    @GetMapping("/students/edit/{id}")
    public String editStudent(@PathVariable Long id, HttpSession session, Model model) {
        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }

        Student student = studentRepository.findById(id).orElseThrow();
        model.addAttribute("student", student);
        model.addAttribute("classes", schoolClassRepository.findAll());
        model.addAttribute("pageTitle", "Edit Student");
        return "students/form";
    }

    @PostMapping("/students/save")
    public String saveStudent(@ModelAttribute Student student, @RequestParam(required = false) Long schoolClassId,
            RedirectAttributes redirectAttributes) {
        SchoolClass schoolClass = schoolClassRepository.findById(schoolClassId).orElse(null);
        student.setSchoolClass(schoolClass);
        studentRepository.save(student);
        redirectAttributes.addFlashAttribute("message", "Student saved successfully.");
        return "redirect:/students";
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        studentRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Student deleted successfully.");
        return "redirect:/students";
    }

    private boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("adminUser") != null;
    }
}