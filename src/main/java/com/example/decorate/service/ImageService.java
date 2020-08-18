package com.example.decorate.service;

import com.example.decorate.domain.Image;
import com.example.decorate.domain.dto.ImageData;
import com.example.decorate.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ImageService {

    private ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public void saveImage(List<ImageData> images,Long prodId) {
        for (ImageData imageData : images) {
            Image image = new Image(imageData);
            image.setProdKey(prodId);
            imageRepository.save(image);
        }
    }


}
