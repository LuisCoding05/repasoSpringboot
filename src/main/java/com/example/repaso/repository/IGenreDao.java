package com.example.repaso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.repaso.model.Genre;

public interface IGenreDao extends JpaRepository<Genre, Long> {

}
