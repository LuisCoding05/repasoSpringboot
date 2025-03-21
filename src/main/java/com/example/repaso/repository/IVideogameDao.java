package com.example.repaso.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.repaso.model.Videogame;

public interface IVideogameDao extends JpaRepository<Videogame,Long>{
    @EntityGraph(attributePaths = {"developer", "genres", "platforms"})
    Optional<Videogame> findByTitle(String videogame);
}
