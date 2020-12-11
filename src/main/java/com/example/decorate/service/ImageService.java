package com.example.decorate.service;

import com.example.decorate.domain.Image;
import com.example.decorate.domain.ImageType;
import com.example.decorate.domain.ProductType;
import com.example.decorate.domain.dto.ImageData;
import com.example.decorate.exception.DecorateBackendException;
import com.example.decorate.exception.ExceptionMessages;
import com.example.decorate.repository.ImageRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.decorate.exception.ExceptionMessages.IMAGE_NOT_EXISTS;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class ImageService {

    private final ImageRepository imageRepository;

    public void saveImageList(List<ImageData> images, Long prodId, ProductType productType) {
        for (ImageData imageData : images) {
            String imgUrl = imageData.getImgUrl();
            String strImageType = imageData.getImageType();

            Image image = Image.builder()
                    .productType(productType)
                    .prodKey(prodId)
                    .imgUrl(imgUrl)
                    .imageType(ImageType.valueOf(strImageType))
                    .build();

            imageRepository.save(image);
        }
    }

    public List<Image> getImagesByProductId(Long productId) {
        return this.imageRepository.findByProdKey(productId);
    }


    public void deleteImage(Image image) {
        imageRepository.findById(image.getId())
                .orElseThrow(() -> new DecorateBackendException(IMAGE_NOT_EXISTS.getMessage()));
        imageRepository.delete(image);
    }
}
