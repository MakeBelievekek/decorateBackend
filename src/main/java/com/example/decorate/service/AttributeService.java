package com.example.decorate.service;

import com.example.decorate.domain.Attribute;
import com.example.decorate.domain.AttributeListItem;
import com.example.decorate.domain.KeyHolder;
import com.example.decorate.domain.dto.AttributeFormData;
import com.example.decorate.domain.dto.AttributeFormListItem;
import com.example.decorate.domain.dto.AttributeListItemData;
import com.example.decorate.domain.dto.FormData;
import com.example.decorate.repository.AttributeListItemRepository;
import com.example.decorate.repository.AttributeRepository;
import com.example.decorate.repository.KeyHolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AttributeService {

    private AttributeRepository attributeRepository;
    private AttributeListItemRepository attributeListItemRepository;
    private KeyHolderRepository keyHolderRepository;

    @Autowired
    public AttributeService(AttributeRepository attributeRepository, AttributeListItemRepository attributeListItemRepository, KeyHolderRepository keyHolderRepository) {
        this.attributeRepository = attributeRepository;
        this.attributeListItemRepository = attributeListItemRepository;
        this.keyHolderRepository = keyHolderRepository;
    }

    public void saveAttribute(AttributeFormData attributeFormData) {
        Attribute attribute = new Attribute(attributeFormData);
        attributeRepository.save(attribute);
    }

    public void saveAttributeListItem(List<AttributeListItemData> attributeListItemDataList, Long prodId) {
        for (AttributeListItemData attributeListItemData : attributeListItemDataList) {
            Optional<Attribute> optAttribute = attributeRepository.findById(attributeListItemData.getId());
            Optional<KeyHolder> keyHolder = keyHolderRepository.findById(prodId);
            if (optAttribute.isPresent()) {
                if (keyHolder.isPresent()) {
                    AttributeListItem attributeListItem = new AttributeListItem(keyHolder.get(), optAttribute.get());
                    attributeListItemRepository.save(attributeListItem);
                }
            }
        }
    }

    public FormData getAll() {
        List<AttributeFormListItem> attributeFormListItems = new ArrayList<>();
        for (Attribute attribute : attributeRepository.findAll()) {
            attributeFormListItems.add(new AttributeFormListItem(attribute));
        }
        return new FormData(attributeFormListItems);
    }

}
