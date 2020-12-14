package com.example.decorate.services;

import com.example.decorate.domain.*;
import com.example.decorate.domain.dto.AttributeModel;
import com.example.decorate.domain.dto.ImageModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@AllArgsConstructor
@Service
@Transactional
public class DTOMapperService {
    private final AttributeService attributeService;
    private final ImageService imageService;

    public List<AttributeModel> getProductAllAttributesModel(ProductKey productKey) {
        List<Attribute> productAllAttributes = attributeService.getProductAllAttributesByProductKey(productKey);
        return convertProductAttributesToDTO(productAllAttributes);
    }

    private List<AttributeModel> convertProductAttributesToDTO(List<Attribute> productAttributes) {
        return productAttributes.stream()
                .map(AttributeModel::new)
                .collect(Collectors.toList());
    }

    public List<ImageModel> getProductAllImageModels(ProductKey productKey) {
        List<Image> imagesByProductId = getProductImagesByProductKey(productKey);
        return convertImagesToDTO(imagesByProductId);
    }

    private List<Image> getProductImagesByProductKey(ProductKey productKey) {
        return imageService.getImagesByProductId(productKey);
    }

    private List<ImageModel> convertImagesToDTO(List<Image> imagesByProductId) {
        List<ImageModel> images = new ArrayList<>();
        for (Image image : imagesByProductId) {
            images.add(new ImageModel(image));
        }
        return images;
    }
}
