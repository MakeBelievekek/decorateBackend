package com.example.decorate.services.curtain;

import com.example.decorate.domain.*;
import com.example.decorate.domain.dto.*;
import com.example.decorate.exception.DecorateBackendException;
import com.example.decorate.mapper.CurtainMapper;
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

    private final ProductKeyService productKeyService;
    private final CurtainRepository curtainRepository;
    private final AttributeService attributeService;
    private final ImageService imageService;
    private final ModelCreatorService modelCreatorService;
    private final EntityUpdateService entityUpdateService;

    public void saveCurtain(ProductCreationFormData productCreationFormData) {
        ProductKey curtainProductKey = new ProductKey();
        productKeyService.saveKey(curtainProductKey, CURTAIN);

        Curtain curtain = new Curtain(productCreationFormData, curtainProductKey);
        curtainRepository.save(curtain);

        List<AttributeCreationFormData> curtainAttributes = productCreationFormData.getAttributeCreationFormDataList();
        attributeService.createProductAttributeItems(curtainAttributes, curtainProductKey);

        imageService.saveImageList(productCreationFormData.getImageList(), curtainProductKey);

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
        ProductKey curtainProductKey = curtain.getProductKey();

        entityUpdateService.setCurtainUpdatedValues(curtain, curtainModel);
        attributeService.updateProductAttributes(curtainProductKey, curtainModel.getAttributes());
        imageService.updateProductImages(curtainProductKey, curtainModel.getImageList());

        curtainRepository.save(curtain);
    }

    public void deleteCurtain(Long curtainId) {
        Curtain curtain = getCurtainById(curtainId);
        ProductKey curtainProductKey = curtain.getProductKey();

        imageService.deleteProductImages(curtainProductKey);
        attributeService.deleteProductAttributeItems(curtainProductKey);
        productKeyService.deleteKeyHolder(curtainProductKey);

        curtainRepository.delete(curtain);
    }

    public List<CurtainModel> getCurtainModelsForList(SearchModel searchModel) {
        return null;
    }

    private Curtain getCurtainById(Long curtainId) {
        return curtainRepository.findById(curtainId)
                .orElseThrow(() -> new DecorateBackendException(CURTAIN_NOT_EXISTS.getMessage()));
    }
}
