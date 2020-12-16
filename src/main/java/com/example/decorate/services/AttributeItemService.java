package com.example.decorate.services;

import com.example.decorate.domain.*;
import com.example.decorate.mapper.AttributeItemMapper;
import com.example.decorate.repositorys.AttributeItemRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
@Transactional
public class AttributeItemService {
    private final AttributeItemRepository attributeItemRepository;
    private final AttributeItemMapper attributeItemMapper;


    public void createProductAttributeItems(List<Attribute> productAttributes, ProductKey productKey) {
        log.warn("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        log.info(productAttributes.toString());
        for (Attribute attribute : productAttributes) {
            AttributeItem attributeItem = attributeItemMapper.createAttributeItemFromAttribute(attribute, productKey);
            log.info(attributeItem.toString());
            attributeItemRepository.save(attributeItem);
        }
    }

    public void updateProductAttributeItems(ProductKey productKey, List<Attribute> attributeList) {
        List<AttributeItem> activeProductAttributeItems = new ArrayList<>();
        for (Attribute attribute : attributeList) {

            Optional<AttributeItem> productAttributeItem = attributeItemRepository
                    .fetchByAttributeAndProductKey(attribute, productKey);

            AttributeItem persistentAttributeItem = productAttributeItem
                    .orElseGet(this::createNewPersistentAttributeItem);
            updateAttributeItem(attribute, productKey, persistentAttributeItem);

            activeProductAttributeItems.add(persistentAttributeItem);
        }
        attributeItemRepository.deleteProductNotUsedAttributeItems(activeProductAttributeItems, productKey);
    }

    private void updateAttributeItem(Attribute attribute, ProductKey productKey, AttributeItem persistentAttributeItem) {
        persistentAttributeItem.setAttribute(attribute);
        persistentAttributeItem.setProductKey(productKey);
        persistentAttributeItem.setModified(Instant.now());
    }

    private AttributeItem createNewPersistentAttributeItem() {
        AttributeItem productAttributeItem = new AttributeItem();
        attributeItemRepository.save(productAttributeItem);
        return productAttributeItem;
    }

    public void deleteProductAllAttributeItemsByProductKey(ProductKey productKey) {
        attributeItemRepository.deleteAllByProductKey(productKey);

    }

    public List<AttributeItem> findProductAllAttributeItemsByProductKey(ProductKey productKey) {
        return attributeItemRepository.findProductAllAttributeItemsByProductKey(productKey);
    }
}
