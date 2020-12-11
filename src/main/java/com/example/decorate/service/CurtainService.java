package com.example.decorate.service;

import com.example.decorate.domain.AttributeListItem;
import com.example.decorate.domain.Curtain;
import com.example.decorate.domain.Image;
import com.example.decorate.domain.KeyHolder;
import com.example.decorate.domain.dto.AttributeModel;
import com.example.decorate.domain.dto.CurtainModel;
import com.example.decorate.domain.dto.ImageData;
import com.example.decorate.domain.dto.ProductCreationFormData;
import com.example.decorate.exception.DecorateBackendException;
import com.example.decorate.repository.CurtainRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.example.decorate.domain.ProductType.CURTAIN;
import static com.example.decorate.exception.ExceptionMessages.CURTAIN_NOT_EXISTS;

@Slf4j
@AllArgsConstructor
@Service
@Transactional

public class CurtainService {

    private final KeyHolderService keyHolderService;
    private final CurtainRepository curtainRepository;
    private final AttributeService attributeService;
    private final ImageService imageService;

    public void saveCurtain(ProductCreationFormData productCreationFormData) {
        KeyHolder keyHolder = new KeyHolder();
        keyHolderService.saveKey(keyHolder, CURTAIN);
        Long keyHolderId = keyHolder.getId();

        Curtain curtain = new Curtain(productCreationFormData, keyHolder);
        curtainRepository.save(curtain);

        attributeService.saveAttributeListItem(productCreationFormData.getAttributeListItemData(), keyHolderId);

        imageService.saveImageList(productCreationFormData.getImageList(), keyHolderId, CURTAIN);
    }

    public CurtainModel getCurtain(Long curtainId) {
        Curtain curtain = getCurtainById(curtainId);

        return createCurtainModel(curtain);
    }

    public List<CurtainModel> getAllCurtains() {
        List<Curtain> allCurtains = curtainRepository.getAllCurtains();
        List<CurtainModel> allCurtainModels = new ArrayList<>();

        for (Curtain curtain : allCurtains) {
            allCurtainModels.add(createCurtainModel(curtain));
        }
        return allCurtainModels;
    }

    public void updateCurtain(Long curtainId, CurtainModel curtainModel) {
        Curtain curtain = getCurtainById(curtainId);

        setCurtainUpdatedValues(curtain, curtainModel);
    }

    public void deleteCurtain(Long curtainId) {
        Curtain curtain = getCurtainById(curtainId);

        List<Image> curtainImagesByProductId = getCurtainImagesByProductId(curtainId);
        List<AttributeListItem> curtainAllAttribute = getCurtainAllAttribute(curtainId);

        for (Image image : curtainImagesByProductId) {
            imageService.deleteImage(image);
        }

        for (AttributeListItem curtainAttribute : curtainAllAttribute) {
            attributeService.deleteAttributeListItem(curtainAttribute);
        }

        curtainRepository.delete(curtain);
    }

    private Curtain getCurtainById(Long curtainId) {
        return curtainRepository.findById(curtainId)
                .orElseThrow(() -> new DecorateBackendException(CURTAIN_NOT_EXISTS.getMessage()));
    }

    private void setCurtainUpdatedValues(Curtain curtain, CurtainModel curtainModel) {
        curtain.setName(curtainModel.getName());
        curtain.setProductDesc(curtainModel.getProductDesc());
        // curtain.setCurtainType(CurtainType.valueOf(curtainModel.getCurtainType()));
        curtain.setWidth(curtainModel.getWidth());
        curtain.setHeight(curtainModel.getHeight());
        curtain.setItemNumber(curtainModel.getItemNumber());
        curtain.setPatternRep(curtainModel.getPatternRep());
        curtain.setPrice(curtainModel.getPrice());
        curtain.setComposition(curtainModel.getComposition());
        curtain.setProductFamily(curtainModel.getProductFamily());
        curtain.setCleaningInst(curtainModel.getCleaningInst());
        curtain.setTypeOfSize(curtainModel.getTypeOfSize());
    }

    private CurtainModel createCurtainModel(Curtain curtain) {
        Long curtainId = curtain.getId();

        List<Image> imagesByProductId = getCurtainImagesByProductId(curtainId);
        List<AttributeListItem> curtainAllAttribute = getCurtainAllAttribute(curtainId);

        List<ImageData> images = convertImgsToDTO(imagesByProductId);
        List<AttributeModel> attributes = convertAttributesToDTO(curtainAllAttribute);

        CurtainModel curtainModel = new CurtainModel(curtain);

        curtainModel.setAttributes(attributes);
        curtainModel.setImageList(images);
        return curtainModel;
    }

    private List<AttributeListItem> getCurtainAllAttribute(Long curtainId) {
        return attributeService.findProductAllAttribute(curtainId);
    }

    private List<Image> getCurtainImagesByProductId(Long curtainId) {
        return imageService.getImagesByProductId(curtainId);
    }

    private List<AttributeModel> convertAttributesToDTO(List<AttributeListItem> productAllAttribute) {
        List<AttributeModel> attributes = new ArrayList<>();
        for (AttributeListItem attributeListItem : productAllAttribute) {
            attributes.add(new AttributeModel(attributeListItem));
        }
        return attributes;
    }

    private List<ImageData> convertImgsToDTO(List<Image> imagesByProductId) {
        List<ImageData> images = new ArrayList<>();
        for (Image image : imagesByProductId) {
            images.add(new ImageData(image));
        }
        return images;
    }

}
