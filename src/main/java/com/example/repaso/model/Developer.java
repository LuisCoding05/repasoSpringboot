package com.example.repaso.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "country")
    private String country;

    @Column(name = "foundation")
    private Integer foundation;
    
    @OneToMany(mappedBy = "developer")
    private Set<Videogame> juegos = new HashSet<>();
}
