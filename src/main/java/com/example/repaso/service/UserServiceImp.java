package com.example.repaso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.repaso.exception.EmailAlreadyExistsException;
import com.example.repaso.model.User;
import com.example.repaso.repository.IUserDao;

@Service
public class UserServiceImp implements UserService, UserDetailsService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true) 
    public List<User> list() {
        return (List<User>) userDao.findAll();
    }

    @Override
    @Transactional
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    @Transactional
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findUser(User user) {
        return userDao.findById(user.getId()).orElse(null);
    }

    @Override
    public User registerUser(User user) {
        System.out.println("Email a registrar: " + user.getEmail()); // Log
        Optional<User> existingUser = userDao.findByEmail(user.getEmail());
        System.out.println("Usuario encontrado: " + existingUser); // Log
        if (existingUser.isPresent()) {
            throw new EmailAlreadyExistsException("El email ya está registrado");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDao.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username)
            .orElseGet(() -> userDao.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado")));

        return org.springframework.security.core.userdetails.User.builder()
            .username(user.getUsername())
            .password(user.getPassword())
            .roles(user.getRol().name())
            .build();
    }

    
    

}
