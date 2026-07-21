package com.workshop.school.config;

import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.workshop.school.model.Admin;
import com.workshop.school.model.SchoolClass;
import com.workshop.school.model.Student;
import com.workshop.school.model.Subject;
import com.workshop.school.model.Teacher;
import com.workshop.school.repository.AdminRepository;
import com.workshop.school.repository.SchoolClassRepository;
import com.workshop.school.repository.StudentRepository;
import com.workshop.school.repository.SubjectRepository;
import com.workshop.school.repository.TeacherRepository;

@Component
public class DataSeeder implements CommandLineRunner {
    private final AdminRepository adminRepository;
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;
    private final SchoolClassRepository schoolClassRepository;
    private final StudentRepository studentRepository;

    public DataSeeder(AdminRepository adminRepository, TeacherRepository teacherRepository,
            SubjectRepository subjectRepository, SchoolClassRepository schoolClassRepository,
            StudentRepository studentRepository) {
        this.adminRepository = adminRepository;
        this.teacherRepository = teacherRepository;
        this.subjectRepository = subjectRepository;
        this.schoolClassRepository = schoolClassRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public void run(String... args) {
        seedAdmin();

        if (teacherRepository.count() == 0 && subjectRepository.count() == 0 && schoolClassRepository.count() == 0
                && studentRepository.count() == 0) {
            seedSchoolData();
        }
    }

    private void seedAdmin() {
        if (adminRepository.findByUsername("admin").isEmpty()) {
            Admin admin = new Admin();
            admin.setUsername("admin");
            admin.setPassword("admin123");
            admin.setDisplayName("School Administrator");
            adminRepository.save(admin);
        }
    }

    private void seedSchoolData() {
        Subject mathematics = subjectRepository.save(new Subject("Mathematics", "MATH101"));
        Subject science = subjectRepository.save(new Subject("Science", "SCI101"));
        Subject english = subjectRepository.save(new Subject("English", "ENG101"));

        Teacher anita = teacherRepository.save(new Teacher("Anita Sharma", "anita@school.com", "9876543210",
                "M.Sc Mathematics", "Mathematics"));
        Teacher rahul = teacherRepository.save(new Teacher("Rahul Verma", "rahul@school.com", "9876501234",
                "M.Sc Physics", "Science"));

        SchoolClass classTen = new SchoolClass();
        classTen.setClassName("Class 10");
        classTen.setSection("A");
        classTen.setClassTeacher(anita);
        classTen.setSubjects(Set.of(mathematics, science, english));
        schoolClassRepository.save(classTen);

        Student aarav = new Student("Aarav Sharma", "10A01", "aarav@student.com", "9000000001",
                "Noida, Uttar Pradesh", classTen);
        Student meera = new Student("Meera Joshi", "10A02", "meera@student.com", "9000000002",
                "Delhi", classTen);
        studentRepository.saveAll(List.of(aarav, meera));

        SchoolClass classNine = new SchoolClass();
        classNine.setClassName("Class 9");
        classNine.setSection("B");
        classNine.setClassTeacher(rahul);
        classNine.setSubjects(Set.of(science, english));
        schoolClassRepository.save(classNine);
    }
}