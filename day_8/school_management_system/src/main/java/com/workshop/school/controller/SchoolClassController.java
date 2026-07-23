package com.workshop.school.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
import com.workshop.school.model.Subject;
import com.workshop.school.model.Teacher;
import com.workshop.school.repository.SchoolClassRepository;
import com.workshop.school.repository.StudentRepository;
import com.workshop.school.repository.SubjectRepository;
import com.workshop.school.repository.TeacherRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class SchoolClassController {
    private final SchoolClassRepository schoolClassRepository;
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;
    private final StudentRepository studentRepository;

    public SchoolClassController(SchoolClassRepository schoolClassRepository, TeacherRepository teacherRepository,
            SubjectRepository subjectRepository, StudentRepository studentRepository) {
        this.schoolClassRepository = schoolClassRepository;
        this.teacherRepository = teacherRepository;
        this.subjectRepository = subjectRepository;
        this.studentRepository = studentRepository;
    }

    @GetMapping("/classes")
    public String listClasses(HttpSession session, Model model) {
        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }

        model.addAttribute("classes", schoolClassRepository.findAll());
        return "classes/list";
    }

    @GetMapping("/classes/new")
    public String newClass(HttpSession session, Model model) {
        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }

        prepareForm(model, new SchoolClass(), "Add Class");
        return "classes/form";
    }

    @GetMapping("/classes/edit/{id}")
    public String editClass(@PathVariable Long id, HttpSession session, Model model) {
        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }

        SchoolClass schoolClass = schoolClassRepository.findById(id).orElseThrow();
        prepareForm(model, schoolClass, "Edit Class");
        return "classes/form";
    }

    @PostMapping("/classes/save")
    public String saveClass(@ModelAttribute SchoolClass schoolClass, @RequestParam(required = false) Long teacherId,
            @RequestParam(required = false) List<Long> subjectIds, RedirectAttributes redirectAttributes) {
        Teacher teacher = teacherRepository.findById(teacherId).orElse(null);
        List<Subject> subjects = subjectIds == null ? List.of() : subjectRepository.findAllById(subjectIds);

        if (teacher != null) {
            for (SchoolClass assignedClass : schoolClassRepository.findByClassTeacherId(teacher.getId())) {
                if (schoolClass.getId() == null || !assignedClass.getId().equals(schoolClass.getId())) {
                    assignedClass.setClassTeacher(null);
                    schoolClassRepository.save(assignedClass);
                }
            }
        }

        schoolClass.setClassTeacher(teacher);
        schoolClass.setSubjects(new HashSet<>(subjects));
        schoolClassRepository.save(schoolClass);
        redirectAttributes.addFlashAttribute("message", "Class saved successfully.");
        return "redirect:/classes";
    }

    @GetMapping("/classes/delete/{id}")
    public String deleteClass(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        List<Student> students = studentRepository.findBySchoolClassId(id);

        for (Student student : students) {
            student.setSchoolClass(null);
            studentRepository.save(student);
        }

        schoolClassRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Class deleted successfully.");
        return "redirect:/classes";
    }

    private void prepareForm(Model model, SchoolClass schoolClass, String pageTitle) {
        Set<Long> selectedSubjectIds = schoolClass.getSubjects().stream()
                .map(Subject::getId)
                .collect(Collectors.toSet());

        model.addAttribute("schoolClass", schoolClass);
        model.addAttribute("teachers", teacherRepository.findAll());
        model.addAttribute("subjects", subjectRepository.findAll());
        model.addAttribute("selectedSubjectIds", selectedSubjectIds);
        model.addAttribute("pageTitle", pageTitle);
    }

    private boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("adminUser") != null;
    }
}