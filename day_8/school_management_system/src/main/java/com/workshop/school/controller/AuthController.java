package com.workshop.school.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.workshop.school.model.Admin;
import com.workshop.school.repository.AdminRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {
    private final AdminRepository adminRepository;

    public AuthController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @GetMapping("/")
    public String home(HttpSession session) {
        if (session.getAttribute("adminUser") == null) {
            return "redirect:/login";
        }
        return "redirect:/dashboard";
    }

    @GetMapping("/login")
    public String loginPage(HttpSession session) {
        if (session.getAttribute("adminUser") != null) {
            return "redirect:/dashboard";
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session,
            Model model) {
        Optional<Admin> admin = adminRepository.findByUsername(username);

        if (admin.isPresent() && admin.get().getPassword().equals(password)) {
            session.setAttribute("adminUser", admin.get().getDisplayName());
            return "redirect:/dashboard";
        }

        model.addAttribute("error", "Invalid username or password.");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("message", "You have logged out successfully.");
        return "redirect:/login";
    }
}