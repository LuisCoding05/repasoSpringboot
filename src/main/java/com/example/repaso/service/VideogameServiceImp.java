package com.example.repaso.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.repaso.model.Developer;
import com.example.repaso.model.Genre;
import com.example.repaso.model.Platform;
import com.example.repaso.model.Videogame;
import com.example.repaso.repository.IVideogameDao;

@Service
public class VideogameServiceImp implements VideogameService{

    @Autowired
    private IVideogameDao videogameDao;

    @Override
    @Transactional(readOnly = true)
    public List<Videogame> list() {
        return (List<Videogame>) videogameDao.findAll();
    }

    @Override
    @Transactional
    public void save(Videogame videogame) {
        videogameDao.save(videogame);
    }

    @Override
    @Transactional
    public void delete(Videogame videogame) {
        videogameDao.delete(videogame);
    }

    @Override
    @Transactional(readOnly = true)
    public Videogame findVideogame(Videogame videogame) {
        return videogameDao.findById(videogame.getId()).orElse(null);
    }

    @Transactional(readOnly = true)
    public Developer findVideogameDeveloper(Videogame videogame) {
        return videogame.getDeveloper(); // Devuelve directamente el desarrollador
    }

    @Transactional(readOnly = true)
    public Set<Genre> getGenresByVideogame(Videogame videogame) {
        return videogame.getGenres(); // Devuelve directamente los géneros
    }

    @Transactional(readOnly = true)
    public Set<Platform> getPlatformsByVideogame(Videogame videogame) {
        return videogame.getPlatforms(); // Devuelve directamente los géneros
    }

    @Override
    @Transactional(readOnly = true)
    public Videogame findById(Long id) {
        return videogameDao.findById(id).orElse(null);
    }

    @Transactional
    public void deleteById(Long id) {
        videogameDao.deleteById(id);
    }
}
