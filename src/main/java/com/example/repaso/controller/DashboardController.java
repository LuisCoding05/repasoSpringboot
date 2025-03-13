package com.example.repaso.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.repaso.model.User;
import com.example.repaso.repository.IUserDao;

@Controller
public class DashboardController {

    @Autowired
    private IUserDao userDao;
    @GetMapping("/")
    public String index() {
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        String emailOrUsername = principal.getName();
    
        // Busca primero por email (asumiendo que el "username" en Spring Security es el email)
        Optional<User> userOptional = userDao.findByUsername(emailOrUsername);
        
        // Si no se encuentra por email, busca por username
        if (!userOptional.isPresent()) {
            userOptional = userDao.findByEmail(emailOrUsername);
        }
        
        // Verifica si el usuario existe
        if (!userOptional.isPresent()) {
            // Manejar error (ej: redirigir a login)
            return "redirect:/login?error=true";
        }
        
        // Obtiene el User del Optional
        User user = userOptional.get();
        
        // Agrega el objeto User al modelo (no el Optional)
        model.addAttribute("user", user);
        System.out.println("Usuario: " + user); // Log del objeto User
        
        return "dashboard";
    }
}
