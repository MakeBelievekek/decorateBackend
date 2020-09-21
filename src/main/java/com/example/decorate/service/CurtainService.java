package com.example.decorate.service;

import com.example.decorate.domain.Curtain;
import com.example.decorate.domain.ImageType;
import com.example.decorate.domain.KeyHolder;
import com.example.decorate.domain.Wallpaper;
import com.example.decorate.domain.dto.ImageData;
import com.example.decorate.domain.dto.ProductFormData;
import com.example.decorate.domain.dto.ProductListItem;
import com.example.decorate.repository.CurtainRepository;
import com.example.decorate.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional

public class CurtainService {

    private CurtainRepository curtainRepository;
    private ImageRepository imageRepository;

    @Autowired
    public CurtainService(CurtainRepository curtainRepository, ImageRepository imageRepository) {
        this.curtainRepository = curtainRepository;
        this.imageRepository = imageRepository;
    }

    public void saveCurtain(ProductFormData productFormData, KeyHolder keyHolder) {
        Curtain curtain = new Curtain(productFormData, keyHolder);
        curtainRepository.save(curtain);
    }
    public ProductListItem getCurtain(KeyHolder keyHolder) {
        Optional<Curtain> curtain = curtainRepository.findById(keyHolder.getId());
        if (curtain.isPresent()) {
            ImageData image = new ImageData(imageRepository.findByProdKeyAndImageType(keyHolder.getId(), ImageType.PRIMARY_KEY));
            return new ProductListItem(curtain.get(), image);
        } else {
            return null;
        }
    }

}
