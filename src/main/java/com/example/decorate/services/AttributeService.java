package com.example.decorate.services;

import com.example.decorate.domain.Attribute;
import com.example.decorate.domain.AttributeItem;
import com.example.decorate.domain.ProductKey;
import com.example.decorate.domain.dto.AttributeCreationFormData;
import com.example.decorate.domain.dto.AttributeModel;
import com.example.decorate.domain.dto.FormData;
import com.example.decorate.repositorys.AttributeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
@Transactional
public class AttributeService {

    private final AttributeRepository attributeRepository;
    private final AttributeItemService attributeItemService;

    public List<Attribute> updateAttributes(List<AttributeModel> attributeModels) {
        List<Attribute> productAttributes = new ArrayList<>();

        for (AttributeModel attributeModel : attributeModels) {
            String attrType = attributeModel.getType();
            String attrDesc = attributeModel.getDescription();
            attributeCreationProcess(productAttributes, attrType, attrDesc);
        }
        return productAttributes;
    }

    private void attributeCreationProcess(List<Attribute> productAttributes,
                                          String attrType,
                                          String attrDesc) {
        Attribute attribute = saveAttribute(attrType, attrDesc);
        productAttributes.add(attribute);
    }

    public Attribute saveAttribute(String attributeType, String attributeDesc) {
        Optional<Attribute> attribute = fetchByDescription(attributeDesc);
        return attribute.orElseGet(() -> createAttribute(attributeType, attributeDesc));
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

    public FormData getAllAttributes() {
        List<AttributeModel> attributeModels = attributeRepository.findAll()
                .stream()
                .map(AttributeModel::new)
                .collect(Collectors.toList());
        return new FormData(attributeModels);
    }

    public void createProductAttributeItems(List<AttributeCreationFormData> productAttributes, ProductKey productKey) {
        List<Attribute> attributes = saveAttributes(productAttributes);
        attributeItemService.createProductAttributeItems(attributes, productKey);
    }

    public List<Attribute> saveAttributes(List<AttributeCreationFormData> attributeCreationFormDataList) {
        List<Attribute> productAttributes = new ArrayList<>();

        for (AttributeCreationFormData attributeCreationFormData : attributeCreationFormDataList) {
            String attrType = attributeCreationFormData.getType();
            String attrDesc = attributeCreationFormData.getDescription();
            attributeCreationProcess(productAttributes, attrType, attrDesc);
        }
        return productAttributes;
    }

    public void updateProductAttributes(ProductKey productKey, List<AttributeModel> attributes) {
        List<Attribute> attributeList = updateAttributes(attributes);
        attributeItemService.updateProductAttributeItems(productKey, attributeList);
    }

    public void deleteProductAttributeItems(ProductKey productKey) {
        attributeItemService.deleteProductAllAttributeItemsByProductKey(productKey);
    }

    public List<Attribute> getProductAllAttributesByProductKey(ProductKey productKey) {
       return attributeItemService
               .findProductAllAttributeItemsByProductKey(productKey).stream()
               .map(AttributeItem::getAttribute)
               .collect(Collectors.toList());
    }
}
