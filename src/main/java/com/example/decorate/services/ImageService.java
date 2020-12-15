package com.example.decorate.services;

import com.example.decorate.domain.Image;
import com.example.decorate.domain.ImageType;
import com.example.decorate.domain.ProductKey;
import com.example.decorate.domain.ProductType;
import com.example.decorate.domain.dto.ImageModel;
import com.example.decorate.exception.DecorateBackendException;
import com.example.decorate.repositorys.ImageRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.decorate.exception.ExceptionMessages.MULTIPLE_PRIMARY_IMG_EXISTS;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class ImageService {

    private final ImageRepository imageRepository;

    public void saveImageList(List<ImageModel> images, ProductKey productKey) {
        for (ImageModel imageModel : images) {
            String imgUrl = imageModel.getImgUrl();
            String strImageType = imageModel.getImageType();
            ProductType productType = productKey.getType();

            Image image = Image.builder()
                    .productType(productType)
                    .productKey(productKey)
                    .imgUrl(imgUrl)
                    .imageType(ImageType.valueOf(strImageType))
                    .created(Instant.now())
                    .build();

            imageRepository.save(image);
          //  checkForDuplicatePrimaryImg(productKey);
        }
    }

    public List<Image> getImagesByProductId(ProductKey productKey) {
        return getAllImagesByProdKey(productKey);
    }

    public void deleteProductImages(ProductKey productKey) {
        List<Image> allImagesByProdKey = getAllImagesByProdKey(productKey);
        imageRepository.deleteAll(allImagesByProdKey);
    }

    private List<Image> getAllImagesByProdKey(ProductKey productKey) {
        return imageRepository.findAllByProductKey(productKey);
    }

    public void updateProductImages(ProductKey productKey, List<ImageModel> imageList) {
        List<Long> activeImagesIdList = new ArrayList<>();
        for (ImageModel imageModel : imageList) {
            ProductType productType = productKey.getType();
            Long imageId = createValidImageId(imageModel.getId());
            Optional<Image> img = imageRepository.findById(imageId);
            Image persistentImg = img.orElseGet(() -> {
                Image imageToSave = new Image();
                imageRepository.save(imageToSave);
                return imageToSave;
            });
            persistentImg.setImageType(ImageType.valueOf(imageModel.getImageType()));
            persistentImg.setImgUrl(imageModel.getImgUrl());
            persistentImg.setProductKey(productKey);
            persistentImg.setProductType(productType);
            persistentImg.setModified(Instant.now());
            activeImagesIdList.add(persistentImg.getId());
        }
        imageRepository.deleteProductInActiveImages(activeImagesIdList, productKey);
        //checkForDuplicatePrimaryImg(productKey);
    }

    private void checkForDuplicatePrimaryImg(ProductKey productKey) {
        List<Long> imagesWhitMultiplePrimaryImages = imageRepository.findImagesWhitMultiplePrimaryImages(productKey);
        if (!imagesWhitMultiplePrimaryImages.isEmpty()) {
            throw new DecorateBackendException(MULTIPLE_PRIMARY_IMG_EXISTS.getMessage());
        }
    }

    private Long createValidImageId(Long imageId) {
        if (imageId == null) {
            return -1L;
        } else {
            return imageId;
        }
    }
}
