package com.example.repaso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.repaso.model.Platform;
import com.example.repaso.repository.IPlatformDao;

@Service
public class PlatformServiceImp {
    @Autowired
    private IPlatformDao platformDao;

    @Transactional(readOnly = true)
    public List<Platform> findAll() {
        return platformDao.findAll();
    }
}
