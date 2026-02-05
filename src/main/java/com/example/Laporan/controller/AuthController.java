package com.example.Laporan.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    // Field username & password
    private final String USERNAME = "admin";
    private final String PASSWORD = "123";

    // Halaman login
    @GetMapping("/login")
    public String loginPage(HttpSession session) {
        // kalau sudah login, langsung ke dashboard
        if (session.getAttribute("loggedUser") != null) {
            return "redirect:/dashboard";
        }
        return "login";
    }

    // Proses login
    @PostMapping("/login")
    public String loginProcess(@RequestParam String username,
                               @RequestParam String password,
                               HttpSession session,
                               Model model) {

        if (username.equals(USERNAME) && password.equals(PASSWORD)) {
            session.setAttribute("loggedUser", username);
            // langsung redirect ke dashboard
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Username atau Password salah!");
            return "login";
        }
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
