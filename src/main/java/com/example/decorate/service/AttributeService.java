package com.example.decorate.service;

import com.example.decorate.domain.*;
import com.example.decorate.domain.dto.AttributeCreationFormData;
import com.example.decorate.domain.dto.AttributeListItemData;
import com.example.decorate.domain.dto.AttributeModel;
import com.example.decorate.domain.dto.FormData;
import com.example.decorate.exception.DecorateBackendException;
import com.example.decorate.exception.ExceptionMessages;
import com.example.decorate.repository.AttributeListItemRepository;
import com.example.decorate.repository.AttributeRepository;
import com.example.decorate.repository.KeyHolderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
@Transactional
public class AttributeService {

    private final AttributeRepository attributeRepository;
    private final AttributeListItemRepository attributeListItemRepository;
    private final KeyHolderRepository keyHolderRepository;

    public void saveAttribute(AttributeCreationFormData attributeCreationFormData) {
        Attribute attribute = new Attribute(attributeCreationFormData);
        Object exist = attributeRepository.findByDescription(attribute.getDescription());
        System.out.println(exist);
        if (exist == null) {
            attributeRepository.save(attribute);
        }
    }

    public void deleteAttributeListItem(AttributeListItem attributeListItem) {
        attributeListItemRepository.findById(attributeListItem.getId())
                .orElseThrow(() -> new DecorateBackendException(ExceptionMessages.ATTRIBUTE_LIST_ITEM_NOT_EXISTS.getMessage()));
        attributeListItemRepository.delete(attributeListItem);
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
            throw new DecorateBackendException("You must fill out the product attribute list!");
        }

        for (AttributeListItemData attributeListItemData : attributeListItemDataList) {
            Long id = attributeListItemData.getId();

            Attribute attribute = attributeRepository
                    .findById(id)
                    .orElseThrow(() -> new DecorateBackendException("Attribute dose not exists!"));
            KeyHolder keyHolder = keyHolderRepository
                    .findById(prodId)
                    .orElseThrow(() -> new DecorateBackendException("Desired product dose not exists!"));

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

    public void saveAttributesFromExcel(List<AttributeCreationFormData> attributes, KeyHolder keyHolder) {
        for (AttributeCreationFormData attribute : attributes) {
            if (attributeRepository.findByDescription(attribute.getDescription()) != null) {
                saveListItem(attributeRepository.findByDescription(attribute.getDescription()), keyHolder);
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
