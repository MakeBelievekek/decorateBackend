package com.example.decorate.services;

import com.example.decorate.domain.Image;
import com.example.decorate.domain.ImageType;
import com.example.decorate.domain.ProductType;
import com.example.decorate.domain.dto.ImageModel;
import com.example.decorate.exception.DecorateBackendException;
import com.example.decorate.repositorys.ImageRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.LazyToOne;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.decorate.exception.ExceptionMessages.IMAGE_NOT_EXISTS;
import static com.example.decorate.exception.ExceptionMessages.MULTIPLE_PRIMARY_IMG_EXISTS;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class ImageService {

    private final ImageRepository imageRepository;

    public void saveImageList(List<ImageModel> images, Long prodId, ProductType productType) {
        for (ImageModel imageModel : images) {
            String imgUrl = imageModel.getImgUrl();
            String strImageType = imageModel.getImageType();

            Image image = Image.builder()
                    .productType(productType)
                    .prodKey(prodId)
                    .imgUrl(imgUrl)
                    .imageType(ImageType.valueOf(strImageType))
                    .created(Instant.now())
                    .build();

            imageRepository.save(image);
            checkForDuplicatePrimaryImg(prodId);
        }
    }

    public List<Image> getImagesByProductId(Long productId) {
        return getAllImagesByProdKey(productId);
    }

    public ImageModel getProductPrimaryImg(Long productId) {
        Image image = imageRepository.findProductPrimaryImage(productId)
                .orElseThrow(() -> new DecorateBackendException(IMAGE_NOT_EXISTS.getMessage()));
        return new ImageModel(image);
    }

    public void deleteImage(Long imageId) {
        Image image = getImageById(imageId);
        imageRepository.delete(image);
    }

    private Image getImageById(Long imageId) {
        return imageRepository.findById(imageId)
                .orElseThrow(() -> new DecorateBackendException(IMAGE_NOT_EXISTS.getMessage()));
    }

    public void deleteProductImages(Long productId) {
        List<Image> imagesByProductId = getAllImagesByProdKey(productId);
        imageRepository.deleteAll(imagesByProductId);
    }

    private List<Image> getAllImagesByProdKey(Long productId) {
        return imageRepository.findAllByProdKey(productId);
    }

    public void updateProductImages(Long productId, List<ImageModel> imageList, ProductType productType) {
        List<Long> activeImagesIdList = new ArrayList<>();
        for (ImageModel imageModel : imageList) {
            Long imageId = createValidImageId(imageModel.getId());
            Optional<Image> img = imageRepository.findById(imageId);
            Image persistentImg = img.orElseGet(() -> {
                Image imageToSave = new Image();
                imageRepository.save(imageToSave);
                return imageToSave;
            });
            persistentImg.setImageType(ImageType.valueOf(imageModel.getImageType()));
            persistentImg.setImgUrl(imageModel.getImgUrl());
            persistentImg.setProdKey(productId);
            persistentImg.setProductType(productType);
            persistentImg.setModified(Instant.now());
            activeImagesIdList.add(persistentImg.getId());
        }
        imageRepository.deleteProductInActiveImages(activeImagesIdList, productId);
        checkForDuplicatePrimaryImg(productId);
    }

    private void checkForDuplicatePrimaryImg(Long productId) {
        List<Long> imageIdsWhitMultiplePrimaryImgs = imageRepository.findImagesWhitMultiplePrimaryImgs(productId);
        if (!imageIdsWhitMultiplePrimaryImgs.isEmpty()) {
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
