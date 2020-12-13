package com.example.decorate.services.decoration;

import com.example.decorate.domain.*;
import com.example.decorate.repositorys.decoration.DecorationAttributeRepository;
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
public class DecorationAttributeService {
    public final DecorationAttributeRepository decorationAttributeRepository;

    public void saveDecorationAttributes(Decoration decoration, List<Attribute> attributeList) {
        KeyHolder keyHolder = decoration.getKey();
        for (Attribute attribute : attributeList) {
            decorationAttributeRepository.save(new DecorationAttribute(attribute, decoration, keyHolder));
        }
    }

    public List<DecorationAttribute> findAllDecorationAttributeByDecorationId(Long decorationId) {
        return decorationAttributeRepository.fetchAllByCurtainId(decorationId);
    }

    public void updateDecorateAttributes(Decoration decoration, List<Attribute> attributeList) {
        List<Long> activeDecorationAttributeIdList = new ArrayList<>();
        Long decorationId = decoration.getId();
        for (Attribute attribute : attributeList) {
            KeyHolder decorationKey = decoration.getKey();
            Long attributeId = attribute.getId();

            Optional<DecorationAttribute> decorationAttribute = decorationAttributeRepository
                    .fetchByAttributeIdAndDecorationId(attributeId, decorationId);

            DecorationAttribute persistentDecorationAttribute = decorationAttribute
                    .orElseGet(this::createNewPersistentDecorationAttribute);
            updateDecorationAttribute(decoration, attribute, decorationKey, persistentDecorationAttribute);

            Long decorationAttributeId = persistentDecorationAttribute.getId();
            activeDecorationAttributeIdList.add(decorationAttributeId);
        }
        decorationAttributeRepository.deleteDecorationNotUsedAttributes(activeDecorationAttributeIdList, decorationId);
    }

    private void updateDecorationAttribute(Decoration decoration, Attribute attribute, KeyHolder key, DecorationAttribute persistentDecorationAttribute) {
        persistentDecorationAttribute.setAttribute(attribute);
        persistentDecorationAttribute.setDecoration(decoration);
        persistentDecorationAttribute.setKey(key);
        persistentDecorationAttribute.setModified(Instant.now());
    }

    private DecorationAttribute createNewPersistentDecorationAttribute() {
        DecorationAttribute decorationAttr = new DecorationAttribute();
        decorationAttributeRepository.save(decorationAttr);
        return decorationAttr;
    }

    public void deleteAllByDecorationId(Long decorationId) {
        decorationAttributeRepository.deleteAllByDecorationId(decorationId);
    }
}
