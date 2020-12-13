package com.example.decorate.services.furniture;

import com.example.decorate.domain.*;
import com.example.decorate.repositorys.furniture.FurnitureFabricAttributeRepository;
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
public class FurnitureFabricAttributeService {
    private final FurnitureFabricAttributeRepository furnitureFabricAttributeRepository;

    public void saveFurnitureFabricAttributes(FurnitureFabric furnitureFabric, List<Attribute> attributes) {
        KeyHolder keyHolder = furnitureFabric.getKey();
        for (Attribute attribute : attributes) {
            furnitureFabricAttributeRepository.save(new FurnitureFabricAttribute(attribute, furnitureFabric, keyHolder));
        }
    }

    public List<FurnitureFabricAttribute> findAllFurnitureAttributeByFurnitureFabricId(Long furnitureFabricId) {
        return furnitureFabricAttributeRepository.fetchAllByFurnitureFabricId(furnitureFabricId);
    }

    public void updateFurnitureFabricAttributes(FurnitureFabric furnitureFabric, List<Attribute> attributes) {
        List<Long> activeCurtainAttributeIdList = new ArrayList<>();
        Long curtainId = furnitureFabric.getId();
        for (Attribute attribute : attributes) {
            KeyHolder furnitureFabricKey = furnitureFabric.getKey();
            Long attributeId = attribute.getId();

            Optional<FurnitureFabricAttribute> furnitureFabricAttribute = furnitureFabricAttributeRepository
                    .fetchByAttributeIdAndCurtainId(attributeId, curtainId);

            FurnitureFabricAttribute persistentCurtainAttribute = furnitureFabricAttribute
                    .orElseGet(this::createNewPersistentFurnitureFabricAttribute);
            updateFurnitureFabricAttribute(furnitureFabric, attribute, furnitureFabricKey, persistentCurtainAttribute);

            activeCurtainAttributeIdList.add(persistentCurtainAttribute.getId());
        }
        furnitureFabricAttributeRepository.deleteFurnitureFabricNotUsedAttributes(activeCurtainAttributeIdList, curtainId);
    }

    private void updateFurnitureFabricAttribute(FurnitureFabric furnitureFabric, Attribute attribute, KeyHolder key, FurnitureFabricAttribute persistentCurtainAttribute) {
        persistentCurtainAttribute.setAttribute(attribute);
        persistentCurtainAttribute.setFurnitureFabric(furnitureFabric);
        persistentCurtainAttribute.setKey(key);
        persistentCurtainAttribute.setModified(Instant.now());
    }

    private FurnitureFabricAttribute createNewPersistentFurnitureFabricAttribute() {
        FurnitureFabricAttribute furnitureFabricAttr = new FurnitureFabricAttribute();
        furnitureFabricAttributeRepository.save(furnitureFabricAttr);
        return furnitureFabricAttr;
    }

    public void deleteAllByFurnitureFabricId(Long curtainId) {
        furnitureFabricAttributeRepository.deleteAllByFurnitureFabricId(curtainId);
    }
}
