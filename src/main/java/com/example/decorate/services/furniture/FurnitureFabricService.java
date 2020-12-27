package com.example.decorate.services.furniture;

import com.example.decorate.domain.FurnitureFabric;
import com.example.decorate.domain.ProductKey;
import com.example.decorate.domain.dto.*;
import com.example.decorate.exception.DecorateBackendException;
import com.example.decorate.repositorys.furniture.FurnitureFabricRepository;
import com.example.decorate.services.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.decorate.domain.ProductType.FURNITURE;
import static com.example.decorate.exception.ExceptionMessages.FURNITURE_NOT_EXISTS;

@Slf4j
@AllArgsConstructor
@Service
@Transactional
public class FurnitureFabricService {
    private final ProductKeyService productKeyService;
    private final FurnitureFabricRepository furnitureFabricRepository;
    private final AttributeService attributeService;
    private final ImageService imageService;
    private final ModelCreatorService modelCreatorService;
    private final EntityUpdateService entityUpdateService;
    private final EntityCreatorService entityCreatorService;

    public void saveFurnitureFabric(ProductCreationFormData productCreationFormData) {
        ProductKey furnitureFabricProductKey = new ProductKey();
        productKeyService.saveKey(furnitureFabricProductKey, FURNITURE);

        FurnitureFabric furnitureFabric = entityCreatorService
                .createFurnitureFabricFromCreationModel(productCreationFormData, furnitureFabricProductKey);
        furnitureFabricRepository.save(furnitureFabric);

        List<AttributeCreationFormData> furnitureFabricAttributes = productCreationFormData.getAttributeCreationFormDataList();
        attributeService.createProductAttributeItems(furnitureFabricAttributes, furnitureFabricProductKey);

        imageService.saveImageList(productCreationFormData.getImageList(), furnitureFabricProductKey);
    }

    public FurnitureFabricModel getFurnitureFabric(Long furnitureFabricId) {
        FurnitureFabric furnitureFabric = getFurnitureFabricById(furnitureFabricId);

        return modelCreatorService.createFurnitureFabricModel(furnitureFabric);
    }

    public List<FurnitureFabricModel> getAllFurnitureFabrics() {
        List<FurnitureFabric> furnitureFabrics = furnitureFabricRepository.getAllFurnitureFabrics();

        return furnitureFabrics.stream()
                .map(modelCreatorService::createFurnitureFabricModel)
                .collect(Collectors.toList());
    }

    public void updateFurnitureFabric(Long furnitureFabricId, FurnitureFabricModel furnitureFabricModel) {
        FurnitureFabric furnitureFabric = getFurnitureFabricById(furnitureFabricId);
        ProductKey furnitureFabricProductKey = furnitureFabric.getProductKey();

        entityUpdateService.setFurnitureFabricUpdatedValues(furnitureFabric, furnitureFabricModel);
        attributeService.updateProductAttributes(furnitureFabricProductKey, furnitureFabricModel.getAttributes());
        imageService.updateProductImages(furnitureFabricProductKey, furnitureFabricModel.getImageList());

        furnitureFabricRepository.save(furnitureFabric);
    }

    public void deleteFurnitureFabric(Long furnitureFabricId) {
        FurnitureFabric furnitureFabric = getFurnitureFabricById(furnitureFabricId);
        ProductKey furnitureFabricProductKey = furnitureFabric.getProductKey();

        imageService.deleteProductImages(furnitureFabricProductKey);
        attributeService.deleteProductAttributeItems(furnitureFabricProductKey);
        productKeyService.deleteKeyHolder(furnitureFabricProductKey);

        furnitureFabricRepository.delete(furnitureFabric);
    }

    public List<FurnitureFabricModel> getFurnitureFabricModelsForList(SearchModel searchModel) {
        List<Long> attributeIds = searchModel.getAttributeIds();
        Long searchParameterCount = (long) attributeIds.size();

        return furnitureFabricRepository.findAllByAttributeIds(attributeIds, searchParameterCount)
                .stream()
                .map(modelCreatorService::createFurnitureFabricModel)
                .collect(Collectors.toList());
    }

    private FurnitureFabric getFurnitureFabricById(Long curtainId) {
        return furnitureFabricRepository.findById(curtainId)
                .orElseThrow(() -> new DecorateBackendException(FURNITURE_NOT_EXISTS.getMessage()));
    }
}
