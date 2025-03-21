package com.example.repaso.converter;

import com.example.repaso.model.Developer;
import com.example.repaso.repository.IDeveloperDao;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToDeveloperConverter implements Converter<String, Developer> {

    private final IDeveloperDao developerDao;

    public StringToDeveloperConverter(IDeveloperDao developerDao) {
        this.developerDao = developerDao;
    }

    @Override
    public Developer convert(String id) {
        if (id == null || id.isEmpty()) return null;
        return developerDao.findById(Long.parseLong(id)).orElse(null);
    }
}