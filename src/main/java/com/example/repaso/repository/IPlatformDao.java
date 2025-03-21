package com.example.repaso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.repaso.model.Platform;

public interface IPlatformDao extends JpaRepository<Platform, Long> {

}
