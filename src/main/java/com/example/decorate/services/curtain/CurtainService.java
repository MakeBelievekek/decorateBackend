package com.example.decorate.services.curtain;

import com.example.decorate.domain.*;
import com.example.decorate.domain.dto.*;
import com.example.decorate.exception.DecorateBackendException;
import com.example.decorate.repositorys.curtain.CurtainRepository;
import com.example.decorate.services.*;
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
    private final ModelCreatorService modelCreatorService;
    private final EntityUpdateService entityUpdateService;

    public void saveCurtain(ProductCreationFormData productCreationFormData) {
        KeyHolder keyHolder = new KeyHolder();
        keyHolderService.saveKey(keyHolder, CURTAIN);

        Curtain curtain = new Curtain(productCreationFormData, keyHolder);
        curtainRepository.save(curtain);

        List<AttributeCreationFormData> curtainAttributes = productCreationFormData.getAttributeCreationFormDataList();
        attributeService.createCurtainAttributes(curtain, curtainAttributes);

        imageService.saveImageList(productCreationFormData.getImageList(), keyHolder.getId(), CURTAIN);

    }

    public CurtainModel getCurtain(Long curtainId) {
        Curtain curtain = getCurtainById(curtainId);

        return modelCreatorService.createCurtainModel(curtain);
    }

    public List<CurtainModel> getAllCurtains() {
        List<Curtain> allCurtains = curtainRepository.getAllCurtains();
        List<CurtainModel> allCurtainModels = new ArrayList<>();

        for (Curtain curtain : allCurtains) {
            allCurtainModels.add(modelCreatorService.createCurtainModel(curtain));
        }
        return allCurtainModels;
    }

    public void updateCurtain(Long curtainId, CurtainModel curtainModel) {
        Curtain curtain = getCurtainById(curtainId);

        entityUpdateService.setCurtainUpdatedValues(curtain, curtainModel);
        attributeService.updateCurtainAttributes(curtain, curtainModel.getAttributes());
        imageService.updateProductImages(curtainId, curtainModel.getImageList(), CURTAIN);

        curtainRepository.save(curtain);
    }

    public void deleteCurtain(Long curtainId) {
        Curtain curtain = getCurtainById(curtainId);

        imageService.deleteProductImages(curtainId);
        attributeService.deleteProductAttributeItems(curtain);
        keyHolderService.deleteKeyHolder(curtainId);

        curtainRepository.delete(curtain);
    }

    private Curtain getCurtainById(Long curtainId) {
        return curtainRepository.findById(curtainId)
                .orElseThrow(() -> new DecorateBackendException(CURTAIN_NOT_EXISTS.getMessage()));
    }
}
