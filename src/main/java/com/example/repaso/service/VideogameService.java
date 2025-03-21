package com.example.repaso.service;

import java.util.List;

import com.example.repaso.model.Videogame;

public interface VideogameService {
    public List<Videogame> list(); // Método que lista todas las personas.

    public void save(Videogame videogame); // Método que guarda una persona.

    public void delete(Videogame videogame); // Método que elimina una persona.

    public Videogame findVideogame(Videogame videogame);
    public Videogame findById(Long id);

}
