package com.example.repaso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.repaso.model.Developer;

public interface IDeveloperDao extends JpaRepository<Developer, Long> {

}
