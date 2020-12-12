package com.example.decorate.services;

import com.example.decorate.domain.*;
import com.example.decorate.domain.dto.AttributeCreationFormData;
import com.example.decorate.domain.dto.AttributeModel;
import com.example.decorate.domain.dto.AttributeListItemData;
import com.example.decorate.domain.dto.FormData;
import com.example.decorate.exception.DecorateBackendException;
import com.example.decorate.exception.ExceptionMessages;
import com.example.decorate.repositorys.AttributeListItemRepository;
import com.example.decorate.repositorys.AttributeRepository;
import com.example.decorate.repositorys.KeyHolderRepository;
import com.example.decorate.services.curtain.CurtainAttributeService;
import com.example.decorate.services.furniture.FurnitureFabricAttributeService;
import com.example.decorate.services.wallpaper.WallpaperAttributeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.decorate.exception.ExceptionMessages.*;

@Slf4j
@AllArgsConstructor
@Service
@Transactional
public class AttributeService {

    private final AttributeRepository attributeRepository;
    private final AttributeListItemRepository attributeListItemRepository;
    private final KeyHolderRepository keyHolderRepository;
    private final CurtainAttributeService curtainAttributeService;
    private final WallpaperAttributeService wallpaperAttributeService;
    private final FurnitureFabricAttributeService furnitureFabricAttributeService;



    public List<Attribute> saveAttributes(List<AttributeCreationFormData> attributeCreationFormDataList) {
        List<Attribute> productAttributes = new ArrayList<>();

        for (AttributeCreationFormData attributeCreationFormData : attributeCreationFormDataList) {
            String attrType = attributeCreationFormData.getType();
            String attrDesc = attributeCreationFormData.getDescription();
            attributeCreationProcess(productAttributes, attrType, attrDesc);
        }
        return productAttributes;
    }

    public List<Attribute> updateAttributes(List<AttributeModel> attributeModels) {
        List<Attribute> productAttributes = new ArrayList<>();

        for (AttributeModel attributeModel : attributeModels) {
            String attrType = attributeModel.getType();
            String attrDesc = attributeModel.getDescription();
            attributeCreationProcess(productAttributes, attrType, attrDesc);
        }
        return productAttributes;
    }

    public void createCurtainAttributes(Curtain curtain, List<AttributeCreationFormData> attributeCreationFormDataList) {
        List<Attribute> attributeList = saveAttributes(attributeCreationFormDataList);
        curtainAttributeService.saveCurtainAttributes(curtain, attributeList);
    }

    public void createWallpaperAttributes(Wallpaper wallpaper, List<AttributeCreationFormData> attributeCreationFormDataList) {
        List<Attribute> attributeList = saveAttributes(attributeCreationFormDataList);
        wallpaperAttributeService.saveWallpaperAttributes(wallpaper, attributeList);
    }


    private void attributeCreationProcess(List<Attribute> productAttributes, String attrType, String attrDesc) {
        Attribute attribute = saveAttribute(attrType, attrDesc);
        productAttributes.add(attribute);
    }

    public Attribute saveAttribute(AttributeCreationFormData attributeCreationFormData) {
        Optional<Attribute> attribute = fetchByDescription(attributeCreationFormData.getDescription());
        return attribute.orElseGet(() -> createAttribute(attributeCreationFormData));
    }

    public Attribute saveAttribute(String attributeType, String attributeDesc) {
        Optional<Attribute> attribute = fetchByDescription(attributeDesc);
        return attribute.orElseGet(() -> createAttribute(attributeType, attributeDesc));
    }

    private Attribute createAttribute(AttributeCreationFormData attributeCreationFormData) {
        Attribute attributeToSave = new Attribute(attributeCreationFormData);
        attributeRepository.save(attributeToSave);
        return attributeToSave;
    }

    private Attribute createAttribute(String attributeType, String attributeDesc) {
        Attribute attributeToSave = new Attribute(attributeType, attributeDesc);
        attributeRepository.save(attributeToSave);
        return attributeToSave;
    }

    private Optional<Attribute> fetchByDescription(String description) {
        return attributeRepository
                .findByDescription(description);
    }

    public void deleteAttributeListItemById(Long attributeListItemId) {
        AttributeListItem attributeListItem = getAttributeListItemById(attributeListItemId);
        attributeListItemRepository.delete(attributeListItem);
    }

    private AttributeListItem getAttributeListItemById(Long attributeListItemId) {
        return attributeListItemRepository.findById(attributeListItemId)
                .orElseThrow(() -> new DecorateBackendException(ExceptionMessages.ATTRIBUTE_LIST_ITEM_NOT_EXISTS.getMessage()));
    }

    public void saveListItem(Attribute attribute, KeyHolder keyHolder) {

        AttributeListItem attributeListItem = AttributeListItem.builder()
                .attribute(attribute)
                .key(keyHolder)
                .build();
        attributeListItemRepository.save(attributeListItem);
    }

