package com.example.decorate.services;

import com.example.decorate.domain.Curtain;
import com.example.decorate.domain.Image;
import com.example.decorate.domain.ImageType;
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

import static com.example.decorate.exception.ExceptionMessages.IMAGE_NOT_EXISTS;

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
                    .timeStamp(Instant.now())
                    .build();

            imageRepository.save(image);
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

    public void updateProductImages(Long productId, List<ImageModel> imageList) {
      /*  List<Long> activeImagesIdList = new ArrayList<>();
        for (ImageModel imageModel : imageList) {
            Long imageId = imageModel.getId();
            Optional<Image> img = imageRepository.findById(imageId);
            Image persistentImg = img.orElseGet(() -> {
                Image imageToSave = new Image(imageModel);
                imageRepository.save(imageToSave);
                return imageToSave;
            });
            activeImagesIdList.add(persistentImg.getId());
        }
        imageRepository.deleteProductInActiveImages(productId);*/
    }
}
