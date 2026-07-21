package com.workshop.school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.workshop.school.model.SchoolClass;
import com.workshop.school.model.Subject;
import com.workshop.school.repository.SchoolClassRepository;
import com.workshop.school.repository.SubjectRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class SubjectController {
    private final SubjectRepository subjectRepository;
    private final SchoolClassRepository schoolClassRepository;

    public SubjectController(SubjectRepository subjectRepository, SchoolClassRepository schoolClassRepository) {
        this.subjectRepository = subjectRepository;
        this.schoolClassRepository = schoolClassRepository;
    }

    @GetMapping("/subjects")
    public String listSubjects(HttpSession session, Model model) {
        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }

        model.addAttribute("subjects", subjectRepository.findAll());
        return "subjects/list";
    }

    @GetMapping("/subjects/new")
    public String newSubject(HttpSession session, Model model) {
        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }

        model.addAttribute("subject", new Subject());
        model.addAttribute("pageTitle", "Add Subject");
        return "subjects/form";
    }

    @GetMapping("/subjects/edit/{id}")
    public String editSubject(@PathVariable Long id, HttpSession session, Model model) {
        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }

        Subject subject = subjectRepository.findById(id).orElseThrow();
        model.addAttribute("subject", subject);
        model.addAttribute("pageTitle", "Edit Subject");
        return "subjects/form";
    }

    @PostMapping("/subjects/save")
    public String saveSubject(@ModelAttribute Subject subject, RedirectAttributes redirectAttributes) {
        subjectRepository.save(subject);
        redirectAttributes.addFlashAttribute("message", "Subject saved successfully.");
        return "redirect:/subjects";
    }

    @GetMapping("/subjects/delete/{id}")
    public String deleteSubject(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Subject subject = subjectRepository.findById(id).orElseThrow();

        for (SchoolClass schoolClass : schoolClassRepository.findAll()) {
            schoolClass.getSubjects().remove(subject);
            schoolClassRepository.save(schoolClass);
        }

        subjectRepository.delete(subject);
        redirectAttributes.addFlashAttribute("message", "Subject deleted successfully.");
        return "redirect:/subjects";
    }

    private boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("adminUser") != null;
    }
}