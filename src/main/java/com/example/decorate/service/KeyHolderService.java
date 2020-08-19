package com.example.decorate.service;

import com.example.decorate.domain.KeyHolder;
import com.example.decorate.domain.Product;
import com.example.decorate.domain.ProductType;
import com.example.decorate.domain.dto.ProductListItem;
import com.example.decorate.repository.KeyHolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class KeyHolderService {

    private KeyHolderRepository keyHolderRepository;

    @Autowired
    public KeyHolderService(KeyHolderRepository keyHolderRepository) {
        this.keyHolderRepository = keyHolderRepository;
    }

    public KeyHolder saveKey(KeyHolder keyHolder, ProductType productType) {
        keyHolder.setType(productType);
        keyHolderRepository.save(keyHolder);
        return keyHolder;
    }

    public List<KeyHolder> getProductsFromLocal(String productIds) {
        List<KeyHolder> keyHolders = new ArrayList<>();
        String[] ids = productIds.split(",");
        List<Long> productsLong = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            productsLong.add(Long.parseLong(ids[i]));
        }
        for (long id : productsLong) {
            Optional<KeyHolder> keyHolder = keyHolderRepository.findById(id);
            keyHolder.ifPresent(keyHolders::add);
        }
        return keyHolders;
    }
}
