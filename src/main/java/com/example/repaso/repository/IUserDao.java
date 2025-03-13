package com.example.repaso.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.repaso.model.User;

@Repository
public interface IUserDao extends JpaRepository<User,Long>{
    Optional<User> findByUsername(String username); // Método que busca un usuario por su nombre de usuario.
    Optional<User> findByEmail(String email); // Método que busca un usuario por su email.
}
