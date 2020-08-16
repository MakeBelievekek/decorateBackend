package com.example.decorate.service;

import com.example.decorate.domain.KeyHolder;
import com.example.decorate.domain.ProductType;
import com.example.decorate.repository.KeyHolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class KeyHolderService {

    private KeyHolderRepository keyHolderRepository;

    @Autowired
    public KeyHolderService(KeyHolderRepository keyHolderRepository) {
        this.keyHolderRepository = keyHolderRepository;
    }

    public KeyHolder saveKey() {

        KeyHolder keyHolder = new KeyHolder();
        keyHolder.setType(ProductType.WALLPAPER);
        keyHolderRepository.save(keyHolder);
        return keyHolder;
    }
}