    public void saveAttributeListItem(List<AttributeListItemData> attributeListItemDataList, Long prodId) {
        if (attributeListItemDataList.isEmpty()) {
            throw new DecorateBackendException(ATTRIBUTE_LIST_EMPTY.getMessage());
        }

        for (AttributeListItemData attributeListItemData : attributeListItemDataList) {
            Long id = attributeListItemData.getId();

            Attribute attribute = attributeRepository
                    .findById(id)
                    .orElseThrow(() -> new DecorateBackendException(ATTRIBUTE_NOT_EXISTS.getMessage()));
            KeyHolder keyHolder = keyHolderRepository
                    .findById(prodId)
                    .orElseThrow(() -> new DecorateBackendException(PRODUCT_NOT_EXISTS.getMessage()));

            saveListItem(attribute, keyHolder);
        }
    }

    public FormData getAll() {
        List<AttributeModel> attributeModels = new ArrayList<>();
        for (Attribute attribute : attributeRepository.findAll()) {
            attributeModels.add(new AttributeModel(attribute));
        }
        return new FormData(attributeModels);
    }

    public List<AttributeListItem> findProductAllAttribute(Long productId) {
        return attributeListItemRepository.findAllByKey_Id(productId);
    }

    public void deleteProductAttributeListItems(Long productId) {
        List<AttributeListItem> productAllAttributeListItems = findProductAllAttribute(productId);
        attributeListItemRepository.deleteAll(productAllAttributeListItems);
    }

    public void saveAttributesFromExcel(List<AttributeCreationFormData> attributes, KeyHolder keyHolder) {
        for (AttributeCreationFormData attribute : attributes) {
            Optional<Attribute> attributeByDesc = attributeRepository.findByDescription(attribute.getDescription());
            if (attributeByDesc.isPresent()) {
                saveListItem(attributeByDesc.get(), keyHolder);
            } else {
                Attribute attr = new Attribute();
                for (AttributeType attributeType : AttributeType.values()) {
                    if (attributeType.getType().equals(attribute.getType())) {
                        attr.setDescription(attribute.getDescription());
                        attr.setType(attributeType);
                    }
                }
                attributeRepository.save(attr);
                saveListItem(attr, keyHolder);
            }
        }

    }

    public List<CurtainAttribute> fetchAllCurtainAttributes(Long curtainId) {
        return curtainAttributeService.findAllCurtainAttributeByCurtainId(curtainId);
    }

    public List<WallpaperAttribute> fetchAllWallpaperAttributes(Long wallpaperId) {
        return wallpaperAttributeService.findAllWallpaperAttributeByWallpaperId(wallpaperId);
    }

    public void updateWallpaperAttributes(Wallpaper wallpaper, List<AttributeModel> attributeModels) {
        List<Attribute> attributeList = updateAttributes(attributeModels);
        wallpaperAttributeService.updateWallpaperAttributes(wallpaper, attributeList);
    }

    public void updateCurtainAttributes(Curtain curtain, List<AttributeModel> attributeModels) {
        List<Attribute> attributeList = updateAttributes(attributeModels);
        curtainAttributeService.updateCurtainAttributes(curtain, attributeList);
    }

    public void deleteProductAttributeItems(Wallpaper wallpaper) {
        Long wallpaperId = wallpaper.getId();
        wallpaperAttributeService.deleteAllByWallpaperId(wallpaperId);
    }

    public void deleteProductAttributeItems(Curtain curtain) {
        Long curtainId = curtain.getId();
        curtainAttributeService.deleteAllByCurtainId(curtainId);
    }

    public void deleteProductAttributeItems(FurnitureFabric furnitureFabric) {
        Long furnitureFabricId = furnitureFabric.getId();
        furnitureFabricAttributeService.deleteAllByFurnitureFabricId(furnitureFabricId);
    }

    public void createFurnitureFabricAttributes(FurnitureFabric furnitureFabric, List<AttributeCreationFormData> furnitureFabricAttributes) {
        List<Attribute> attributeList = saveAttributes(furnitureFabricAttributes);
        furnitureFabricAttributeService.saveFurnitureFabricAttributes(furnitureFabric, attributeList);
    }

    public List<FurnitureFabricAttribute> fetchAllFurnitureFabricAttributes(Long furnitureFabricId) {
        return furnitureFabricAttributeService.findAllFurnitureAttributeByFurnitureFabricId(furnitureFabricId);
    }

    public void updateFurnitureFabricAttributes(FurnitureFabric furnitureFabric, List<AttributeModel> attributes) {
        List<Attribute> attributeList = updateAttributes(attributes);
        furnitureFabricAttributeService.updateFurnitureFabricAttributes(furnitureFabric, attributeList);
    }
}
