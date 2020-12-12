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

    public Attribute saveAttribute(AttributeCreationFormData attributeCreationFormData) {
        Attribute attributeToSave = new Attribute(attributeCreationFormData);
        Optional<Attribute> attribute = getByDescription(attributeToSave.getDescription());
        if (attribute.isPresent()) {
            return attribute.get();
        }
        attributeRepository.save(attributeToSave);
        return attributeToSave;
    }

    private Optional<Attribute> getByDescription(String description) {
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
}
