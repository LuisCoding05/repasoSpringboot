package com.example.repaso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.repaso.model.Genre;
import com.example.repaso.repository.IGenreDao;

@Service
public class GenreServiceImp {
     @Autowired
    private IGenreDao genreDao;

    @Transactional(readOnly = true)
    public List<Genre> findAll() {
        return genreDao.findAll();
    }
}
