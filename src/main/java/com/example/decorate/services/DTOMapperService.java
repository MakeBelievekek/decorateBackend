package com.example.decorate.services;

import com.example.decorate.domain.*;
import com.example.decorate.domain.dto.AttributeModel;
import com.example.decorate.domain.dto.ImageModel;

import java.util.ArrayList;
import java.util.List;

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

    public List<ImageModel> getProductAllImageModels(Long productId) {
        List<Image> imagesByProductId = getProductImagesByProductId(productId);
        return convertImagesToDTO(imagesByProductId);
    }

    public <T> List<AttributeModel> getProductAllAttributesModel(T product) {
        List<AttributeModel> attributeModels = null;
        String productClass = product.getClass().toString();

        final String curtainClass = Curtain.class.toString();

        if (productClass.equals(curtainClass)) {
            Curtain curtain = (Curtain) product;
            Long curtainId = curtain.getId();
            List<CurtainAttribute> curtainAttributes = attributeService.fetchAllCurtainAttributes(curtainId);
            attributeModels = convertCurtainAttributesToDTO(curtainAttributes);
        }
        return attributeModels;
    }

    private List<Image> getProductImagesByProductId(Long curtainId) {
        return imageService.getImagesByProductId(curtainId);
    }

    private List<ImageModel> convertImagesToDTO(List<Image> imagesByProductId) {
        List<ImageModel> images = new ArrayList<>();
        for (Image image : imagesByProductId) {
            images.add(new ImageModel(image));
        }
        return images;
    }

    private List<AttributeListItem> getProductAllAttribute(Long curtainId) {
        return attributeService.findProductAllAttribute(curtainId);
    }

    private List<AttributeModel> convertAttributesToDTO(List<AttributeListItem> productAllAttribute) {
        List<AttributeModel> attributes = new ArrayList<>();
        for (AttributeListItem attributeListItem : productAllAttribute) {
            attributes.add(new AttributeModel(attributeListItem));
        }
        return attributes;
    }

    private List<AttributeModel> convertCurtainAttributesToDTO(List<CurtainAttribute> curtainAttributes) {
        List<AttributeModel> attributes = new ArrayList<>();
        for (CurtainAttribute curtainAttribute : curtainAttributes) {
            attributes.add(new AttributeModel(curtainAttribute));
        }
        return attributes;
    }
}
