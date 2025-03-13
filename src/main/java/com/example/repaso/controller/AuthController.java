package com.example.repaso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.repaso.model.User;
import com.example.repaso.service.UserServiceImp;

@Controller
public class AuthController {
    
    @Autowired
    UserServiceImp userServiceImp;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, RedirectAttributes redirect) {
        userServiceImp.registerUser(user);
        redirect.addFlashAttribute("success", "¡Registro exitoso!");
        return "redirect:/login";
    }
}
