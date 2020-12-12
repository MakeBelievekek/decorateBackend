package com.example.decorate.services.furniture;

import com.example.decorate.domain.Attribute;
import com.example.decorate.domain.FurnitureFabric;
import com.example.decorate.domain.FurnitureFabricAttribute;
import com.example.decorate.domain.KeyHolder;
import com.example.decorate.domain.dto.AttributeCreationFormData;
import com.example.decorate.domain.dto.CurtainModel;
import com.example.decorate.domain.dto.FurnitureFabricModel;
import com.example.decorate.domain.dto.ProductCreationFormData;
import com.example.decorate.exception.DecorateBackendException;
import com.example.decorate.repositorys.furniture.FurnitureFabricRepository;
import com.example.decorate.services.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.example.decorate.domain.ProductType.CURTAIN;
import static com.example.decorate.domain.ProductType.FURNITURE_FABRIC;
import static com.example.decorate.exception.ExceptionMessages.FURNITURE_FABRIC_NOT_EXISTS;

@Slf4j
@AllArgsConstructor
@Service
@Transactional
public class FurnitureFabricService {
    private final KeyHolderService keyHolderService;
    private final FurnitureFabricRepository furnitureFabricRepository;
    private final AttributeService attributeService;
    private final ImageService imageService;
    private final ModelCreatorService modelCreatorService;
    private final EntityUpdateService entityUpdateService;
    private final FurnitureFabricAttributeService furnitureFabricAttributeService;

    public void saveFurnitureFabric(ProductCreationFormData productCreationFormData) {
        KeyHolder keyHolder = new KeyHolder();
        keyHolderService.saveKey(keyHolder, CURTAIN);

        FurnitureFabric furnitureFabric = new FurnitureFabric(productCreationFormData, keyHolder);
        furnitureFabricRepository.save(furnitureFabric);

        List<AttributeCreationFormData> furnitureFabricAttributes = productCreationFormData.getAttributeCreationFormDataList();
        attributeService.createFurnitureFabricAttributes(furnitureFabric, furnitureFabricAttributes);
        imageService.saveImageList(productCreationFormData.getImageList(), keyHolder.getId(), FURNITURE_FABRIC);
    }

    public FurnitureFabricModel getFurnitureFabric(Long furnitureFabricId) {
        FurnitureFabric furnitureFabric = getFurnitureFabricById(furnitureFabricId);

        return modelCreatorService.createFurnitureFabricModel(furnitureFabric);
    }

    public List<FurnitureFabricModel> getAllFurnitureFabrics() {
        List<FurnitureFabric> furnitureFabrics = furnitureFabricRepository.getAllFurnitureFabrics();
        List<FurnitureFabricModel> allFurnitureFabricModels = new ArrayList<>();

        for (FurnitureFabric furnitureFabric : furnitureFabrics) {
            allFurnitureFabricModels.add(modelCreatorService.createFurnitureFabricModel(furnitureFabric));
        }
        return allFurnitureFabricModels;
    }

    public void updateFurnitureFabric(Long furnitureFabricId, FurnitureFabricModel furnitureFabricModel) {
        FurnitureFabric furnitureFabric = getFurnitureFabricById(furnitureFabricId);

        entityUpdateService.setFurnitureFabricUpdatedValues(furnitureFabric, furnitureFabricModel);
        attributeService.updateFurnitureFabricAttributes(furnitureFabric, furnitureFabricModel.getAttributes());
        imageService.updateProductImages(furnitureFabricId, furnitureFabricModel.getImageList());

        furnitureFabricRepository.save(furnitureFabric);
    }

    public void deleteFurnitureFabric(Long furnitureFabricId) {
        FurnitureFabric furnitureFabric = getFurnitureFabricById(furnitureFabricId);

        imageService.deleteProductImages(furnitureFabricId);
        attributeService.deleteProductAttributeItems(furnitureFabric);
        keyHolderService.deleteKeyHolder(furnitureFabricId);

        furnitureFabricRepository.delete(furnitureFabric);
    }

    private FurnitureFabric getFurnitureFabricById(Long curtainId) {
        return furnitureFabricRepository.findById(curtainId)
                .orElseThrow(() -> new DecorateBackendException(FURNITURE_FABRIC_NOT_EXISTS.getMessage()));
    }
}
