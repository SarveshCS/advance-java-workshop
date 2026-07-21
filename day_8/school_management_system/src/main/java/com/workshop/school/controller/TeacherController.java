package com.workshop.school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.workshop.school.model.SchoolClass;
import com.workshop.school.model.Teacher;
import com.workshop.school.repository.SchoolClassRepository;
import com.workshop.school.repository.TeacherRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class TeacherController {
    private final TeacherRepository teacherRepository;
    private final SchoolClassRepository schoolClassRepository;

    public TeacherController(TeacherRepository teacherRepository, SchoolClassRepository schoolClassRepository) {
        this.teacherRepository = teacherRepository;
        this.schoolClassRepository = schoolClassRepository;
    }

    @GetMapping("/teachers")
    public String listTeachers(HttpSession session, Model model) {
        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }

        model.addAttribute("teachers", teacherRepository.findAll());
        return "teachers/list";
    }

    @GetMapping("/teachers/new")
    public String newTeacher(HttpSession session, Model model) {
        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }

        model.addAttribute("teacher", new Teacher());
        model.addAttribute("pageTitle", "Add Teacher");
        return "teachers/form";
    }

    @GetMapping("/teachers/edit/{id}")
    public String editTeacher(@PathVariable Long id, HttpSession session, Model model) {
        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }

        Teacher teacher = teacherRepository.findById(id).orElseThrow();
        model.addAttribute("teacher", teacher);
        model.addAttribute("pageTitle", "Edit Teacher");
        return "teachers/form";
    }

    @PostMapping("/teachers/save")
    public String saveTeacher(@ModelAttribute Teacher teacher, RedirectAttributes redirectAttributes) {
        teacherRepository.save(teacher);
        redirectAttributes.addFlashAttribute("message", "Teacher saved successfully.");
        return "redirect:/teachers";
    }

    @GetMapping("/teachers/delete/{id}")
    public String deleteTeacher(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        for (SchoolClass schoolClass : schoolClassRepository.findByClassTeacherId(id)) {
            schoolClass.setClassTeacher(null);
            schoolClassRepository.save(schoolClass);
        }

        teacherRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Teacher deleted successfully.");
        return "redirect:/teachers";
    }

    private boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("adminUser") != null;
    }
}