package com.example.repaso.service;

import java.util.List;

import com.example.repaso.model.User;

public interface UserService {
    public List<User> list(); // Método que lista todas las personas.

    public void save(User user); // Método que guarda una persona.

    public void delete(User user); // Método que elimina una persona.

    public User findUser(User user);

    public User registerUser(User user);
}
